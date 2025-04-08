package org.MendezGalindoEmiliano.pixup.repository.jdbc.impl;

import org.MendezGalindoEmiliano.pixup.model.Colonia;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.Conexion;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.ColoniaJdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ColoniaJdbcImpl extends Conexion<Colonia> implements ColoniaJdbc {
    private static ColoniaJdbc coloniaJdbc;

    private ColoniaJdbcImpl()
    {
    }

    public static ColoniaJdbc getInstance( )
    {
        if( coloniaJdbc == null )
        {
            coloniaJdbc = new ColoniaJdbcImpl( );
        }
        return coloniaJdbc;
    }

    @Override
    public List<Colonia> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Colonia>colonias = null;
        Colonia colonia = null;
        String query = "SELECT * FROM TBL_Colonia";

        try
        {
            if( openConnection() )
            {
                System.out.println("Error en conexión");
                return null;
            }
            statement = connection.createStatement( );
            resultSet = statement.executeQuery( query );
            colonias = new ArrayList<>( );
            while( resultSet.next() )
            {
                colonia = new Colonia();
                colonia.setId( resultSet.getInt( 1 ) );
                colonia.setNombre( resultSet.getString( 3 ) );
                colonia.setCp( resultSet.getString( 4 ) );
                colonias.add( colonia );
            }
            resultSet.close();
            statement.close();
            closeConnection( );
            return colonias;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void main( String a[] )
    {
        ColoniaJdbcImpl
                .getInstance()
                .findAll()
                .stream()
                .forEach( System.out::println);
    }

}