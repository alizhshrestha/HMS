//package com.icodify.multitenant.security.config;
//
//import com.icodify.multitenant.exception.ResourceNotFoundException;
//import com.icodify.multitenant.model.entities.Admin;
//import com.icodify.multitenant.repository.AdminRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class CustomUserDetailService implements UserDetailsService {
//
//    private AdminRepository adminRepository;
//
//    public CustomUserDetailService(AdminRepository adminRepository) {
//        this.adminRepository = adminRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        //loading user from database by username
//        Admin admin = adminRepository.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", "email: " + username));
//        return null;
//
//    }
//}
