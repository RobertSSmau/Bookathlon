package com.bookathlon.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookathlon.entities.Libro;

@Controller
@RequestMapping("/admin")
public class AdminViewController {

	@GetMapping("/form-libro")
    public String mostraForm(Model model) {
        model.addAttribute("libro", new Libro());
        return "admin-libri";
    }
}
