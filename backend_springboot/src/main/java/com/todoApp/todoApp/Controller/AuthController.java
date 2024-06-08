package com.todoApp.todoApp.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todoApp.todoApp.Model.DTO.RequestObject.Authrequest;
import com.todoApp.todoApp.Model.DTO.RequestObject.ChangePasswordRequest;
import com.todoApp.todoApp.Model.DTO.RequestObject.CreateUserRequest;
import com.todoApp.todoApp.Model.Entity.User;
import com.todoApp.todoApp.Model.Enum.Role;
import com.todoApp.todoApp.Security.JWT.JWTService;
import com.todoApp.todoApp.Service.Abstract.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/auth")
public class AuthController {
    private final UserService userService;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper mapper;

    @PostMapping(value = "/register",consumes= MediaType.APPLICATION_JSON_VALUE,produces = "application/json")
    public ResponseEntity<Object> register(@RequestBody CreateUserRequest request) {
        Map<String,String> response=new HashMap<>();
        try{



         userService.createUser(request);
        response.put("success","Succesfully created user in db.");
         return new ResponseEntity<>(response,HttpStatus.OK);
     }catch(Exception e){
        response.put("error",e.getMessage());

         return  new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);


     }
    }
    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response login(@RequestBody Authrequest authrequest)  {

        return new Response();
     }

     @PutMapping("/changepassword")
    public  ResponseEntity<Object> changePassword(@RequestBody ChangePasswordRequest request) {
       boolean isSuccess= userService.changePassword(request.oldPassword(), request.newPassword());
         Map<String,String> response=new HashMap<>();
       if(isSuccess){
           return new ResponseEntity<>(HttpStatus.OK);
       }else{
           response.put("error","Current password is wrong.");
           return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
       }

     }


}
