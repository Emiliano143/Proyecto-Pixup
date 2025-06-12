package org.MendezGalindoEmiliano.pixup.repository.jdbc.impl;

import org.MendezGalindoEmiliano.pixup.model.Artista;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.ArtistaJdbc;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class ArtistaJdbcImplTest {

    @Test
    void getInstance() {
        assertNotNull(ArtistaJdbcImpl.getInstance());
        System.out.println("instancia encontrada");
    }

    @Test
    void findAll() {
        ArtistaJdbc artistaJdbc=ArtistaJdbcImpl.getInstance();
        List<Artista> list=artistaJdbc.findAll();
        assertNotNull(list);
        list.stream().forEach(System.out::println);
    }

    @Test
    void save(){
        Artista artista=new Artista();
        boolean res=false;
        ArtistaJdbc artistaJdbc=ArtistaJdbcImpl.getInstance();
        artista.setArtista("artGuardado");
        res= artistaJdbc.save(artista);
        assertEquals(true, res);
        System.out.println("guardado");
    }

    @Test
    void update(){
        Artista artista=new Artista();
        boolean res=false;
        artista.setArtista("artModificado");
        artista.setId(15);
        ArtistaJdbc artistaJdbc=ArtistaJdbcImpl.getInstance();
        res= artistaJdbc.update(artista);
        assertEquals(true, res);
        System.out.println("modificado");
    }

    @Test
    void delete(){
        Artista artista=new Artista();
        boolean res=false;
        artista.setId(15);
        ArtistaJdbc artistaJdbc=ArtistaJdbcImpl.getInstance();
        res= artistaJdbc.delete(artista);
        assertEquals(true, res);
        System.out.println("eliminado");
    }

    @Test
    void findById(){
        ArtistaJdbc artistaJdbc=ArtistaJdbcImpl.getInstance();
        Artista artista=artistaJdbc.findById(14);
        assertNotNull(artista);
        assertTrue("artnuevo".equals(artista.getArtista()));
        assertEquals(14, artista.getId());
        System.out.println(artista);
    }
}