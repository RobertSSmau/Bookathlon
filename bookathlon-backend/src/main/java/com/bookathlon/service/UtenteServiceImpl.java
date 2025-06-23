package com.bookathlon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookathlon.entities.Utente;
import com.bookathlon.repos.UtenteRepository;

@Service
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    private UtenteRepository dao;
    
    @Autowired
    private PasswordEncoder passwordEncoder;	
    @Override
    public List<Utente> getUtenti() {
        return dao
        		.findAll();
    }

    @Override
    public Utente getUtenteById(Long id) {
        return dao.findById(id)
        		.orElse(null);
    }

    @Override
    public Utente addUtente(Utente u) {
        u.setPassword(passwordEncoder.encode(u.getPassword()));
    	return dao
        		.save(u);
    }

	@Override
	public boolean existsByEmail(String email) {
		return dao.findByEmail(email) != null;
	}

	@Override
	public boolean existsByUsername(String username) {
		return dao.findByUsername(username) != null;
	}
}