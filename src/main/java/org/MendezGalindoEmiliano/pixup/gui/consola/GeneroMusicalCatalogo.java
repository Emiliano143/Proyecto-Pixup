package org.MendezGalindoEmiliano.pixup.gui.consola;

import org.MendezGalindoEmiliano.pixup.model.GeneroMusical;
import org.MendezGalindoEmiliano.pixup.util.ReadUtil;

import java.io.File;

public class GeneroMusicalCatalogo extends Catalogos<GeneroMusical>
{
    private static GeneroMusicalCatalogo GeneroMusicalCatalogo;

    private GeneroMusicalCatalogo()
    {
    }
    public static GeneroMusicalCatalogo getInstance( )
    {
        if(GeneroMusicalCatalogo==null)
        {
            GeneroMusicalCatalogo = new GeneroMusicalCatalogo();
        }
        return GeneroMusicalCatalogo;
    }

    @Override
    public GeneroMusical newT() {
        return new GeneroMusical();
    }

    @Override
    public boolean processNewT(GeneroMusical GeneroMusical) {
        System.out.println("Dame el genero musical");
        GeneroMusical.setDescripcion(ReadUtil.read());
        return true;
    }

    @Override
    public void processEditT(GeneroMusical GeneroMusical) {
        System.out.println("El genero musical es: "+GeneroMusical.getDescripcion());
        System.out.println("El id del genero musical es: "+GeneroMusical.getId());
        System.out.println("Dame el nuevo genero musical");
        GeneroMusical.setDescripcion(ReadUtil.read());
    }

    @Override
    public File getFile() {
        return new File("GeneroMusical.list");
    }

    @Override
    public String getTitulo() {
        return "Generos musicales";
    }
}
