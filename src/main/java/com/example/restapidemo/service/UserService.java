package com.example.restapidemo.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.restapidemo.controller.form.UserForm;
import com.example.restapidemo.data.entity.User;
import com.example.restapidemo.data.repository.UserRepository;
import com.example.restapidemo.exception.ValidationErrorException;

/**
 * UserService
 * 
 * ユーザー関連のサービス
 *
 * @author Daisuke Wakita
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * ユーザーリストを取得
	 * 
	 * @return
	 */
	public List<User> list() {
		return userRepository.findByOrderByUpdatedAtDesc();
	}

	/**
	 * ユーザーを1件取得
	 * 
	 * @param id
	 * @return
	 */
	public User get(Long id) {
		return userRepository.findById(id).orElseThrow();
	}

	/**
	 * ユーザー作成
	 * 
	 * @param form
	 * @param bindingResult
	 * @return
	 */
	public User create(@Valid UserForm form, BindingResult bindingResult) {

		// 入力エラーをチェック
		if (bindingResult.hasErrors()) {
			throw new ValidationErrorException(bindingResult);
		}

		// UserFormをUserにコピー
		User user = new User();
		BeanUtils.copyProperties(form, user);

		// 作成
		userRepository.save(user);

		return userRepository.getOne(user.getId());
	}

	/**
	 * ユーザーを更新
	 * 
	 * @param id
	 * @param form
	 * @param bindingResult
	 * @return
	 */
	public User update(Long id, @Valid UserForm form, BindingResult bindingResult) {

		// ユーザー取得
		User user = userRepository.findById(id).orElseThrow();

		// 入力エラーをチェック
		if (bindingResult.hasErrors()) {
			throw new ValidationErrorException(bindingResult);
		}

		// UserFormをUserにコピー
		BeanUtils.copyProperties(form, user);

		// 更新
		userRepository.save(user);

		return userRepository.getOne(user.getId());
	}

	/**
	 * ユーザー削除
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		// 削除。なかったら例外スロー。
		userRepository.delete(userRepository.findById(id).orElseThrow());
	}

}
