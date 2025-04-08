package org.MendezGalindoEmiliano.pixup.repository.jdbc.impl;

import org.MendezGalindoEmiliano.pixup.model.GeneroMusical;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.GeneroMusicalJdbc;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.Conexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GeneroMusicalJdbcImpl extends Conexion<GeneroMusical> implements GeneroMusicalJdbc {
    private static GeneroMusicalJdbc generoMusicalJdbc;

    private GeneroMusicalJdbcImpl()
    {
    }

    public static GeneroMusicalJdbc getInstance( )
    {
        if( generoMusicalJdbc == null )
        {
            generoMusicalJdbc = new GeneroMusicalJdbcImpl( );
        }
        return generoMusicalJdbc;
    }

    @Override
    public List<GeneroMusical> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<GeneroMusical>generoMusicals = null;
        GeneroMusical generoMusical = null;
        String query = "SELECT * FROM TBL_GeneroMusical";

        try
        {
            if( openConnection() )
            {
                System.out.println("Error en conexión");
                return null;
            }
            statement = connection.createStatement( );
            resultSet = statement.executeQuery( query );
            generoMusicals = new ArrayList<>( );
            while( resultSet.next() )
            {
                generoMusical = new GeneroMusical();
                generoMusical.setId( resultSet.getInt( 1 ) );
                generoMusical.setDescripcion( resultSet.getString( 2 ) );
                generoMusicals.add( generoMusical );
            }
            resultSet.close();
            statement.close();
            closeConnection( );
            return generoMusicals;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void main( String a[] )
    {
        GeneroMusicalJdbcImpl
                .getInstance()
                .findAll()
                .stream()
                .forEach( System.out::println);
    }

}