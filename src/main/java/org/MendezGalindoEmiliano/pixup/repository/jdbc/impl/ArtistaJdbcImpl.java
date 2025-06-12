package org.MendezGalindoEmiliano.pixup.repository.jdbc.impl;

import org.MendezGalindoEmiliano.pixup.model.Artista;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.Conexion;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.ArtistaJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ArtistaJdbcImpl extends Conexion<Artista> implements ArtistaJdbc {
    private static ArtistaJdbc artistaJdbc;

    private ArtistaJdbcImpl()
    {
    }

    public static ArtistaJdbc getInstance( )
    {
        if( artistaJdbc == null )
        {
            artistaJdbc = new ArtistaJdbcImpl( );
        }
        return artistaJdbc;
    }

    @Override
    public List<Artista> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Artista>artistas = null;
        Artista artista = null;
        String query = "SELECT * FROM TBL_artista";

        try
        {
            if( !openConnection() )
            {
                System.out.println("Error en conexión");
                return null;
            }
            statement = connection.createStatement( );
            resultSet = statement.executeQuery( query );
            if(resultSet==null){
                return null;
            }
            artistas = new java.util.ArrayList<Artista>( );
            while( resultSet.next() )
            {
                artista = new Artista();
                artista.setId( resultSet.getInt( 1 ) );
                artista.setArtista( resultSet.getString( 2 ) );
                artistas.add( artista );
            }
            resultSet.close();
            statement.close();
            closeConnection( );
            return artistas;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Artista artista) {
        PreparedStatement preparedStatement=null;
        String query="insert tbl_artista (nombre) values (?)";
        int res=0;

        try {
            if(!openConnection()){
                System.out.println("Error en conexion");
                return false;
            }
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1, artista.getArtista());
            res=preparedStatement.executeUpdate();
            preparedStatement.close();
            closeConnection();
            return res==1;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Artista artista) {
        PreparedStatement preparedStatement=null;
        String query="update tbl_artista set nombre=? where id=?";
        int res=0;

        try {
            if(!openConnection()){
                System.out.println("Error en conexion");
                return false;
            }
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1, artista.getArtista());
            preparedStatement.setInt(2, artista.getId());
            res=preparedStatement.executeUpdate();
            preparedStatement.close();
            closeConnection();
            return res==1;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Artista artista) {
        PreparedStatement preparedStatement=null;
        String query="delete from tbl_artista where id=?";
        int res=0;

        try {
            if(!openConnection()){
                System.out.println("Error en conexion");
                return false;
            }
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1, artista.getId());
            res=preparedStatement.executeUpdate();
            preparedStatement.close();
            closeConnection();
            return res==1;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Artista findById(Integer id) {
        Statement statement = null;
        ResultSet resultSet = null;
        Artista artista = null;
        String query = "SELECT * FROM TBL_artista where id=%d";

        try
        {
            if( !openConnection() )
            {
                System.out.println("Error en conexión");
                return null;
            }
            query = String.format(query, id);
            statement = connection.createStatement( );
            resultSet = statement.executeQuery( query );
            if( resultSet.next() )
            {
                artista = new Artista();
                artista.setId( resultSet.getInt( 1 ) );
                artista.setArtista( resultSet.getString( 2 ) );
            }
            resultSet.close();
            statement.close();
            closeConnection( );
            return artista;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void main( String a[] )
    {
        ArtistaJdbc artistaJdbc=ArtistaJdbcImpl.getInstance();
        List<Artista> list=artistaJdbc.findAll();
        for(Artista artista:list)
        {
            System.out.println(artista.getId()+" "+artista.getArtista());
        }

    }

}