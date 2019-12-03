package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.domain.User;
import com.stackroute.authenticationservice.exception.UserNotFoundException;
import com.stackroute.authenticationservice.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.mockito.Mockito.times;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private User user;

    @InjectMocks
    private UserServiceImpl userService;

    Optional optional;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks((this));
        user = new User();
        user.setUsername("John");
        user.setPassword("John123");
    }

    @Test
    public void testSaveUserSuccess() {
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User fetchedUser = userService.saveUser(user);
        Assert.assertEquals(user, fetchedUser);
        verify(userRepository, times( 1)).save(user);
    }

    @Test
    public void testFindByUsernameAndPassword() throws UserNotFoundException{
        Mockito.when(userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())).thenReturn(user);
        User fetchedUser = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        Assert.assertEquals(user.getUsername(), fetchedUser.getUsername());
        verify(userRepository,times(1)).findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
