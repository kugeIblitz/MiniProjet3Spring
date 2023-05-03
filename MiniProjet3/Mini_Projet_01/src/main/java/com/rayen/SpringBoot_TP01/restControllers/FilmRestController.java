package com.rayen.SpringBoot_TP01.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rayen.SpringBoot_TP01.entities.Film;
import com.rayen.SpringBoot_TP01.services.FilmService;
import com.rayen.SpringBoot_TP01.services.GenresService;


@RequestMapping("/api/films")
@RestController
@CrossOrigin
public class FilmRestController {
	@Autowired
	private FilmService FilmService;



	@Autowired
	private GenresService GenreService;

	@GetMapping
	List<Film> getAllFilms() {
		return this.FilmService.getAllFilms();
	}

	@GetMapping("/{FilmId}")
	Film getFilmById(@PathVariable("FilmId") Long FilmId) {
		return this.FilmService.getFilm(FilmId);
	}



	@GetMapping("/Genre_Films/{GenreId}")
	List<Film> getAllFilmsByGenre(@PathVariable("GenreId") Long GenreId) {
		return this.FilmService.findFilmsByGenreId(GenreId);
	}



	@PatchMapping("/{FilmId}")
	Film updateFilm(@RequestBody Film Film, @PathVariable("FilmId") Long FilmId) {
		Film oldFilmInfo = this.FilmService.getFilm(FilmId);

		Film.setGenre(oldFilmInfo.getGenres());

		Film.setFilmId(FilmId);

		return this.FilmService.updateFilm(Film);
	}

	@DeleteMapping("/{FilmId}")
	void deleteFilm(@PathVariable("FilmId") Long FilmId) {
		this.FilmService.deleteFilmById(FilmId);
	}

}