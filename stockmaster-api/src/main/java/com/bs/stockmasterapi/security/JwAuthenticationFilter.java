package com.bs.stockmasterapi.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bs.stockmasterapi.exceptions.StockMasterException;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwAuthenticationFilter extends OncePerRequestFilter {

	private final JwtProvider jwtProvider;
	private final UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String jwt = getJwtTokenormRequest(request);

		if (StringUtils.hasText(jwt) && jwtProvider.validateToken(jwt)) {
			String username = jwtProvider.getUsernamefromJwt(jwt);
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
					null, userDetails.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}else {
			throw new StockMasterException("Invalid json token");
		}

		filterChain.doFilter(request, response);
	}

	private String getJwtTokenormRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		} else {
			throw new StockMasterException("Invalid json token");
		}
	}

}
