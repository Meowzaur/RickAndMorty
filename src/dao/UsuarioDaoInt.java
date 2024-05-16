package dao;

import models.Usuario;

public interface UsuarioDaoInt extends CommonDaoInt<Usuario> {

	public Usuario searchByNombre(final String nombre);

}
