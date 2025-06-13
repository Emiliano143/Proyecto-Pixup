package org.MendezGalindoEmiliano.pixup.repository.jdbc.impl;

import org.MendezGalindoEmiliano.pixup.model.Disquera;
import org.MendezGalindoEmiliano.pixup.model.Disquera;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.DisqueraJdbc;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.Conexion;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.DisqueraJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DisqueraJdbcImpl extends Conexion<Disquera> implements DisqueraJdbc {
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
        String query = "SELECT * FROM TBL_disquera";

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
            disqueras = new java.util.ArrayList<Disquera>( );
            while( resultSet.next() )
            {
                disquera = new Disquera();
                disquera.setId( resultSet.getInt( 1 ) );
                disquera.setDisquera( resultSet.getString( 2 ) );
                disqueras.add( disquera );
            }
            resultSet.close();
            statement.close();
            //closeConnection( );
            return disqueras;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Disquera disquera) {
        PreparedStatement preparedStatement=null;
        String query="insert tbl_disquera (nombre) values (?)";
        int res=0;

        try {
            if(!openConnection()){
                System.out.println("Error en conexion");
                return false;
            }
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1, disquera.getDisquera());
            res=preparedStatement.executeUpdate();
            preparedStatement.close();
            //closeConnection();
            return res==1;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Disquera disquera) {
        PreparedStatement preparedStatement=null;
        String query="update tbl_disquera set nombre=? where id=?";
        int res=0;

        try {
            if(!openConnection()){
                System.out.println("Error en conexion");
                return false;
            }
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1, disquera.getDisquera());
            preparedStatement.setInt(2, disquera.getId());
            res=preparedStatement.executeUpdate();
            preparedStatement.close();
            //closeConnection();
            return res==1;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Disquera disquera) {
        PreparedStatement preparedStatement=null;
        String query="delete from tbl_disquera where id=?";
        int res=0;

        try {
            if(!openConnection()){
                System.out.println("Error en conexion");
                return false;
            }
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1, disquera.getId());
            res=preparedStatement.executeUpdate();
            preparedStatement.close();
            //closeConnection();
            return res==1;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Disquera findById(Integer id) {
        Statement statement = null;
        ResultSet resultSet = null;
        Disquera disquera = null;
        String query = "SELECT * FROM TBL_disquera where id=%d";

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
                disquera = new Disquera();
                disquera.setId( resultSet.getInt( 1 ) );
                disquera.setDisquera( resultSet.getString( 2 ) );
            }
            resultSet.close();
            statement.close();
            //closeConnection( );
            return disquera;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void main( String a[] )
    {
        DisqueraJdbc disqueraJdbc=DisqueraJdbcImpl.getInstance();
        List<Disquera> list=disqueraJdbc.findAll();
        for(Disquera disquera:list)
        {
            System.out.println(disquera.getId()+" "+disquera.getDisquera());
        }

    }

}