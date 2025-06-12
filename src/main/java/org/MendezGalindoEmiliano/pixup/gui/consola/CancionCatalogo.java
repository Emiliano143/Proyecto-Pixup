package org.MendezGalindoEmiliano.pixup.gui.consola;

import org.MendezGalindoEmiliano.pixup.model.Cancion;
import org.MendezGalindoEmiliano.pixup.util.ReadUtil;

import java.io.File;
import java.util.List;

public class CancionCatalogo extends Catalogos<Cancion>
{
    public static CancionCatalogo cancionCatalogo;
    private CancionCatalogo( )
    {
        super();
    }

    public static CancionCatalogo getInstance( )
    {
        if(cancionCatalogo==null)
        {
            cancionCatalogo = new CancionCatalogo();
        }
        return cancionCatalogo;
    }

    @Override
    public Cancion newT()
    {
        return new Cancion( );
    }

    @Override
    public boolean removeT(Cancion Cancion)
    {
        System.out.println("Teclee un Cancion" );
        Cancion.setTitulo( ReadUtil.read( ) );
        return true;
    }

    @Override
    public boolean editT(Cancion cancion) {
        return false;
    }

    @Override
    public boolean processNewT1(Cancion cancion) {
        return false;
    }

    @Override
    public void processEditT(Cancion Cancion)
    {
        System.out.println("Id del Cancion " + Cancion.getId( ) );
        System.out.println("Cancion a editar: " + Cancion.getTitulo( ) );
        System.out.println("Teclee el valor nuevo del Cancion" );
        Cancion.setTitulo( ReadUtil.read( ) );
    }

    @Override
    public List<Cancion> processList() {
        return List.of();
    }

    @Override
    public String getTitulo() {
        return "Canciones";
    }
}
