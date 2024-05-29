package dao;

import java.util.List;

import models.Personaje;
import models.UsuarioPersonajes;

public interface UsuarioPersonajesDaoInt extends CommonDaoInt<UsuarioPersonajes> {

	/**
	 * Te devuelve la lista de personajes que el usuario haya dado como favorito.
	 * 
	 * @param nombre Nombre del usuario
	 */
	public List<Personaje> personajesPorUsuario(final String nombre);
	
	/**
	 * Elimina la relación de favoritismo entre el usuario y el personaje.
	 * 
	 * @param nombre Nombre del usuario
	 * @param id Número identificador del personaje
	 */

	public void eliminarPorIdPersonajeDeUsuario(final String nombre, final int id);

}
