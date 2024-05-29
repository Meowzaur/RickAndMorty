package apimodels;

import models.Personaje;

/*
 * Modelo principal que se encarga de crear los datos que contiene la API
 */
public class RespuestaAPI {

	// Atributos
	private InfoAPI info;
	private Personaje[] results;

	// Constructor
	public RespuestaAPI(InfoAPI info, Personaje[] results) {
		this.info = info;
		this.results = results;
	}

	// Getters
	public InfoAPI getInfo() {
		return info;
	}

	public Personaje[] getResults() {
		return results;
	}

}
