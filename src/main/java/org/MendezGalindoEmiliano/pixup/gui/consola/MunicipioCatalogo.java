package org.MendezGalindoEmiliano.pixup.gui.consola;

import org.MendezGalindoEmiliano.pixup.model.Municipio;
import org.MendezGalindoEmiliano.pixup.util.ReadUtil;

import java.io.File;

public class MunicipioCatalogo extends Catalogos<Municipio>
{
    public static MunicipioCatalogo municipioCatalogo;
    private MunicipioCatalogo( )
    {
        super();
    }

    public static MunicipioCatalogo getInstance( )
    {
        if(municipioCatalogo==null)
        {
            municipioCatalogo = new MunicipioCatalogo();
        }
        return municipioCatalogo;
    }

    @Override
    public Municipio newT()
    {
        return new Municipio( );
    }

    @Override
    public boolean processNewT(Municipio Municipio)
    {
        System.out.println("Teclee un Municipio" );
        Municipio.setNombre( ReadUtil.read( ) );
        return true;
    }

    @Override
    public void processEditT(Municipio Municipio)
    {
        System.out.println("Id del Municipio " + Municipio.getId( ) );
        System.out.println("Municipio a editar: " + Municipio.getNombre( ) );
        System.out.println("Teclee el valor nuevo del Municipio" );
        Municipio.setNombre( ReadUtil.read( ) );
    }

    @Override
    public File getFile()
    {
        return new File( "./Municipio.object");
    }

    @Override
    public String getTitulo() {
        return "Municipios";
    }
}
