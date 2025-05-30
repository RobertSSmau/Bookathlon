package com.bookathlon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookathlon.entities.Libro;
import com.bookathlon.service.LibroService;

@Controller
public class HomeController {

	@Autowired
	private LibroService libroService;
	
	@GetMapping("/")
	public String homePage(Model m) {
		List<Libro> libri = libroService.getLibri();
        m.addAttribute("libri", libri);
        return "home"; 
	}
	
}
