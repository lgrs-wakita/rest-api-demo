package com.example.restapidemo.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapidemo.controller.form.LoginForm;
import com.example.restapidemo.data.entity.User;
import com.example.restapidemo.service.UserService;

/**
 * LoginController
 *
 * @author Daisuke Wakita
 */
@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	/**
	 * ログイン
	 * 
	 * @param form
	 * @param bindingResult
	 * @param session
	 * @return
	 */
	@PostMapping
	public User login(@RequestBody @Valid LoginForm form, BindingResult bindingResult, HttpSession session) {
		return userService.login(form, bindingResult, session);
	}

}
