package com.icodify.multitenant.security.config;

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

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final UserDetailsService userDetailsService;
    private final JwtTokenHelper jwtTokenHelper;

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

        if (requestToken != null && requestToken.startsWith("Bearer")) {
            token = requestToken.substring(7);
            try {
                username = this.jwtTokenHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get Jwt token");
            } catch (ExpiredJwtException e) {
                System.out.println("Jwt token has expired");
            } catch (MalformedJwtException e) {
                System.out.println("Invalid jwt");
            }
        } else
            log.info("Jwt token does not begin with Bearer");

        //once we get the token, now validate
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            boolean isSuperAdmin = false;

            //get tenant id from token and set to context
            String tenant = this.jwtTokenHelper.getTenantIdFromToken(token);
            ArrayList<String> roles = this.jwtTokenHelper.getRolesFromToken(token);
            System.out.println("Roles::::::<<<<>>>>>>>" + roles);

            for (String role : roles) {
                if (role.equals("ROLE_SUPERADMIN")) {
                    isSuperAdmin = true;
                }
            }

//            String requestTenant = request.getHeader("tenant-id");

//            if (isSuperAdmin && requestTenant != null)
//                TenantContext.setCurrentTenant(requestTenant);
//            else
//                TenantContext.setCurrentTenant(tenant);

            if (isSuperAdmin){
                System.out.println("Access To::>>>>>" + "SUPERADMIN");
            }
            else
                TenantContext.setCurrentTenant(tenant);

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (this.jwtTokenHelper.validateToken(token, userDetails)) {
                //if token is valid
                //to do authentication

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            } else {
                log.info("Invalid jwt token");
            }


        } else {
            log.info("Username is null or context is not null");
        }

        filterChain.doFilter(request, response);
    }
}
