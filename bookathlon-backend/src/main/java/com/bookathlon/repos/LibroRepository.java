package com.bookathlon.repos;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookathlon.entities.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>{

	/**
	 * Questo metodo cerca libri per parola chiave nel titolo o nell'autore, usando una query SQL nativa case-insensitive. 
	 * Ritorna una lista di libri corrispondenti alla ricerca.
	 */
	@Query(
		    value = """
		        SELECT *
		        FROM "better-mockup-schema-2".libro
		        WHERE titolo ILIKE CONCAT('%', :kw, '%')
		           OR autore ILIKE CONCAT('%', :kw, '%')
		    """,
		    nativeQuery = true
		)
	List<Libro> ricercaSQL(@Param("kw") String keyword);
	
	@Query(value = """
		    SELECT l.* 
		    FROM "better-mockup-schema-2".libreria_utente lu
		    JOIN "better-mockup-schema-2".libro l ON lu.libro_id = l.id
		    GROUP BY l.id, l.isbn, l.titolo, l.autore, l.genere, l.data_pubblicazione, l.url_copertina
		    ORDER BY COUNT(*) DESC
		    LIMIT 10
		""", nativeQuery = true)
	List<Libro> trovaLibriPopolari();
	
}
