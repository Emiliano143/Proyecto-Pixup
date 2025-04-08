package org.MendezGalindoEmiliano.pixup.repository.jdbc;

import org.MendezGalindoEmiliano.pixup.model.Estado;

import java.util.List;

public interface EstadoJdbc
{
    List<Estado> findAll();
}
