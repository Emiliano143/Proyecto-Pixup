package org.MendezGalindoEmiliano.pixup.gui.consola;

import org.MendezGalindoEmiliano.pixup.model.Colonia;
import org.MendezGalindoEmiliano.pixup.util.ReadUtil;

import java.io.File;

public class ColoniaCatalogo extends Catalogos<Colonia>
{
    public static ColoniaCatalogo ColoniaCatalogo;
    private ColoniaCatalogo( )
    {
        super();
    }

    public static ColoniaCatalogo getInstance( )
    {
        if(ColoniaCatalogo==null)
        {
            ColoniaCatalogo = new ColoniaCatalogo();
        }
        return ColoniaCatalogo;
    }

    @Override
    public Colonia newT()
    {
        return new Colonia( );
    }

    @Override
    public boolean processNewT(Colonia Colonia)
    {
        System.out.println("Teclee un Colonia" );
        Colonia.setNombre( ReadUtil.read( ) );
        System.out.println("Teclee un codigo postal" );
        Colonia.setCp( ReadUtil.read( ) );
        return true;
    }

    @Override
    public void processEditT(Colonia Colonia)
    {
        System.out.println("Id de Colonia " + Colonia.getId( ) );
        System.out.println("Colonia a editar: " + Colonia.getNombre( )+ " "+Colonia.getCp() );
        System.out.println("Teclee el valor nuevo de Colonia" );
        Colonia.setNombre( ReadUtil.read( ) );
        System.out.println("Teclee el nuevo codigo postal de Colonia" );
        Colonia.setCp( ReadUtil.read( ) );
    }

    @Override
    public File getFile()
    {
        return new File( "./Colonia.object");
    }

    @Override
    public String getTitulo() {
        return "Colonia";
    }
}