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
	 * Esempio, se keyword "ciccio", trova tutti i libri il cui titolo o autore contiene "ciccio".
	 *
	 * @param keyword La parola chiave da cercare
	 * @return Una lista di libri che corrispondono alla ricerca.
	 */
	@Query(
	    value = """
	        SELECT * 
	        FROM libro 
	        WHERE titolo ILIKE CONCAT('%', :kw, '%') 
	           OR autore ILIKE CONCAT('%', :kw, '%')
	    """,
	    nativeQuery = true
	)
	List<Libro> ricercaSQL(@Param("kw") String keyword);

	
}
