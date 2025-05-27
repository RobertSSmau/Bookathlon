package com.bookathlon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookathlon.service.LibroService;

@Controller
public class LibroMVC {

	private LibroService service;
	
	@GetMapping("libri")
	public String getLibri(Model m) {
		
		return null;
	}
	
}
