package com.example.restapidemo.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * LoginForm
 * 
 * ログインフォーム
 *
 * @author Daisuke Wakita
 */
@Data
public class LoginForm {

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(min = 8)
	private String password;

}
