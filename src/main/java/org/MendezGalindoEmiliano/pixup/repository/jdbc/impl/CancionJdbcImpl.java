package org.MendezGalindoEmiliano.pixup.repository.jdbc.impl;

import org.MendezGalindoEmiliano.pixup.model.*;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.Conexion;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.CancionJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CancionJdbcImpl extends Conexion<Cancion> implements CancionJdbc {
    private static CancionJdbc cancionJdbc;

    public CancionJdbcImpl() {
    }

    public static CancionJdbc getInstance() {
        if (cancionJdbc == null) {
            cancionJdbc = new CancionJdbcImpl();
        }
        return cancionJdbc;
    }

    @Override
    public List<Cancion> findAll() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Cancion> list = new ArrayList<>();
        String sql = "SELECT c.id AS can_id, c.titulo AS can_titulo, c.duracion AS can_duracion, " +
                "d.id AS dis_id, d.titulo AS dis_titulo, d.precio AS dis_precio, d.existencia AS dis_existencia, " +
                "d.descuento AS dis_descuento, d.fecha_lanzamiento AS dis_fecha, d.imagen AS d_imagen, " +
                "a.id AS art_id, a.nombre AS artista, dis.id AS disquera_id, dis.nombre AS disquera, " +
                "g.id AS gen_id, g.descripcion AS genero " +
                "FROM tbl_cancion c " +
                "INNER JOIN tbl_disco d ON d.id = c.tbl_disco_id " +
                "INNER JOIN tbl_artista a ON a.id = d.tbl_artista_id " +
                "INNER JOIN tbl_generomusical g ON g.id = d.tbl_generomusical_id " +
                "INNER JOIN tbl_disquera dis ON dis.id = d.tbl_disquera_id;";

        try {
            if (!openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Cancion cancion = new Cancion();
                cancion.setId(resultSet.getInt("can_id"));
                cancion.setTitulo(resultSet.getString("can_titulo"));
                cancion.setDuracion(resultSet.getString("can_duracion"));

                // Crear el objeto Disco y asignar sus valores
                Disco disco = new Disco();
                disco.setId(resultSet.getInt("dis_id"));
                disco.setTitulo(resultSet.getString("dis_titulo"));
                disco.setPrecio(resultSet.getInt("dis_precio"));
                disco.setExistencia(resultSet.getInt("dis_existencia"));
                disco.setDescuento(resultSet.getFloat("dis_descuento"));
                disco.setFecha(resultSet.getString("dis_fecha"));
                disco.setImagen(resultSet.getString("d_imagen"));

                // Relacionar Artista
                Artista artista = new Artista();
                artista.setId(resultSet.getInt("art_id"));
                artista.setArtista(resultSet.getString("artista"));
                disco.setArtista(artista);

                // Relacionar Disquera
                Disquera disquera = new Disquera();
                disquera.setId(resultSet.getInt("disquera_id"));
                disquera.setDisquera(resultSet.getString("disquera"));
                disco.setDisquera(disquera);

                // Relacionar Género Musical
                GeneroMusical generoMusical = new GeneroMusical();
                generoMusical.setId(resultSet.getInt("gen_id"));
                generoMusical.setGeneroMusical(resultSet.getString("genero"));
                disco.setGeneroMusical(generoMusical);

                // Asignar el disco a la canción
                cancion.setDisco(disco);
                list.add(cancion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                //closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    @Override
    public boolean save(Cancion cancion) {
        String query = "INSERT INTO tbl_cancion (titulo, duracion, tbl_disco_id) VALUES (?, ?, ?)";
        try {
            if (!openConnection()) {
                System.out.println("Error en conexión");
                return false;
            }

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cancion.getTitulo());
            preparedStatement.setString(2, cancion.getDuracion());
            preparedStatement.setInt(3, cancion.getDisco().getId()); // Solo asociando con disco existente

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
    public boolean update(Cancion cancion) {
        String query = "UPDATE tbl_cancion SET titulo=?, duracion=?, tbl_disco_id=? WHERE id=?";
        try {
            if (!openConnection()) {
                System.out.println("Error en conexión");
                return false;
            }

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cancion.getTitulo());
            preparedStatement.setString(2, cancion.getDuracion());
            preparedStatement.setInt(3, cancion.getDisco().getId());
            preparedStatement.setInt(4, cancion.getId());

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
    public boolean delete(Cancion cancion) {
        String query = "DELETE FROM tbl_cancion WHERE id=?";
        try {
            if (!openConnection()) {
                System.out.println("Error en conexión");
                return false;
            }

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cancion.getId());

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
    public Cancion findById(Integer id) {
        String sql = "SELECT c.id AS can_id, c.titulo AS can_titulo, c.duracion AS can_duracion, " +
                "d.id AS dis_id, d.titulo AS dis_titulo, d.precio AS dis_precio, d.existencia AS dis_existencia, " +
                "d.descuento AS dis_descuento, d.fecha_lanzamiento AS dis_fecha, d.imagen AS d_imagen, " +
                "a.id AS art_id, a.nombre AS artista, dis.id AS disquera_id, dis.nombre AS disquera, " +
                "g.id AS gen_id, g.descripcion AS genero " +
                "FROM tbl_cancion c " +
                "INNER JOIN tbl_disco d ON d.id = c.tbl_disco_id " +
                "INNER JOIN tbl_artista a ON a.id = d.tbl_artista_id " +
                "INNER JOIN tbl_generomusical g ON g.id = d.tbl_generomusical_id " +
                "INNER JOIN tbl_disquera dis ON dis.id = d.tbl_disquera_id WHERE c.id=?";

        try {
            if (!openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Cancion cancion = null;
            if (resultSet.next()) {
                cancion = new Cancion();
                cancion.setId(resultSet.getInt("can_id"));
                cancion.setTitulo(resultSet.getString("can_titulo"));
                cancion.setDuracion(resultSet.getString("can_duracion"));

                // Relacionar Disco
                Disco disco = new Disco();
                disco.setId(resultSet.getInt("dis_id"));
                disco.setTitulo(resultSet.getString("dis_titulo"));
                disco.setPrecio(resultSet.getFloat("dis_precio"));
                disco.setExistencia(resultSet.getInt("dis_existencia"));
                disco.setDescuento(resultSet.getFloat("dis_descuento"));
                disco.setFecha(resultSet.getString("dis_fecha"));
                disco.setImagen(resultSet.getString("d_imagen"));

                // Relacionar Artista
                Artista artista = new Artista();
                artista.setId(resultSet.getInt("art_id"));
                artista.setArtista(resultSet.getString("artista"));
                disco.setArtista(artista);

                // Relacionar Disquera
                Disquera disquera = new Disquera();
                disquera.setId(resultSet.getInt("disquera_id"));
                disquera.setDisquera(resultSet.getString("disquera"));
                disco.setDisquera(disquera);

                // Relacionar Género Musical
                GeneroMusical generoMusical = new GeneroMusical();
                generoMusical.setId(resultSet.getInt("gen_id"));
                generoMusical.setGeneroMusical(resultSet.getString("genero"));
                disco.setGeneroMusical(generoMusical);

                cancion.setDisco(disco);
            }

            resultSet.close();
            preparedStatement.close();
            //closeConnection();

            return cancion;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
