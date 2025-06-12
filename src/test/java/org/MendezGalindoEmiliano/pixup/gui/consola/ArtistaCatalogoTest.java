package org.MendezGalindoEmiliano.pixup.gui.consola;

import org.MendezGalindoEmiliano.pixup.repository.jdbc.impl.ArtistaJdbcImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtistaCatalogoTest {

    @Test
    void getInstance() {
        assertNotNull(ArtistaJdbcImpl.getInstance());
    }

    @Test
    void newT() {
    }

    @Test
    void processNewT() {
    }

    @Test
    void processEditT() {
    }

    @Test
    void processList()
    {
        //ArtistaCatalogo artistaCatalogo=ArtistaCatalogo.getInstance();
        //List<Artista> artistas=artistaCatalogo.processList();
        //artistas.forEach(System.out::println);
    }

    @Test
    void procesaOpcion()
    {
        //ArtistaCatalogo artistaCatalogo=ArtistaCatalogo.getInstance();
        //artistaCatalogo.setOpcion(3);
        //artistaCatalogo.procesaOpcion();
    }
}