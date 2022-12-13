package com.icodify.multitenant.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icodify.multitenant.config.multitenancy.context.TenantContext;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private UserDetailsService userDetailsService;
    private JwtTokenHelper jwtTokenHelper;

    public JwtAuthenticationFilter(UserDetailsService userDetailsService, JwtTokenHelper jwtTokenHelper) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenHelper = jwtTokenHelper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //get token
        String requestToken = request.getHeader("Authorization");

        log.info("Token:::::: {}", requestToken);
        String username = null;
        String token = null;

        if(requestToken!=null && requestToken.startsWith("Bearer")){
            token = requestToken.substring(7);
            try{
                username = this.jwtTokenHelper.getUsernameFromToken(token);
            }catch (IllegalArgumentException e){
                System.out.println("Unable to get Jwt token");
            }catch (ExpiredJwtException e){
                System.out.println("Jwt token has expired");
            }catch (MalformedJwtException e){
                System.out.println("Invalid jwt");
            }
        }else
            log.info("Jwt token does not begin with Bearer");

        //once we get the token, now validate
        if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if(this.jwtTokenHelper.validateToken(token, userDetails)){
                //if token is valid
                //to do authentication
                ObjectMapper objectMapper = new ObjectMapper();
                boolean isSuperAdmin = false;

                //get tenant id from token and set to context
                String tenant = this.jwtTokenHelper.getTenantIdFromToken(token);
                ArrayList<String> roles = this.jwtTokenHelper.getRolesFromToken(token);
//                Map<String, Object> parametersFromToken = this.jwtTokenHelper.getParametersFromToken(token);
//                String tenant = parametersFromToken.get("tenant").toString();
//                Object rolesObj = parametersFromToken.get("roles");
//                List<String> roles = objectMapper.readValue(rolesObj.toString(), List.class);

//                List<String> roles = Stream.of(rolesObj).map(Object::toString).collect(Collectors.toList());
                System.out.println("tenant>>>>>>>>>>>>>>" + tenant);
                System.out.println("roles>>>>>>>>>>>>>>" + roles.get(0));

                for(String role: roles){
                    if(role.equals("ROLE_SUPERADMIN")){
                        isSuperAdmin = true;
                    }
                }
                if(isSuperAdmin)
                    TenantContext.setCurrentTenant(TenantContext.DEFAULT_TENANT_ID);
                else
                    TenantContext.setCurrentTenant(tenant);

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }else{
                log.info("Invalid jwt token");
            }
        }else{
            log.info("Username is null or context is not null");
        }

        filterChain.doFilter(request, response);
    }
}
