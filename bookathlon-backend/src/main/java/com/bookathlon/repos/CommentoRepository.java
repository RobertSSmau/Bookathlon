package com.bookathlon.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookathlon.entities.Commento;

public interface CommentoRepository extends JpaRepository<Commento, Long> {

	@Query(value = """
	        SELECT * FROM "better-mockup-schema-2".commento
	        WHERE libro_id = :libroId
	        ORDER BY data_creazione DESC
	    """, nativeQuery = true)
	    List<Commento> trovaPerLibro(@Param("libroId") Long libroId);
	
}
