package org.MendezGalindoEmiliano.pixup.repository.jdbc;

import org.MendezGalindoEmiliano.pixup.model.Colonia;

import java.util.List;

public interface ColoniaJdbc {
    List<Colonia> findAll();
}