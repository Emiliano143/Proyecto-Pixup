package org.MendezGalindoEmiliano.pixup.repository.jdbc.impl;

import org.MendezGalindoEmiliano.pixup.model.GeneroMusical;
import org.MendezGalindoEmiliano.pixup.model.GeneroMusical;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.Conexion;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.GeneroMusicalJdbc;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.GeneroMusicalJdbc;

import java.sql.PreparedStatement;
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
        String query = "SELECT * FROM TBL_generoMusical";

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
            generoMusicals = new java.util.ArrayList<GeneroMusical>( );
            while( resultSet.next() )
            {
                generoMusical = new GeneroMusical();
                generoMusical.setId( resultSet.getInt( 1 ) );
                generoMusical.setGeneroMusical( resultSet.getString( 2 ) );
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

    @Override
    public boolean save(GeneroMusical generoMusical) {
        PreparedStatement preparedStatement=null;
        String query="insert tbl_generoMusical (descripcion) values (?)";
        int res=0;

        try {
            if(!openConnection()){
                System.out.println("Error en conexion");
                return false;
            }
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1, generoMusical.getGeneroMusical());
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
    public boolean update(GeneroMusical generoMusical) {
        PreparedStatement preparedStatement=null;
        String query="update tbl_generoMusical set descripcion=? where id=?";
        int res=0;

        try {
            if(!openConnection()){
                System.out.println("Error en conexion");
                return false;
            }
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1, generoMusical.getGeneroMusical());
            preparedStatement.setInt(2, generoMusical.getId());
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
    public boolean delete(GeneroMusical generoMusical) {
        PreparedStatement preparedStatement=null;
        String query="delete from tbl_generoMusical where id=?";
        int res=0;

        try {
            if(!openConnection()){
                System.out.println("Error en conexion");
                return false;
            }
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1, generoMusical.getId());
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
    public GeneroMusical findById(Integer id) {
        Statement statement = null;
        ResultSet resultSet = null;
        GeneroMusical generoMusical = null;
        String query = "SELECT * FROM TBL_generoMusical where id=%d";

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
                generoMusical = new GeneroMusical();
                generoMusical.setId( resultSet.getInt( 1 ) );
                generoMusical.setGeneroMusical( resultSet.getString( 2 ) );
            }
            resultSet.close();
            statement.close();
            closeConnection( );
            return generoMusical;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void main( String a[] )
    {
        GeneroMusicalJdbc generoMusicalJdbc=GeneroMusicalJdbcImpl.getInstance();
        List<GeneroMusical> list=generoMusicalJdbc.findAll();
        for(GeneroMusical generoMusical:list)
        {
            System.out.println(generoMusical.getId()+" "+generoMusical.getGeneroMusical());
        }

    }

}