package com.bookathlon.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookathlon.entities.Libro;

@Repository
public interface LibroDAO extends JpaRepository<Libro, Integer>{

	
	//Query JPQL per i libri di tendenza in homepage
	@Query(value = """
		    SELECT l.* FROM libri l
		    JOIN library_books lb ON lb.libro_id = l.id
		    WHERE lb.status = 'letto'
		    GROUP BY l.id
		    ORDER BY COUNT(*) DESC
		    LIMIT 5
		""", nativeQuery = true)
		List<Libro> findLibriDiTendenza();
	
}
