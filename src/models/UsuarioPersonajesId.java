package models;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class UsuarioPersonajesId implements java.io.Serializable {

	@ManyToOne
	@JoinColumn(name = "usuario_nombre")
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(name = "personaje_id")
	private Personaje personaje;

	public UsuarioPersonajesId() {
	}

	public UsuarioPersonajesId(Usuario usuario, Personaje personaje) {
		super();
		this.usuario = usuario;
		this.personaje = personaje;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Personaje getPersonaje() {
		return personaje;
	}

}
