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

	// Atributos
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
	
	// Constructor vacío
	public Personaje() {
	}

	// Constructor
	public Personaje(int id, String name, String status, String image) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.image = image;
	}

	// Getters
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

}
