package apimodels;

import models.Personaje;

public class RespuestaAPI {

	private InfoAPI info;
	private Personaje[] results;
	
	public RespuestaAPI(InfoAPI info, Personaje[] results) {
		this.info = info;
		this.results = results;
	}

	public InfoAPI getInfo() {
		return info;
	}

	public Personaje[] getResults() {
		return results;
	}

}
