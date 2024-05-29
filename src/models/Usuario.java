package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "usuario")
public class Usuario implements java.io.Serializable {

	// Atributos
	@Id
	@Column(name = "nombre")
	private String nombre;

	@Column(name = "password")
	private String password;

	@OneToMany(mappedBy = "id.usuario", cascade = CascadeType.ALL)
	private Set<UsuarioPersonajes> usuarioOTM = new HashSet<UsuarioPersonajes>(0);
	
	// Constructor vacío
	public Usuario() {
	}

	// Constructor
	public Usuario(String nombre, String password) {
		this.nombre = nombre;
		this.password = password;
	}

	// Getters
	public String getNombre() {
		return nombre;
	}

	public String getPassword() {
		return password;
	}

}