package org.MendezGalindoEmiliano.pixup.repository.jdbc;

import org.MendezGalindoEmiliano.pixup.model.Disquera;
import org.MendezGalindoEmiliano.pixup.model.Municipio;

import java.util.List;

public interface DisqueraJdbc {
    List<Disquera> findAll();
}
