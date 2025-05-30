package com.bookathlon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bookathlon.entities.Utente;
import com.bookathlon.repos.UtenteDAO;

//cerca username e password creando un oggetto UserDetails per verificare password e ruolo dal db

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UtenteDAO utenteDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Cerca l'utente nel database usando lo username
        Utente utente = utenteDAO.findByUsername(username);
        if(utente==null) {
        	 throw new UsernameNotFoundException("nessun utente: " + username);
        }
        	 List<GrantedAuthority> ruoli = new ArrayList<>();
             ruoli.add(new SimpleGrantedAuthority("RUOLO_" + utente.getRuolo()));
             
             return new User(utente.getUsername(), utente.getPassword(), ruoli);
        	 
        }
}
	
	
