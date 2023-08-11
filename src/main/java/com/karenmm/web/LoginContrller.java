package com.karenmm.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginContrller {
	//2023-08-11
	@GetMapping("/login")
	public String index() {
		return "login";
	}
	

}
