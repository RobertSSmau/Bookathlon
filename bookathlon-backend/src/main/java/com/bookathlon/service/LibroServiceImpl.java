package com.bookathlon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookathlon.entities.Libro;
import com.bookathlon.repos.LibroDAO;

@Service
public class LibroServiceImpl implements LibroService {
	@Autowired
	private LibroDAO dao;
	
	@Override
	public List<Libro> getLibri() {
		
		return dao.findAll();
	}

	@Override
	public Libro getLibroById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Libro addLibro(Libro l) {
		// TODO Auto-generated method stub
		return dao.save(l);
	}

}
