package models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "usuario_personajes")
public class UsuarioPersonajes implements java.io.Serializable {

	// Atributo
	@EmbeddedId
	private UsuarioPersonajesId id;

	// Constructor vacío
	public UsuarioPersonajes() {
	}

	// Constructor
	public UsuarioPersonajes(UsuarioPersonajesId id) {
		this.id = id;
	}

	// Getter
	public UsuarioPersonajesId getId() {
		return this.id;
	}

}
