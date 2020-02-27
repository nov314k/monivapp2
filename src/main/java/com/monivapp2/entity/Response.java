package com.monivapp2.entity;

// TODO Change this to an inner class?
public class Response {

	private ResponseEmbedded _embedded;
	
	public Response() {

	}
	
	public Response(ResponseEmbedded _embedded) {
		this._embedded = _embedded;
	}

	public ResponseEmbedded get_embedded() {
		return _embedded;
	}

	public void set_embedded(ResponseEmbedded _embedded) {
		this._embedded = _embedded;
	}	
}