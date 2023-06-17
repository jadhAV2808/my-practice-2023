package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/public")
	public String home() {
		return "this is public! ";
	}
	
	@GetMapping("/user")
	public String user() {
		return "this is for user only! ";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "this is for admin only! ";
	}

}
