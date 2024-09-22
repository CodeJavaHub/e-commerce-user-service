package com.ecom.userservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ecom.userservice.entity.User;
import com.ecom.userservice.repository.UserRepository;
import com.ecom.userservice.service.impl.UserServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceMockTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserServiceImpl userService;

  @Test
  void testGetUserByIdValidId() {
      User user = new User();
      user.setFirstName("Sunil");
      user.setLastName("Bombe");
      user.setId(1L);
      user.setGender("Male");
      user.setRole("ADMIN");
      user.setEmail("sunil.bombe@gmail.com");
      user.setIsLocked(false);
      user.setIsEnable(true);
      user.setPhone("8605705665");
  //  UserRepository userRepositoryMock = mock(UserRepository.class);
    when(userRepository.findById(1L)).thenReturn(Optional.of(user));
  //  UserService userService = new UserServiceImpl(userRepositoryMock);
    assertEquals(user.getEmail(), userService.getUserById(1L).getEmail());
  }


}
