package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.domain.User;
import com.stackroute.authenticationservice.exception.UserNotFoundException;
import com.stackroute.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user)
    {
        return userRepository.save(user);
    }

  /*@Override
  public String getUser(User user)
  {
    return userRepository.findAll().toString();
  }*/

    @Override
    public User findByUsernameAndPassword(String username, String password) throws UserNotFoundException {

        User user = userRepository.findByUsernameAndPassword(username, password);

        if(user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }
}
