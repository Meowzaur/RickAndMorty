package models;

public class Usuario {

	// Atributos
	private String nombre;
	private String password;

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
