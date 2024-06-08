package com.todoApp.todoApp.Security.Filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todoApp.todoApp.Model.DTO.RequestObject.CreateUserRequest;
import com.todoApp.todoApp.Security.JWT.JWTService;
import com.todoApp.todoApp.Security.SecurityExceptions.InvalidTokenException;
import com.todoApp.todoApp.Service.Abstract.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Component
@RequiredArgsConstructor
public class CustomOPRFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
HttpServletResponse errorResponse= response;


        if(request.getServletPath().equals("/api/auth/login")|| request.getServletPath().equals("/api/auth/register")){
            filterChain.doFilter(request,response);
        }
        else{ try{

            String authHeader = request.getHeader("Authorization");
            String token = null;
            String email = null;

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                email = jwtService.extractUser(token);


            }

            if (email != null && SecurityContextHolder.getContext().getAuthentication()==null) {
                UserDetails user = userService.findByEmail(email);
                if (jwtService.validateToken(token, user)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    response.setHeader("access_token",jwtService.generateToken(user));

                }
                    }
                 }catch (Exception e){

                     response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                     response.setStatus(409);
                     response.getWriter().format("""
                        {
                          "error":[
                            {
                              "status": %d,
                              "detail": "%s"
                            }
                          ]
                        }
                        """,409,"Invalid Token");

                 }

                filterChain.doFilter(request, response);

            }

    }


}



