package com.icodify.multitenant.controller;

import com.icodify.multitenant.exception.ApiException;
import com.icodify.multitenant.payloads.JwtAuthRequest;
import com.icodify.multitenant.payloads.JwtAuthResponse;
import com.icodify.multitenant.security.config.JwtTokenHelper;
import com.icodify.multitenant.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JwtTokenHelper jwtTokenHelper;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    private AdminService adminService;

    public AuthController(JwtTokenHelper jwtTokenHelper,
                          UserDetailsService userDetailsService,
                          AuthenticationManager authenticationManager,
                          AdminService adminService) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request, HttpServletRequest servletRequest) throws Exception{
        this.authenticate(request.getEmail(), request.getPassword());

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.jwtTokenHelper.generateToken(userDetails, servletRequest);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    private void authenticate(String email, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);
        try{
            this.authenticationManager.authenticate(authenticationToken);
        }catch(BadCredentialsException ex){
            throw new ApiException("Invalid username or password");
        }
    }

}
