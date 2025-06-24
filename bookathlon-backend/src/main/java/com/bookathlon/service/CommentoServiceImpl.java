package com.bookathlon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookathlon.entities.Commento;
import com.bookathlon.repos.CommentoRepository;

@Service
public class CommentoServiceImpl implements CommentoService {

	@Autowired
	private CommentoRepository commentoRepo;

	@Override
    public Commento salva(Commento commento) {
        return commentoRepo.save(commento);
    }
	
	@Override
    public List<Commento> trovaPerLibro(Long libroId) {
        return commentoRepo.trovaPerLibro(libroId);
    }
	
}
