package com.monivapp2.exception;

public class MovieNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MovieNotFoundException(int movieId) {
		
		super("Could not find movie id=" + movieId);
	}
}