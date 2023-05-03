package com.rayen.SpringBoot_TP01.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.rayen.SpringBoot_TP01.entities.Film;

public interface FilmService {
	Film addFilm(Film film);
	Film updateFilm(Film film);
	Film getFilm(Long filmId);
	List<Film> getAllFilms();
	Page<Film> getAllFilmByPage(int page, int size);
	List<Film> findFilmsByGenreId(Long GenreId);
	void deleteFilmById(Long filmId);
	void deleteFilm(Film film);
}
