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

	@Id
	@Column(name = "nombre")
	private String nombre;

	@Column(name = "password")
	private String password;

	@OneToMany(mappedBy = "id.usuario", cascade = CascadeType.ALL)
	private Set<UsuarioPersonajes> usuarioOTM = new HashSet<UsuarioPersonajes>(0);
	
	public Usuario() {
	}

	public Usuario(String nombre, String password) {
		this.nombre = nombre;
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPassword() {
		return password;
	}

	public Set<UsuarioPersonajes> getUsuarioOTM() {
		return usuarioOTM;
	}

	public void setUsuarioOTM(Set<UsuarioPersonajes> usuarioOTM) {
		this.usuarioOTM = usuarioOTM;
	}

}