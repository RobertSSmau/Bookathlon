package com.bookathlon.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookathlon.entities.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>{

	//Libro findById(Long libroId);

	
}
