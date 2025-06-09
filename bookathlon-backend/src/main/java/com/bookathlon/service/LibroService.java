package com.bookathlon.service;

import java.util.List;

import com.bookathlon.entities.Libro;

public interface LibroService {

	
	List<Libro> getLibri();
	Libro getLibroById(Long id);
	Libro addLibro(Libro l);
	List<Libro> getLibriDiTendenza();
	List<Libro> cerca(String keyword);
	
}
