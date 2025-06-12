package org.MendezGalindoEmiliano.pixup.repository.jdbc.impl;

import org.MendezGalindoEmiliano.pixup.model.Artista;
import org.MendezGalindoEmiliano.pixup.model.Disco;
import org.MendezGalindoEmiliano.pixup.model.Disquera;
import org.MendezGalindoEmiliano.pixup.model.GeneroMusical;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.DiscoJdbc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiscoJdbcImplTest {

    @Test
    void getInstance() {
        Assertions.assertNotNull(DiscoJdbcImpl.getInstance());
        System.out.println("instancia encontrada");
    }

    @Test
    void findAll() {
        DiscoJdbc discoJdbc=DiscoJdbcImpl.getInstance();
        List<Disco> list=discoJdbc.findAll();
        Assertions.assertNotNull(list);
        list.stream().forEach(System.out::println);
        String sql="SELECT d.id AS d_id, d.titulo AS titulo, d.precio AS precio, d.existencia AS existencia, d.descuento AS descuento, d.fecha_lanzamiento AS fecha, d.imagen AS imagen, a.id AS art_id, a.nombre AS artista, dis.id AS dis_id, dis.nombre AS disquera, g.id AS gen_id, g.descripcion AS genero FROM tbl_disco d INNER JOIN tbl_artista a ON a.id = d.tbl_artista_id INNER JOIN tbl_disquera dis ON dis.id = d.tbl_disquera_id INNER JOIN tbl_generomusical g ON g.id = d.tbl_generomusical_id;";

    }

    @Test
    void save() {
        Disco disco = new Disco();
        boolean res = false;
        DiscoJdbc discoJdbc = DiscoJdbcImpl.getInstance();

        disco.setTitulo("DiscoGuardado");
        disco.setPrecio(290f);
        disco.setExistencia(110);
        disco.setDescuento(120f);
        disco.setFecha("2023-06-10");
        disco.setImagen("nuevo_disco");
        disco.setArtista(new Artista());
        disco.getArtista().setId(1);
        disco.setDisquera(new Disquera());
        disco.getDisquera().setId(1);
        disco.setGeneroMusical(new GeneroMusical());
        disco.getGeneroMusical().setId(1);

        res = discoJdbc.save(disco);
        assertTrue(res);
        System.out.println("guardado");
    }

    @Test
    void update() {
        Disco disco = new Disco();
        boolean res = false;
        DiscoJdbc discoJdbc = DiscoJdbcImpl.getInstance();

        disco.setId(4);
        disco.setTitulo("DiscoCambiado");
        disco.setPrecio(320f);
        disco.setExistencia(98);
        disco.setDescuento(157f);
        disco.setFecha("2056-06-18");
        disco.setImagen("ImagenModificado");
        disco.setArtista(new Artista());
        disco.getArtista().setId(1);
        disco.setDisquera(new Disquera());
        disco.getDisquera().setId(1);
        disco.setGeneroMusical(new GeneroMusical());
        disco.getGeneroMusical().setId(1);

        res = discoJdbc.update(disco);
        assertTrue(res);
        System.out.println("cambiado");
    }

    @Test
    void delete() {
        Disco disco = new Disco();
        boolean res = false;
        disco.setId(4);
        DiscoJdbc discoJdbc = DiscoJdbcImpl.getInstance();

        res = discoJdbc.delete(disco);
        assertTrue(res);
        System.out.println("eliminado");
    }

    @Test
    void findById() {
        DiscoJdbc discoJdbc = DiscoJdbcImpl.getInstance();
        Disco disco = discoJdbc.findById(3);

        assertNotNull(disco);
        assertEquals(3, disco.getId());
        System.out.println(disco);
    }
}
