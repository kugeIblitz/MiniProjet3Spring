package com.rayen.SpringBoot_TP01.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.rayen.SpringBoot_TP01.entities.Genres;
import com.rayen.SpringBoot_TP01.repos.GenresRepo;

@Service
public class GenresServiceImplementation implements GenresService {

	@Autowired
	private GenresRepo genresRepo;

	@Override
	public Genres createGenre(Genres genres) {
		// TODO Auto-generated method stub
		return this.genresRepo.save(genres);
	}

	@Override
	public Genres updateGenre(Genres genres) {
		// TODO Auto-generated method stub
		return this.genresRepo.save(genres);
	}

	@Override
	public Genres getGenreById(Long id) {
		// TODO Auto-generated method stub
		return this.genresRepo.findById(id).get();
	}

	@Override
	public List<Genres> getGenres() {
		// TODO Auto-generated method stub
		return this.genresRepo.findAll();
	}

	@Override
	public Page<Genres> getGenresByPage(int page, int size) {
		// TODO Auto-generated method stub
		return this.genresRepo.findAll(PageRequest.of(page, size));
	}

	@Override
	public void deleteGenre(Genres genres) {
		// TODO Auto-generated method stub
		this.genresRepo.delete(genres);
	}

	@Override
	public void deleteGenreById(Long id) {
		// TODO Auto-generated method stub
		this.genresRepo.deleteById(id);
	}

}
