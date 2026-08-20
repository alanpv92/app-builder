package com.appbuilder.appbuilder.filters;

import com.appbuilder.appbuilder.entity.UserEntity;
import com.appbuilder.appbuilder.exceptions.AuthenticatoinException;
import com.appbuilder.appbuilder.repository.UserRepository;
import com.appbuilder.appbuilder.services.JwtService;
import com.appbuilder.appbuilder.utils.constants.ErrorMessageConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

   private final JwtService jwtService;
   private final UserRepository userRepository;
   private final HandlerExceptionResolver exceptionResolver;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            UserRepository userRepository,
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver
    ) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            final String authHeader = request.getHeader("Authorization");
            final String jwt;

            if(request.getRequestURI().contains("/auth")){
                filterChain.doFilter(request, response);
                return;
            }

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new  AuthenticatoinException(ErrorMessageConstants.JWT_TOKEN_INVALID);
            }
            jwt = authHeader.substring(7);

            final String userId=jwtService.extractUserId(jwt);
            if(userId==null) {
                throw new AuthenticatoinException(ErrorMessageConstants.JWT_TOKEN_INVALID);
            }
            final UserEntity fetchedUser=userRepository.findById(userId).orElseThrow(
                    () -> new AuthenticatoinException(ErrorMessageConstants.USER_NOT_FOUND)
            );

            UsernamePasswordAuthenticationToken token =new UsernamePasswordAuthenticationToken(fetchedUser, null,List.of());
            if(SecurityContextHolder.getContext().getAuthentication()==null) {
                SecurityContextHolder.getContext().setAuthentication(token);
            }
            filterChain.doFilter(request, response);
        }catch (Exception ex){

            if(ex instanceof AuthenticatoinException){
                exceptionResolver.resolveException(request, response, null, ex);
            }else{
                exceptionResolver.resolveException(request, response, null, new AuthenticatoinException(ErrorMessageConstants.SOMETHING_WENT_WRONG));
            }

        }

    }
}
