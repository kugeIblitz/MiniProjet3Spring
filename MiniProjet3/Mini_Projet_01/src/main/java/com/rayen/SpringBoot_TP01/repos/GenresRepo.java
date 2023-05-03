package com.rayen.SpringBoot_TP01.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rayen.SpringBoot_TP01.entities.Genres;

public interface GenresRepo extends JpaRepository<Genres, Long> {

}
