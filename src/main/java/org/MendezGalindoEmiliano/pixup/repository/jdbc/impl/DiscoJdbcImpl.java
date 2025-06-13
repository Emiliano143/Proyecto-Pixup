package org.MendezGalindoEmiliano.pixup.repository.jdbc.impl;

import org.MendezGalindoEmiliano.pixup.model.*;
import org.MendezGalindoEmiliano.pixup.model.Disco;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.Conexion;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.DiscoJdbc;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.DiscoJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DiscoJdbcImpl extends Conexion<Disco> implements DiscoJdbc 
{
    private static DiscoJdbc discoJdbc;

    public DiscoJdbcImpl() {
    }

    public static DiscoJdbc getInstance( )
    {
        if( discoJdbc == null )
        {
            discoJdbc = new DiscoJdbcImpl( );
        }
        return discoJdbc;
    }

    @Override
    public List<Disco> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Disco>list = null;
        Disco disco = null;
        String sql = "SELECT d.id AS d_id, d.titulo AS titulo, d.precio AS precio, d.existencia AS existencia, d.descuento AS descuento, d.fecha_lanzamiento AS fecha, d.imagen AS imagen, a.id AS art_id, a.nombre AS artista, dis.id AS dis_id, dis.nombre AS disquera, g.id AS gen_id, g.descripcion AS genero FROM tbl_disco d INNER JOIN tbl_artista a ON a.id = d.tbl_artista_id INNER JOIN tbl_disquera dis ON dis.id = d.tbl_disquera_id INNER JOIN tbl_generomusical g ON g.id = d.tbl_generomusical_id;";

        try
        {
            if( !openConnection() )
            {
                System.out.println("Error en conexión");
                return null;
            }
            statement = connection.createStatement( );
            resultSet = statement.executeQuery( sql );
            if(resultSet==null){
                return null;
            }
            list = new java.util.ArrayList<Disco>( );
            while( resultSet.next() )
            {

                disco = new Disco();
                disco.setId( resultSet.getInt( 1 ) );
                disco.setTitulo( resultSet.getString( 2) );
                disco.setPrecio( resultSet.getFloat( 3) );
                disco.setExistencia( resultSet.getInt( 4) );
                disco.setDescuento( resultSet.getFloat( 5) );
                disco.setFecha( resultSet.getString( 6) );
                disco.setImagen( resultSet.getString( 7) );
                disco.setArtista(new Artista());
                disco.setDisquera(new Disquera());
                disco.setGeneroMusical(new GeneroMusical());
                disco.getArtista().setId(resultSet.getInt(8));
                disco.getArtista().setArtista(resultSet.getString(9));
                disco.getDisquera().setId(resultSet.getInt(10));
                disco.getDisquera().setDisquera(resultSet.getString(11));
                disco.getGeneroMusical().setId(resultSet.getInt(12));
                disco.getGeneroMusical().setGeneroMusical(resultSet.getString(13));
                list.add( disco );
            }
            resultSet.close();
            //closeConnection( );
            return list;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean save(Disco disco) {
        String query = "INSERT INTO tbl_disco (titulo, precio, existencia, descuento, fecha_lanzamiento, imagen, tbl_artista_id, tbl_disquera_id, tbl_generomusical_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            if (!openConnection()) {
                System.out.println("Error en conexión");
                return false;
            }

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, disco.getTitulo());
            preparedStatement.setFloat(2, disco.getPrecio());
            preparedStatement.setInt(3, disco.getExistencia());
            preparedStatement.setFloat(4, disco.getDescuento());
            preparedStatement.setString(5, disco.getFecha());
            preparedStatement.setString(6, disco.getImagen());
            preparedStatement.setInt(7, disco.getArtista().getId());
            preparedStatement.setInt(8, disco.getDisquera().getId());
            preparedStatement.setInt(9, disco.getGeneroMusical().getId());

            int res = preparedStatement.executeUpdate();
            preparedStatement.close();
            ////closeConnection();

            return res == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Disco disco) {
        String query = "UPDATE tbl_disco SET titulo=?, precio=?, existencia=?, descuento=?, fecha_lanzamiento=?, imagen=?, tbl_artista_id=?, tbl_disquera_id=?, tbl_generomusical_id=? WHERE id=?";
        try {
            if (!openConnection()) {
                System.out.println("Error en conexión");
                return false;
            }

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, disco.getTitulo());
            preparedStatement.setFloat(2, disco.getPrecio());
            preparedStatement.setInt(3, disco.getExistencia());
            preparedStatement.setFloat(4, disco.getDescuento());
            preparedStatement.setString(5, disco.getFecha());
            preparedStatement.setString(6, disco.getImagen());
            preparedStatement.setInt(7, disco.getArtista().getId());
            preparedStatement.setInt(8, disco.getDisquera().getId());
            preparedStatement.setInt(9, disco.getGeneroMusical().getId());
            preparedStatement.setInt(10, disco.getId());

            int res = preparedStatement.executeUpdate();
            preparedStatement.close();
            //closeConnection();

            return res == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Disco disco) {
        String query = "DELETE FROM tbl_disco WHERE id=?";
        try {
            if (!openConnection()) {
                System.out.println("Error en conexión");
                return false;
            }

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, disco.getId());

            int res = preparedStatement.executeUpdate();
            preparedStatement.close();
            //closeConnection();

            return res == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Disco findById(Integer id)
    {
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Disco>list = null;
        Disco disco = null;
        String sql = "SELECT d.id AS d_id, d.titulo AS titulo, d.precio AS precio, d.existencia AS existencia, d.descuento AS descuento, d.fecha_lanzamiento AS fecha, d.imagen AS imagen, a.id AS art_id, a.nombre AS artista, dis.id AS dis_id, dis.nombre AS disquera, g.id AS gen_id, g.descripcion AS genero FROM tbl_disco d INNER JOIN tbl_artista a ON a.id = d.tbl_artista_id INNER JOIN tbl_disquera dis ON dis.id = d.tbl_disquera_id INNER JOIN tbl_generomusical g ON g.id = d.tbl_generomusical_id where d.id=?;";

        try {
            if (!openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                disco = new Disco();
                disco.setId(resultSet.getInt("d_id"));
                disco.setTitulo(resultSet.getString("titulo"));
                disco.setPrecio(resultSet.getFloat("precio"));
                disco.setExistencia(resultSet.getInt("existencia"));
                disco.setDescuento(resultSet.getFloat("descuento"));
                disco.setFecha(resultSet.getString("fecha"));
                disco.setImagen(resultSet.getString("imagen"));

                // Relacionar Artista
                Artista artista = new Artista();
                artista.setId(resultSet.getInt("art_id"));
                artista.setArtista(resultSet.getString("artista"));
                disco.setArtista(artista);

                // Relacionar Disquera
                Disquera disquera = new Disquera();
                disquera.setId(resultSet.getInt("dis_id"));
                disquera.setDisquera(resultSet.getString("disquera"));
                disco.setDisquera(disquera);

                // Relacionar Género Musical
                GeneroMusical generoMusical = new GeneroMusical();
                generoMusical.setId(resultSet.getInt("gen_id"));
                generoMusical.setGeneroMusical(resultSet.getString("genero"));
                disco.setGeneroMusical(generoMusical);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                //closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return disco;
    }
}
