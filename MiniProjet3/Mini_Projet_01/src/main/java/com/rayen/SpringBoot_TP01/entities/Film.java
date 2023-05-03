package com.rayen.SpringBoot_TP01.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long filmId;
	private String filmName;
	private String filmDesc;

	@ManyToOne
	private Genres genres;



	public Film() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Film(String filmName, String filmDesc) {
		super();
		this.filmName = filmName;
		this.filmDesc = filmDesc;
	}

	public Long getFilmId() {
		return filmId;
	}

	public void setFilmId(Long filmId) {
		this.filmId = filmId;
	}

	public String getFilmName() {
		return filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	public String getFilmDesc() {
		return filmDesc;
	}

	public void setFilmDesc(String filmDesc) {
		this.filmDesc = filmDesc;
	}







	public Genres getGenres() {
		return genres;
	}

	public void setGenre(Genres genres) {
		this.genres = genres;
	}

	@Override
	public String toString() {
		return "Film [filmId=" + filmId + ", filmName=" + filmName + ", filmDesc=" + filmDesc + "]";
	}
}
