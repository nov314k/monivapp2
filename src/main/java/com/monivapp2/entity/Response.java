package com.monivapp2.entity;

public class Response {

	private EmbeddedResponse _embedded;
	
	public Response() {

	}
	
	public Response(EmbeddedResponse _embedded) {
		this._embedded = _embedded;
	}

	public EmbeddedResponse get_embedded() {
		return _embedded;
	}

	public void set_embedded(EmbeddedResponse _embedded) {
		this._embedded = _embedded;
	}	
}