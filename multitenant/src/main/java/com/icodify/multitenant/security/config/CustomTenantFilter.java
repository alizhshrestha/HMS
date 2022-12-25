package com.icodify.multitenant.security.config;

import com.icodify.multitenant.config.multitenancy.context.TenantContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomTenantFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        boolean isSuperAdmin = false;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            System.out.println("User has authorities: " + userDetails.getAuthorities());



            for(GrantedAuthority authority: userDetails.getAuthorities()){
                if(authority.getAuthority().equals("ROLE_SUPERADMIN")){
                    isSuperAdmin = true;
                }
            }

            if(isSuperAdmin){
                HttpServletRequest request = (HttpServletRequest) servletRequest;
                String requestTenant = request.getHeader("tenant-id");
                TenantContext.setCurrentTenant(requestTenant);

            }else{
                throw new InternalAuthenticationServiceException("Unable to authenticate user as a superadmin");
            }

        }



        filterChain.doFilter(servletRequest, servletResponse);
    }
}
