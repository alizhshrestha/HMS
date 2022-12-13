package com.icodify.multitenant.security.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icodify.multitenant.exception.ResourceNotFoundException;
import com.icodify.multitenant.model.dto.response.RoleResponseDto;
import com.icodify.multitenant.model.entities.Account;
import com.icodify.multitenant.model.entities.AccountAdmins;
import com.icodify.multitenant.model.entities.Admin;
import com.icodify.multitenant.model.entities.Role;
import com.icodify.multitenant.repository.AccountRepository;
import com.icodify.multitenant.repository.AdminRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenHelper {

    private AdminRepository adminRepository;
    private AccountRepository accountRepository;
    private ModelMapper modelMapper;

    public JwtTokenHelper(AdminRepository adminRepository,
                          AccountRepository accountRepository,
                          ModelMapper modelMapper) {
        this.adminRepository = adminRepository;
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    private String secret = "jwtTokenKey";

    //retrieve username from jwt token
    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String getTenantIdFromToken(String token){
        final Claims claims = getAllClaimsFromToken(token);
        return claims.get("tenant-id").toString();
    }

//    public Map<String, Object> getParametersFromToken(String token){
//        final Claims claims = getAllClaimsFromToken(token);
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("tenant", claims.get("tenant-id").toString());
//        parameters.put("roles", claims.get("roles", ArrayList.class));
//        return parameters;
//    }

    public ArrayList getRolesFromToken(String token){
        final Claims claims = getAllClaimsFromToken(token);
        return claims.get("roles", ArrayList.class);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieving any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //Check if the token has expired
    private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(UserDetails userDetails) throws JsonProcessingException {

        Admin admin = adminRepository.findByEmailAndPassword(userDetails.getUsername(), userDetails.getPassword());
        List<Role> admin_roles = admin.getAdminRoles().stream().map(adminRoles -> adminRoles.getRole()).collect(Collectors.toList());
        List<String> roles = new ArrayList<>();
        admin_roles.stream().forEach(role -> {
            roles.add(role.getName());
        });

        Account accountOfAdmin = findAccountOfAdmin(admin, 1);
        Map<String, Object> claims = new HashMap<>();
//        claims.put("tenant-id", accountOfAdmin.getTitle());
        claims.put("tenant-id", "tenant");
        claims.put("roles", roles);
        return doGenerateToken(claims, userDetails.getUsername());
    }

    //while creating the token
    //1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key
    //3. According to JWS Compact Serialization
    private String doGenerateToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public Account findAccountOfAdmin(Admin admin, Integer accountId){
        Account account = admin.getAccountAdmins().stream()
                .map(AccountAdmins::getAccount)
                .filter(acc -> acc.getId() == accountId)
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException("Account", "Id", accountId));

        return account;
    }

}
