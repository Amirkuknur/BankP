package com.example.Testing;

import com.example.Testing.Entiity.UserEntity;
import com.example.Testing.Repository.UserRepository;
import com.example.Testing.Service.Appservice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TestingApplicationTests {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private Appservice appservice;

	@Test
	public void testCreateAccountSuccess() {

		UserEntity user = new UserEntity();
		user.setId(1);
		user.setUsername("John");


		when(userRepository.save(user)).thenReturn(user);

		ResponseEntity<String> response = appservice.createAccount(user);


		assertEquals(200, response.getStatusCodeValue());
		assertEquals("Account Created", response.getBody());
		verify(userRepository, times(1)).save(user);
	}
	@Test
	public void testCreateAccountFailure() {

		UserEntity user = new UserEntity();
		user.setId(1);
		user.setUsername("John");


		when(userRepository.save(user)).thenThrow(new RuntimeException("DB error"));

		ResponseEntity<String> response = appservice.createAccount(user);


		assertEquals(500, response.getStatusCodeValue());
		assertEquals("Sorry", response.getBody());
	}


}
