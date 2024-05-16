package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "personaje")
public class Personaje implements java.io.Serializable {

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "status")
	private String status;

	@Column(name = "image")
	private String image;

	@OneToMany(mappedBy = "id.personaje", cascade = CascadeType.ALL)
	private Set<UsuarioPersonajes> personajeOTM = new HashSet<UsuarioPersonajes>(0);
	
	public Personaje() {
	}

	public Personaje(int id, String name, String status, String image) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public String getImage() {
		return image;
	}

	public Set<UsuarioPersonajes> getPersonajeOTM() {
		return personajeOTM;
	}

	public void setPersonajeOTM(Set<UsuarioPersonajes> personajeOTM) {
		this.personajeOTM = personajeOTM;
	}

}
