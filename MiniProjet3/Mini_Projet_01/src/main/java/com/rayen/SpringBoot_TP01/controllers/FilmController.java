package com.rayen.SpringBoot_TP01.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rayen.SpringBoot_TP01.entities.Film;
import com.rayen.SpringBoot_TP01.entities.Genres;
import com.rayen.SpringBoot_TP01.services.FilmService;
import com.rayen.SpringBoot_TP01.services.GenresService;

@Controller
public class FilmController {

	@Autowired
	private FilmService filmService;



	@Autowired
	private GenresService genresService;



	public void listOfGenres(ModelMap modelMap) {
		List<Genres> genres = this.genresService.getGenres();

		modelMap.addAttribute("genres", genres);
	}

	public void paginate(ModelMap modelMap, int page, int size) {
		Page<Film> films = this.filmService.getAllFilmByPage(page, size);
		modelMap.addAttribute("films", films);
		modelMap.addAttribute("pages", new int[films.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
	}


	@GetMapping("/newFilm")
	public String getCreateFilmPage(ModelMap modelMap) {

		this.listOfGenres(modelMap);
		return "/film/create_film";
	}

	@PostMapping("/createFilm")
	public String createFilm(
			ModelMap modelMap,
			@ModelAttribute("film") Film film
			) {

		if (film.getFilmName().equals("") || film.getFilmDesc().equals("")) {
			String error = "All Fields R required while creating a new film !!!";
			modelMap.addAttribute("error", error);

			return "/film/create_film";
		}


		this.filmService.addFilm(film);

		this.paginate(modelMap, 0, 2);
		return "/film/list_films";
	}

	@GetMapping("/films")
	public String filmsPage(
					ModelMap modelMap,
					@RequestParam(name = "page", defaultValue = "0") int page,
					@RequestParam(name = "size", defaultValue = "2") int size) {

		this.paginate(modelMap, page, size);
		return "/film/list_films";
	}

	@GetMapping("/deleteFilm")
	public String deleteFilm(
				ModelMap modelMap,
				@RequestParam("id") Long filmId,
				@RequestParam(name = "page", defaultValue = "0") int page,
				@RequestParam(name = "size", defaultValue = "2") int size ){
		this.filmService.deleteFilmById(filmId);
		this.paginate(modelMap, page, size);

		return "/film/list_films";
	}

	@GetMapping("/editFilm")
	public String getUpdateFilmPage(ModelMap modelMap, @RequestParam("id") Long filmId, @RequestParam("page") int page) {
		Film film = this.filmService.getFilm(filmId);

		Genres currentGenre = film.getGenres();


		this.listOfGenres(modelMap);
		modelMap.addAttribute("film", film);

		modelMap.addAttribute("currentGenre", currentGenre);
		modelMap.addAttribute("currentPage", page);


		return "/film/update_film";
	}

	@PostMapping("/updateFilm")
	public String updateFilm(
			ModelMap modelMap,
			@ModelAttribute("film") Film film,

			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {

		if (film.getFilmName().equals("") || film.getFilmDesc().equals("")) {
			String error = "All The Fields Are Required .";



			modelMap.addAttribute("error", error);
			return "/film/update_film";
		}

		this.filmService.addFilm(film);

		this.paginate(modelMap, page, size);

		return "/film/list_films";
	}


}
