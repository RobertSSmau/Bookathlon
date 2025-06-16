package com.bookathlon.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookathlon.entities.Libro;
import com.bookathlon.service.LibroService;

@Controller
@RequestMapping("/admin/libri")
public class AdminLibroController {

    @Autowired
    private LibroService libroService;

    @PostMapping("/aggiungi")
    public String aggiungiLibro(@ModelAttribute Libro libro) {
        libro.setId(null);
        libroService.addLibro(libro);
        return "redirect:/admin/form-libro";
    }
}
