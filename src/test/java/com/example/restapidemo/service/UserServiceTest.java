package com.example.restapidemo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.restapidemo.data.mapper.UserMapper;
import com.example.restapidemo.data.mapper.UserSearchCondition;
import com.example.restapidemo.data.repository.UserRepository;

@AutoConfigureMockMvc
@SpringBootTest
class UserServiceTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Test
	void ユーザーリスト() throws Exception {

		assertNotNull(userRepository.findByOrderByUpdatedAtDesc());
		assertNotNull(userService.list());

		// this.mockMvc.perform(get("/users")).andExpect(status().isOk());

		UserSearchCondition condition = new UserSearchCondition();
		assertNotNull(userMapper.search(condition));

		condition.setEmail("daisuke");

		assertNotNull(userMapper.search(condition));
	}

	@Test
	void ユーザー取得() throws Exception {
//		this.mockMvc.perform(get("/users/1")).andExpect(status().isOk());
//		this.mockMvc.perform(get("/users/2")).andExpect(status().isOk());
//		this.mockMvc.perform(get("/users/3")).andExpect(status().isOk());
//		this.mockMvc.perform(get("/users/4")).andExpect(status().isOk());
//		this.mockMvc.perform(get("/users/5")).andExpect(status().isOk());
	}

}
