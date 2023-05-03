package com.rayen.SpringBoot_TP01.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.rayen.SpringBoot_TP01.entities.Genres;

public interface GenresService {
	Genres createGenre(Genres genres);
	Genres updateGenre(Genres genres);
	Genres getGenreById(Long id);
	List<Genres> getGenres();
	Page<Genres> getGenresByPage(int page, int size);
	void deleteGenre(Genres genres);
	void deleteGenreById(Long id);
}
