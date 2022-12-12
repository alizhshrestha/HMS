//package com.icodify.multitenant.security.domain;
//
//import com.icodify.multitenant.security.model.JwtClaim;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Optional;
//
//@Component
//class SecurityDomainImpl implements SecurityDomain {
//
//	private static final String AUTHORIZATION_HEADER = "Authorization";
//	private static final String TOKEN_PREFIX = "Bearer ";
//
//	@Override
//	public String getTenantIdFromJwt(HttpServletRequest request) {
//		return Optional.ofNullable(request)
//				.map(JwtDecoder::new)
//				.map(jwtDecoder -> jwtDecoder.getJwtParameter(JwtClaim.TOKEN_ID))
//				.orElse(null);
//	}
//
//}
