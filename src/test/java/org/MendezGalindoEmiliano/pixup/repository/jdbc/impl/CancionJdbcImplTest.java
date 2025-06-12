package org.MendezGalindoEmiliano.pixup.repository.jdbc.impl;

import org.MendezGalindoEmiliano.pixup.model.*;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.CancionJdbc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CancionJdbcImplTest {

    @Test
    void getInstance() {
        Assertions.assertNotNull(CancionJdbcImpl.getInstance());
        System.out.println("instancia encontrada");
    }

    @Test
    void findAll()  {
        CancionJdbc cancionJdbc = CancionJdbcImpl.getInstance();
        List<Cancion> list = cancionJdbc.findAll();
        Assertions.assertNotNull(list);
        list.forEach(System.out::println);
        String sql = "SELECT c.id AS can_id, c.titulo AS can_titulo, c.duracion AS can_duracion, d.id AS dis_id, d.titulo AS dis_titulo, d.precio AS dis_precio, d.existencia AS dis_existencia, d.descuento AS dis_descuento, d.fecha_lanzamiento AS dis_fecha, d.imagen AS d_imagen, a.id AS art_id, a.nombre AS artista, dis.id AS disquera_id, dis.nombre AS disquera, g.id AS gen_id, g.descripcion AS genero FROM tbl_cancion c INNER JOIN tbl_disco d ON d.id = c.tbl_disco_id INNER JOIN tbl_artista a ON a.id = d.tbl_artista_id INNER JOIN tbl_generomusical g ON g.id = d.tbl_generomusical_id INNER JOIN tbl_disquera dis ON dis.id = d.tbl_disquera_id;";
    }

    @Test
    void save() {
        Cancion cancion = new Cancion();
        boolean res = false;
        CancionJdbc cancionJdbc = CancionJdbcImpl.getInstance();

        cancion.setTitulo("CancionGuardada");
        cancion.setDuracion("02:05");

        // Asignar solo el ID del disco
        Disco disco = new Disco();
        disco.setId(1);
        cancion.setDisco(disco);

        res = cancionJdbc.save(cancion);
        assertTrue(res);
        System.out.println("guardado");
    }

    @Test
    void update() {
        Cancion cancion = new Cancion();
        boolean res = false;
        CancionJdbc cancionJdbc = CancionJdbcImpl.getInstance();

        cancion.setId(7);
        cancion.setTitulo("CancionModificada3");
        cancion.setDuracion("04:30");

        Disco disco = new Disco();
        disco.setId(3);
        cancion.setDisco(disco);

        res = cancionJdbc.update(cancion);
        assertTrue(res);
        System.out.println("cambiado");
    }

    @Test
    void delete() {
        Cancion cancion = new Cancion();
        boolean res = false;
        cancion.setId(7);
        CancionJdbc cancionJdbc = CancionJdbcImpl.getInstance();

        res = cancionJdbc.delete(cancion);
        assertTrue(res);
        System.out.println("eliminado");
    }

    @Test
    void findById() {
        CancionJdbc cancionJdbc = CancionJdbcImpl.getInstance();
        Cancion cancion = cancionJdbc.findById(5);

        assertNotNull(cancion);
        assertEquals(5, cancion.getId());
        System.out.println(cancion);
    }
}