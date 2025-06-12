package org.MendezGalindoEmiliano.pixup.repository.jdbc;

import org.MendezGalindoEmiliano.pixup.model.Cancion;

import java.util.List;

public interface CancionJdbc
{
    List<Cancion> findAll();
    boolean save(Cancion cancion);
    boolean update(Cancion cancion);
    boolean delete(Cancion cancion);

    Cancion findById(Integer id);
}
