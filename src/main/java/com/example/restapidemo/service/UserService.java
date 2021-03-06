package com.example.restapidemo.service;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.restapidemo.common.SessionNames;
import com.example.restapidemo.controller.form.LoginForm;
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

	@Autowired
	private PasswordEncoder passwordEncoder;

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

		// パスワードはエンコードする
		user.setPassword(passwordEncoder.encode(form.getPassword()));

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

		// パスワードはエンコードする
		user.setPassword(passwordEncoder.encode(form.getPassword()));

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

	/**
	 * ログイン処理
	 * 
	 * @param form
	 * @param bindingResult
	 * @param session
	 * @return
	 */
	public User login(LoginForm form, BindingResult bindingResult, HttpSession session) {

		// 入力チェック
		if (bindingResult.hasErrors()) {
			throw new ValidationErrorException(bindingResult);
		}

		// ユーザー取得
		User user = userRepository.findByEmail(form.getEmail());
		if (user == null) {
			// 存在しないメアド
			bindingResult.rejectValue("email", "error.login");
			throw new ValidationErrorException(bindingResult);
		}

		// パスワードチェック
		if (!passwordEncoder.matches(form.getPassword(), user.getPassword())) {
			// パスワード間違い
			bindingResult.rejectValue("email", "error.login");
			throw new ValidationErrorException(bindingResult);
		}

		// セッションにセット
		session.setAttribute(SessionNames.LOGIN_USER.name(), user);

		return user;
	}

}
