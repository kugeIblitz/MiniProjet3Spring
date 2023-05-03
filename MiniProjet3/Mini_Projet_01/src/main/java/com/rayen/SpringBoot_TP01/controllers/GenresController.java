package com.rayen.SpringBoot_TP01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rayen.SpringBoot_TP01.entities.Genres;
import com.rayen.SpringBoot_TP01.services.GenresService;

@Controller
public class GenresController {
	@Autowired
	private GenresService genresService;

	public void paginate(ModelMap modelMap, int page, int size) {
		Page<Genres> genres = this.genresService.getGenresByPage(page, size);
		modelMap.addAttribute("genres", genres);
		modelMap.addAttribute("pages", new int[genres.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
	}

	@GetMapping("/newGenres")
	public String getCreateGenresPage(ModelMap modelMap) {

		return "Genres/create_genres";
	}

	@PostMapping("/createGenres")
	public String createGenres(
			ModelMap modelMap,
			@ModelAttribute("genres") Genres genres,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size
			) {

		if (genres.getGenreName().equals("")) {
			String error = "All Fields Are Required";
			modelMap.addAttribute("error", error);
			return "Genres/create_genres";
		}

		this.genresService.createGenre(genres);
		this.paginate(modelMap, page, size);

		return "Genres/list_genres";
	}

	@GetMapping("/genres")
	public String getGenresPage(
			ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size
			) {
		this.paginate(modelMap, page, size);
		return "Genres/list_genres";
	}

	@GetMapping("deleteGenres")
	public String deleteGenres(
			ModelMap modelMap,
			@RequestParam("id") Long genresId,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		this.genresService.deleteGenreById(genresId);
		this.paginate(modelMap, page, size);
		return "Genres/list_genres";
	}

	@GetMapping("/editGenres")
	public String getEditGenresPage(ModelMap modelMap, @RequestParam("id") Long genreId, @RequestParam("page") int page) {
		Genres genres = this.genresService.getGenreById(genreId);
		modelMap.addAttribute("genre", genres);
		modelMap.addAttribute("currentPage", page);
		return "Genres/update_genres";
	}

	@PostMapping("/updateGenres")
	public String updateGenres(
			ModelMap modelMap,
			@ModelAttribute("genres") Genres genres,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size
			) {

		if (genres.getGenreName().equals("")) {
			String error = "All Fields Are Required .";
			modelMap.addAttribute("error", error);
			return "Genres/update_genres";
		}

		this.genresService.createGenre(genres);
		this.paginate(modelMap, page, size);

		return "Genres/list_genres";
	}
}
