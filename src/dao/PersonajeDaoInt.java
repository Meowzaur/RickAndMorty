package dao;

import java.util.List;

import models.Personaje;

public interface PersonajeDaoInt extends CommonDaoInt<Personaje> {

	public List<Personaje> searchByName(final String name);

	public Personaje searchByID(final int id);

}
