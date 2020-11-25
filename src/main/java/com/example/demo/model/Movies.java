package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Movies {
	
	@Id
	private int movieId;
	private String title;
	private String genres;
	private Date date;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenres() {
		return genres;
	}
	public void setGenres(String genres) {
		this.genres = genres;
	}
	public Movies(int movieId, String title, String genres,Date date) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.genres = genres;
		this.date = date;
	}
	public Movies() {
		
	}
	
	
}
