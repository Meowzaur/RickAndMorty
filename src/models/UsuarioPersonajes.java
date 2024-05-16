package models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "usuario_personajes")
public class UsuarioPersonajes implements java.io.Serializable {

	@EmbeddedId
	private UsuarioPersonajesId id;

	public UsuarioPersonajes() {
	}

	public UsuarioPersonajes(UsuarioPersonajesId id) {
		this.id = id;
	}

	public UsuarioPersonajesId getId() {
		return this.id;
	}

}
