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

	public void agregarPersonajesApi() {

		OkHttpClient client = new OkHttpClient();

		for (int i = 1; i <= 42; i++) {
			Request request = new Request.Builder().url("https://rickandmortyapi.com/api/character/?page=" + i).get()
					.addHeader("accept", "application/json")
					.addHeader("Authorization",
							"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMTgzOTQ0MzA5MzA4NGZiM2ZmODQ2ZTM3ZDZjZWJhOCIsInN1YiI6IjY1N2MyZmNmOGUyYmE2MDBhYzhkOGUwYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Lp4NwmXe6l66onGG-28eyCrOO6LOSxqBrzY32ocdDCA")
					.build();

			try {
				Response response = client.newCall(request).execute();
				String respuesta = response.body().string();

				Gson gson = new Gson();
				RespuestaAPI respAPI = gson.fromJson(respuesta, RespuestaAPI.class);

				for (Personaje p : respAPI.getResults()) {
					Listas.listaPersonajes.add(p);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}