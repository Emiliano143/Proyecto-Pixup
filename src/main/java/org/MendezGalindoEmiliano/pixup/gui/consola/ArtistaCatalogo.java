package org.MendezGalindoEmiliano.pixup.gui.consola;

import org.MendezGalindoEmiliano.pixup.model.Artista;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.ArtistaJdbc;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.impl.ArtistaJdbcImpl;
import org.MendezGalindoEmiliano.pixup.util.ReadUtil;

import java.util.ArrayList;
import java.util.List;

public class ArtistaCatalogo extends Catalogos<Artista> {
    private static ArtistaCatalogo artistaCatalogo;
    private final ArtistaJdbc artistaJdbc=ArtistaJdbcImpl.getInstance();

    private ArtistaCatalogo() {}

    public static ArtistaCatalogo getInstance() {
        if (artistaCatalogo == null) {
            artistaCatalogo = new ArtistaCatalogo();
        }
        return artistaCatalogo;
    }

    @Override
    public Artista newT() {
        return new Artista();
    }

    @Override
    public boolean processNewT(Artista artista) {
        if (artistaJdbc == null) {
            return false;
        }
        return artistaJdbc.findAll() != null;
    }

    @Override
    public boolean processNewT1(Artista a) {
        System.out.println("Dame el nombre del artista:");
        ArtistaJdbc artistaJdbc = ArtistaJdbcImpl.getInstance();

        a.setArtista(ReadUtil.read());
        boolean res = artistaJdbc.save(a);

        return res;
    }

    @Override
    public void processEditT(Artista artista) {
        System.out.println("El nombre del artista es: " + artista.getArtista());
        System.out.println("Dame el nuevo nombre del artista");
        artista.setArtista(ReadUtil.read());

        ArtistaJdbc artistaJdbc = ArtistaJdbcImpl.getInstance();

        artistaJdbc.update(artista);
    }

    @Override
    public void processRemoveT(Artista artista) {
        System.out.println("El nombre del artista es: " + artista.getArtista());

        ArtistaJdbc artistaJdbc = ArtistaJdbcImpl.getInstance();

        artistaJdbc.delete(artista);
    }

    @Override
    public void processFindByIdT(int id)
    {
        ArtistaJdbc artistaJdbc = ArtistaJdbcImpl.getInstance();

        Artista artista = artistaJdbc.findById(id);

        System.out.println("Artista encontrado:");
        System.out.println(artista.toString());
    }

    @Override
    public List<Artista> processList() {
        ArtistaJdbc artistaJdbc=ArtistaJdbcImpl.getInstance();
        List<Artista> list = artistaJdbc.findAll();

        if (list == null) {
            System.out.println("Error al ejecutar la consulta `findAll()`. Verifica la conexión y consulta.");
            return new ArrayList<>();
        }

        if (list.isEmpty()) {
            System.out.println("La base de datos está conectada, pero no hay registros en `tbl_artista`.");
        }

        return list;
    }


    @Override
    public String getTitulo() {
        return "Artistas";
    }
}



