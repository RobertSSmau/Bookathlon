package com.bookathlon.service;

import java.util.List;

import com.bookathlon.entities.Commento;

public interface CommentoService {

	  Commento salva(Commento commento);
	  List<Commento> trovaPerLibro(Long libroId);
	
}
