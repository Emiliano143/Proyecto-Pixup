package org.MendezGalindoEmiliano.pixup.gui.consola;

import org.MendezGalindoEmiliano.pixup.model.Artista;
import org.MendezGalindoEmiliano.pixup.util.ReadUtil;

import java.io.File;

public class ArtistaCatalogo extends Catalogos<Artista>
{
    private static ArtistaCatalogo artistaCatalogo;

    private ArtistaCatalogo()
    {
    }
    public static ArtistaCatalogo getInstance( )
    {
        if(artistaCatalogo==null)
        {
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
        System.out.println("Dame el nombre del artista");
        artista.setArtista(ReadUtil.read());
        return true;
    }

    @Override
    public void processEditT(Artista artista) {
        System.out.println("El nombre del artista es: "+artista.getArtista());
        System.out.println("Dame el nuevo nombre del artista");
        artista.setArtista(ReadUtil.read());
    }

    @Override
    public File getFile() {
        return new File("Artista.list");
    }

    @Override
    public String getTitulo() {
        return "Artistas";
    }
}
