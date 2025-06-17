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
	 * Cerca libri nel database filtrando per parola chiave.
	 * La ricerca avviene su titolo e autore usando il confronto case insensitive (ILIKE).
	 * È una query SQL nativa compatibile con PostgreSQL.
	 *
	 * Esempio, se keyword "x", trova tutti i libri il cui titolo o autore contiene "x".
	 *
	 * esempio: keyword La parola chiave da cercare
	 * return: Una lista di libri che corrispondono alla ricerca.
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
