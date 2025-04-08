package org.MendezGalindoEmiliano.pixup.inicio;

import org.MendezGalindoEmiliano.pixup.gui.ConsolaVentana;

public class Inicio
{
    public static void main( String[] args )
    {
        System.out.println( "Inicio PixUp" );
        ConsolaVentana.getInstance( ).run( );
        System.out.println( "Termino PixUp" );
    }
}
