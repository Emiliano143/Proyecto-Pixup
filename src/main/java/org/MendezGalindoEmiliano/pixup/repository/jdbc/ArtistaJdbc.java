package org.MendezGalindoEmiliano.pixup.repository.jdbc;

import org.MendezGalindoEmiliano.pixup.model.Artista;

import java.util.List;

public interface ArtistaJdbc {
    List<Artista> findAll();
}