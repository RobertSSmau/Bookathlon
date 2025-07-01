package com.bookathlon.service;

public interface LikeCommentoService {

	long contaLike(Long commentoId);
    boolean haGiaMessoLike(Long commentoId, Long utenteId);
    void mettiLike(Long commentoId, Long utenteId);
    void rimuoviLike(Long commentoId, Long utenteId);
	
}
