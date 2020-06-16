package com.example.restapidemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * IndexController
 *
 * @author Daisuke Wakita
 */
@RestController
public class IndexController {

	@GetMapping("/")
	public void index() {
	}

}
