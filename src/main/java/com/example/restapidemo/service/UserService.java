package com.example.restapidemo.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.restapidemo.controller.UserController.UserForm;
import com.example.restapidemo.data.entity.User;
import com.example.restapidemo.data.repository.UserRepository;
import com.example.restapidemo.exception.ValidationErrorException;

/**
 * UserService
 *
 * @author Daisuke Wakita
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> list() {
		return userRepository.findByOrderByUpdatedAtDesc();
	}

	public User get(Long id) {
		return userRepository.findById(id).orElseThrow();
	}

	public User create(@Valid UserForm form, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new RuntimeException();
		}

		User user = new User();
		BeanUtils.copyProperties(form, user);

		return userRepository.save(user);
	}

	public User update(Long id, @Valid UserForm form, BindingResult bindingResult) {

		User user = userRepository.findById(id).orElseThrow();

		if (bindingResult.hasErrors()) {
			throw new ValidationErrorException(bindingResult);
		}

		BeanUtils.copyProperties(form, user);

		return userRepository.save(user);
	}

	public void delete(Long id) {
		userRepository.delete(userRepository.findById(id).orElseThrow());
	}

}
