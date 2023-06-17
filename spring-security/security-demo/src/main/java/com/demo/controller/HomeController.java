package com.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	@GetMapping("/public")
	public String home() {
		return "this is public! ";
	}
	
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping("/user")
	public String user() {
		return "this is for user only! ";
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/admin")
	public String admin() {
		return "this is for admin only! ";
	}

}
