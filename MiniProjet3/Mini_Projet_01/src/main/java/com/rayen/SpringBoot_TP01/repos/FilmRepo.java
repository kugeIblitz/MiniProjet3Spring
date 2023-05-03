package com.rayen.SpringBoot_TP01.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.rayen.SpringBoot_TP01.entities.Film;



@RepositoryRestResource(path = "rest")
public interface FilmRepo extends JpaRepository<Film, Long> {
	List<Film>findByfilmName(String nom);

}
