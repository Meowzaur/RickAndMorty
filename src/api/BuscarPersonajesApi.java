package api;

import java.io.IOException;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import apimodels.RespuestaAPI;
import models.Personaje;
import utils.Listas;

public class BuscarPersonajesApi {

	/*
	 * Se encarga de buscar todos los personajes de la serie Rick and Morty y
	 * agregarlos a una lista dentro del programa.
	 */
	public void agregarPersonajesApi() {

		OkHttpClient client = new OkHttpClient();

		// Recorre este bucle 42 veces porque son las páginas máximas actuales que
		// contienen personajes.
		for (int i = 1; i <= 42; i++) {
			Request request = new Request.Builder().url("https://rickandmortyapi.com/api/character/?page=" + i).get()
					.addHeader("accept", "application/json")
					.addHeader("Authorization",
							"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMTgzOTQ0MzA5MzA4NGZiM2ZmODQ2ZTM3ZDZjZWJhOCIsInN1YiI6IjY1N2MyZmNmOGUyYmE2MDBhYzhkOGUwYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Lp4NwmXe6l66onGG-28eyCrOO6LOSxqBrzY32ocdDCA")
					.build();

			try {
				// Se encarga de almacenar la respuesta en un string.
				Response response = client.newCall(request).execute();
				String respuesta = response.body().string();

				// Se guarda el string en un Json para poder trabajar con él.
				Gson gson = new Gson();
				RespuestaAPI respAPI = gson.fromJson(respuesta, RespuestaAPI.class);

				// Se agregan a la lista de personajes
				for (Personaje p : respAPI.getResults()) {
					Listas.listaPersonajes.add(p);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}