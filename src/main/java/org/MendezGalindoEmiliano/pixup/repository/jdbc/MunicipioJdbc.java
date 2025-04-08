package org.MendezGalindoEmiliano.pixup.repository.jdbc;

import org.MendezGalindoEmiliano.pixup.model.Municipio;

import java.util.List;

public interface MunicipioJdbc
{
    List<Municipio> findAll();
}