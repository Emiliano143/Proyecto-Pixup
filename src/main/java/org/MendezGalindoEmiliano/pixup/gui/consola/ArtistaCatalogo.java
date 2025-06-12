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
    public boolean removeT(Artista artista) {
        System.out.println("Dame el id del artista:");
        ArtistaJdbc artistaJdbc = ArtistaJdbcImpl.getInstance();

        artista.setId(ReadUtil.readInt());
        boolean res = artistaJdbc.delete(artista);

        return res;
    }

    @Override
    public boolean editT(Artista artista) {
        System.out.println("Dame el id del artista:");
        ArtistaJdbc artistaJdbc = ArtistaJdbcImpl.getInstance();
        artista.setId(ReadUtil.readInt());
        System.out.println("Dame el nuevo nombre");
        artista.setArtista(ReadUtil.read());
        boolean res = artistaJdbc.update(artista);

        return res;
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
    }

    @Override
    public List<Artista> processList() {
        List<Artista> list = artistaJdbc.findAll();
        if (list == null) {
            System.out.println("Error al ejecutar la consulta `findAll()`. Verifica la conexión y consulta.");

        }
        return new ArrayList<>();
    }



        @Override
    public String getTitulo() {
        return "Artistas";
    }
}



