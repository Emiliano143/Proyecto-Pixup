package org.MendezGalindoEmiliano.pixup.repository.jdbc.impl;

import org.MendezGalindoEmiliano.pixup.model.Estado;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.Conexion;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.EstadoJdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EstadoJdbcImpl extends Conexion<Estado> implements EstadoJdbc {
    private static EstadoJdbc estadoJdbc;

    private EstadoJdbcImpl()
    {
    }

    public static EstadoJdbc getInstance( )
    {
        if( estadoJdbc == null )
        {
            estadoJdbc = new EstadoJdbcImpl( );
        }
        return estadoJdbc;
    }

    @Override
    public List<Estado> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Estado>estados = null;
        Estado estado = null;
        String query = "SELECT * FROM TBL_ESTADO";

        try
        {
            if( openConnection() )
            {
                System.out.println("Error en conexión");
                return null;
            }
            statement = connection.createStatement( );
            resultSet = statement.executeQuery( query );
            estados = new ArrayList<>( );
            while( resultSet.next() )
            {
                estado = new Estado();
                estado.setId( resultSet.getInt( 1 ) );
                estado.setNombre( resultSet.getString( 2 ) );
                estados.add( estado );
            }
            resultSet.close();
            statement.close();
            closeConnection( );
            return estados;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void main( String a[] )
    {
        EstadoJdbcImpl
                .getInstance()
                .findAll()
                .stream()
                .forEach( System.out::println);
    }

}
