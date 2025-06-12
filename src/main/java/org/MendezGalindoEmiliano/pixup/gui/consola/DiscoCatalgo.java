package org.MendezGalindoEmiliano.pixup.gui.consola;

import org.MendezGalindoEmiliano.pixup.model.Disco;
import org.MendezGalindoEmiliano.pixup.util.ReadUtil;

import java.io.File;
import java.util.List;

public class DiscoCatalgo extends Catalogos<Disco>
{
    public static DiscoCatalgo discoCatalogo;
    private DiscoCatalgo( )
    {
        super();
    }

    public static DiscoCatalgo getInstance( )
    {
        if(discoCatalogo==null)
        {
            discoCatalogo = new DiscoCatalgo();
        }
        return discoCatalogo;
    }

    @Override
    public Disco newT()
    {
        return new Disco( );
    }

    @Override
    public boolean removeT(Disco Disco)
    {
        System.out.println("Teclee un Disco" );
        Disco.setTitulo( ReadUtil.read( ) );
        return true;
    }

    @Override
    public boolean editT(Disco disco) {
        return false;
    }

    @Override
    public boolean processNewT1(Disco disco) {
        return false;
    }

    @Override
    public void processEditT(Disco Disco)
    {
        System.out.println("Id del Disco " + Disco.getId( ) );
        System.out.println("Disco a editar: " + Disco.getTitulo( ) );
        System.out.println("Teclee el valor nuevo del Disco" );
        Disco.setTitulo( ReadUtil.read( ) );
    }

    @Override
    public List<Disco> processList() {
        return List.of();
    }

    @Override
    public String getTitulo() {
        return "Discos";
    }
}