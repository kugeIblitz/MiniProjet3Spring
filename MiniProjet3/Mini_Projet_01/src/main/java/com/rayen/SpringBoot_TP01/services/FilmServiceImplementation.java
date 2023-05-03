package com.rayen.SpringBoot_TP01.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.rayen.SpringBoot_TP01.entities.Film;
import com.rayen.SpringBoot_TP01.repos.FilmRepo;

@Service
public class FilmServiceImplementation implements FilmService{

	@Autowired
	private FilmRepo filmRepo;

	@Override
	public Film addFilm(Film film) {
		// TODO Auto-generated method stub
		return this.filmRepo.save(film);
	}

	@Override
	public Film updateFilm(Film film) {
		// TODO Auto-generated method stub
		return this.filmRepo.save(film);
	}

	@Override
	public Film getFilm(Long filmId) {
		// TODO Auto-generated method stub
		return this.filmRepo.findById(filmId).get();
	}

	@Override
	public List<Film> getAllFilms() {
		// TODO Auto-generated method stub
		return this.filmRepo.findAll();
	}

	@Override
	public Page<Film> getAllFilmByPage(int page, int size) {
		// TODO Auto-generated method stub
		return this.filmRepo.findAll(PageRequest.of(page, size));
	}

	@Override
	public void deleteFilmById(Long filmId) {
		// TODO Auto-generated method stub
		this.filmRepo.deleteById(filmId);
	}

	@Override
	public void deleteFilm(Film film) {
		// TODO Auto-generated method stub
		this.filmRepo.delete(film);
	}

	@Override
	public List<Film> findFilmsByGenreId(Long GenreId) {
		// TODO Auto-generated method stub
		return null;
	}

}
