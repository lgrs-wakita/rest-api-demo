package com.example.restapidemo.controller.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * UserForm
 * 
 * ユーザーフォーム
 *
 * @author Daisuke Wakita
 */
@Data
public class UserForm {

	@NotBlank
	private String email;

	@NotBlank
	private String password;

}
