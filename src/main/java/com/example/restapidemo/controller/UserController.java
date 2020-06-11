package com.example.restapidemo.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapidemo.data.entity.User;
import com.example.restapidemo.service.UserService;

import lombok.Data;

/**
 * UserController
 *
 * @author Daisuke Wakita
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * ユーザーリスト
	 * 
	 * @return
	 */
	@GetMapping("/users")
	public List<User> list() {
		return userService.list();
	}

	/**
	 * ユーザー取得
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/users/{id}")
	public User update(@PathVariable("id") Long id) {
		return userService.get(id);
	}

	/**
	 * ユーザー作成
	 * 
	 * @param user
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/users")
	public User create(@RequestBody @Valid UserForm form, BindingResult bindingResult) {
		return userService.create(form, bindingResult);
	}

	/**
	 * ユーザー更新
	 * 
	 * @param id
	 * @param user
	 * @param bindingResult
	 * @return
	 */
	@PutMapping("/users/{id}")
	public User update(@PathVariable("id") Long id, @RequestBody @Valid UserForm form, BindingResult bindingResult) {

		return userService.update(id, form, bindingResult);

	}

	/**
	 * ユーザー削除
	 * 
	 * @param id
	 */
	@DeleteMapping("/users/{id}")
	public void delete(@PathVariable("id") Long id) {
		userService.delete(id);
	}

	/**
	 * UserForm
	 * 
	 * ユーザーフォーム
	 *
	 * @author Daisuke Wakita
	 */
	@Data
	public static class UserForm {

		@NotBlank
		private String email;

		@NotBlank
		private String password;

	}

}
