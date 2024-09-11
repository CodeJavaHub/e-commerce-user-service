package com.ecom.userservice.config;

import com.ecom.userservice.common.ErrorMessage;
import com.ecom.userservice.controller.UserController;
import com.ecom.userservice.service.impl.JwtServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.lang.NonNull;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);


  private final HandlerExceptionResolver handlerExceptionResolver;

  private final JwtServiceImpl jwtService;

  private final UserDetailsService userDetailsService;

  public JwtAuthenticationFilter(
      JwtServiceImpl jwtService,
      UserDetailsService userDetailsService,
      HandlerExceptionResolver handlerExceptionResolver) {
    this.jwtService = jwtService;
    this.userDetailsService = userDetailsService;
    this.handlerExceptionResolver = handlerExceptionResolver;
  }

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }
    try {
      final String jwt = authHeader.substring(7);
      final String userEmail = jwtService.extractUsername(jwt);
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (userEmail != null && authentication == null) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

        if (jwtService.isTokenValid(jwt, userDetails)) {
          UsernamePasswordAuthenticationToken authToken =
              new UsernamePasswordAuthenticationToken(
                  userDetails, null, userDetails.getAuthorities());

          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authToken);
        }
      }

      filterChain.doFilter(request, response);
    } catch (Exception exception) {
      log.error("Authorization Excepton {}",exception);
 //     throw exception;
//      exception.printStackTrace();
//      ErrorMessage error = new ErrorMessage();
//      error.setStatus(HttpStatus.NOT_FOUND.value());
//      error.setMessage(exception.getMessage());
//      error.setTimeStamp(System.currentTimeMillis());
//      response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//      response.getWriter().print(error);
     handlerExceptionResolver.resolveException(request, response, null, exception);
    }
  }
}
