package com.bookathlon.service;

import java.util.List;

import com.bookathlon.entities.Libro;

public interface LibroService {

	
	List<Libro> getLibri();
	Libro getLibroById(int id);
	Libro addLibro(Libro l);
	
	
}
