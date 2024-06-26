package com.todoApp.todoApp.Service.Concrete;

import com.todoApp.todoApp.Model.DTO.RequestObject.CreateUserRequest;
import com.todoApp.todoApp.Model.Entity.User;
import com.todoApp.todoApp.Model.Enum.Role;
import com.todoApp.todoApp.Repository.UserRepository;
import com.todoApp.todoApp.Security.JWT.JWTService;
import com.todoApp.todoApp.Service.Abstract.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service @RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return findByEmail(email);
    }

    @Override
    public User createUser(CreateUserRequest request) {
        if(userRepository.findByEmail(request.email()).isEmpty()){
            User user=new User();
            user.setUsername(request.username());
            user.setEmail(request.email());
            user.setPassword(passwordEncoder.encode(request.password()));
            Set<Role> auths=new HashSet<>();
            auths.add(Role.ROLE_USER);
            user.setAuthorities(auths);
            userRepository.save(user);
            return user;
        }else{
            throw new EntityExistsException("User already exists with this email.");
        }


    }

    @Override
    public User findByEmail(String email) {
        Optional<User> user=userRepository.findByEmail(email);

        return user.orElse(null);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword) {
        if(passwordEncoder.matches(oldPassword,getUser().getPassword())){
            getUser().setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(getUser());
            return true;
        }else{
            return false;
        }


    }
    private User getUser(){
        Optional<User> user= userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if(user.isPresent()){
            return user.get();
        }else{
            throw new UsernameNotFoundException("User not found, todo cant be added.");
        }
    }
}
