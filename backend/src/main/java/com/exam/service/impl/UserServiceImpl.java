package com.exam.service.impl;

import com.exam.helper.UserFoundException;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repository.RoleRepository;
import com.exam.repository.UserRepository;
import com.exam.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class UserServiceImpl implements UserService{
    private static final Logger LOGGER= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    //creating user
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

   User local= userRepository.findByUsername(user.getUsername());
   if (local!=null){
       LOGGER.info("User is already there !!");
       throw new UserFoundException();
   }else{
       for (UserRole ur:userRoles){

           roleRepository.save(ur.getRole());
       }
        user.getUserRoles().addAll(userRoles);
      local = userRepository.save(user);
   }
   return local;
    }
    //get username by userName
    @Override
    public User getUserByUserName(String username){
    return userRepository.findByUsername(username);
    }
    @Override
    public void deleteUserById(Long userId) {
    userRepository.deleteById(userId);
    }
}