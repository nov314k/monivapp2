package com.monivapp2.entity;

public class Response {

	private EmbeddedMovies _embedded;
	
	public Response() {

	}
	
	public Response(EmbeddedMovies _embedded) {
		this._embedded = _embedded;
	}

	public EmbeddedMovies get_embedded() {
		return _embedded;
	}

	public void set_embedded(EmbeddedMovies _embedded) {
		this._embedded = _embedded;
	}	
}