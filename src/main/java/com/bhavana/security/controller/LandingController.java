package com.bhavana.security.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class LandingController {
	
	@GetMapping("/")
	public String GetLandingPage() {
		return new String("Landing Page");
	}
	
}
