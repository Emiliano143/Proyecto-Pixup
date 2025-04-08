package org.MendezGalindoEmiliano.pixup.repository.jdbc.impl;

import org.MendezGalindoEmiliano.pixup.model.Disquera;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.DisqueraJdbc;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.Conexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DisqueraJdbcImpl extends Conexion<Disquera> implements org.MendezGalindoEmiliano.pixup.repository.jdbc.DisqueraJdbc {
    private static DisqueraJdbc disqueraJdbc;

    private DisqueraJdbcImpl()
    {
    }

    public static DisqueraJdbc getInstance( )
    {
        if( disqueraJdbc == null )
        {
            disqueraJdbc = new DisqueraJdbcImpl( );
        }
        return disqueraJdbc;
    }

    @Override
    public List<Disquera> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Disquera>disqueras = null;
        Disquera disquera = null;
        String query = "SELECT * FROM TBL_Disquera";

        try
        {
            if( openConnection() )
            {
                System.out.println("Error en conexión");
                return null;
            }
            statement = connection.createStatement( );
            resultSet = statement.executeQuery( query );
            disqueras = new ArrayList<>( );
            while( resultSet.next() )
            {
                disquera = new Disquera();
                disquera.setId( resultSet.getInt( 1 ) );
                disquera.setNombre( resultSet.getString( 2 ) );
                disqueras.add( disquera );
            }
            resultSet.close();
            statement.close();
            closeConnection( );
            return disqueras;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void main( String a[] )
    {
        DisqueraJdbcImpl
                .getInstance()
                .findAll()
                .stream()
                .forEach( System.out::println);
    }

}