package org.MendezGalindoEmiliano.pixup.repository.jdbc;

import org.MendezGalindoEmiliano.pixup.model.GeneroMusical;

import java.util.List;

public interface GeneroMusicalJdbc {
    List<GeneroMusical> findAll();
}