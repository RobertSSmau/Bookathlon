package com.bookathlon.controller;

import com.bookathlon.entities.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
     @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

     @PostMapping("/login")
    public String processLogin(@ModelAttribute LoginForm loginForm, Model model) {
        String user = loginForm.getUsername();
        String pass = loginForm.getPassword();

        if ("admin".equals(user) && "1234".equals(pass)) {
            model.addAttribute("username", user);
            return "welcome";
        } else {
            model.addAttribute("error", "Credenziali non valide");
            return "login";
        }
    }

}
