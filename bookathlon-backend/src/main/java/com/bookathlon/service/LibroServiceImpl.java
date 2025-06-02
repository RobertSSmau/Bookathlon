package com.bookathlon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookathlon.entities.Libro;
import com.bookathlon.repos.LibroRepository;

@Service
public class LibroServiceImpl implements LibroService {
	@Autowired
	private LibroRepository repo;
	
	@Override
	public List<Libro> getLibri() {
		
		return repo.findAll();
	}

	@Override
	public Libro getLibroById(int id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Libro addLibro(Libro l) {
		return repo.save(l);
	}

	@Override
	public List<Libro> getLibriDiTendenza() {
		List<Libro> tutti = repo.findAll();
	    return tutti.stream().limit(5).toList();
	}
	
}
