package com.nidhi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping({"/status", "/health"})
public class HomeController {

	@GetMapping
	public String checkHealth() {
		return "Application is runing..";
	}
	
	@PostConstruct
	public void init() {
	    System.out.println("✅ HomeController loaded successfully");
	}
}

