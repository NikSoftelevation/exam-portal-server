package com.exam.controller;

import com.exam.helper.UserFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController{
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @PostMapping("/post")
    public ResponseEntity<User> createUser(@RequestBody User user) throws Exception{

        Set<UserRole> userRoles=new HashSet<>();

        Role role=new Role();
        role.setRoleName("NORMAL");

        //encoding password with BCryptPasswordEncoder
        user.setPassword(passwordEncoder.encode(user.getPassword()));

      UserRole userRole=new UserRole();
      userRole.setUser(user);
      userRole.setRole(role);

        userRoles.add(userRole);

    User createdUser = userService.createUser(user,userRoles);
    return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
    }
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUserName(@PathVariable("username") String username){
        return new ResponseEntity<>(userService.getUserByUserName(username),HttpStatus.OK);
    }
    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable("userId") Long userId){
       userService.deleteUserById(userId);
    }
    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex){
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }
}