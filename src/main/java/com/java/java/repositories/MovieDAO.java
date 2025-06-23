package com.java.java.repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.java.java.model.Movie;
import com.java.java.utils.ConnectionFactory;

/**
 * This class connects to the database and provides methods to insert, update, delete,
 * retrieve, and list Movie records.
 */
public class MovieDAO implements CrudRepository<Movie> {

    ConnectionFactory connectionFactory = new ConnectionFactory();
    Connection connection;

    /**
     * Constructs a new MovieDAO and establishes a connection to the database.
     */
    public MovieDAO() {
        connection = connectionFactory.getConnection();
    }

    /**
     * Inserts a new Movie record into the database.
     *
     * @param movie The Movie object to be inserted.
     * @return true if the insertion was successful; false otherwise.
     */
    @Override
    public boolean insert(Movie movie) {
        String sql = "INSERT INTO filme (titulo, genero, diretor, sinopse, midia) VALUES (?, ?, ?, ?, ?) RETURNING id";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, movie.getTitulo());
            preparedStatement.setString(2, movie.getGenero());
            preparedStatement.setString(3, movie.getDiretor());
            preparedStatement.setString(4, movie.getSinopse());
            preparedStatement.setBytes(5, movie.getMidia());
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()){
                movie.setId(result.getInt("id"));
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates an existing Movie record in the database.
     *
     * @param movie The User object with updated values.
     * @return true if the update was successful; false otherwise.
     */
    @Override
    public boolean update(Movie movie) {
        String sql = "UPDATE filme SET titulo = ?, genero = ?, diretor = ?, sinopse = ?, midia = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, movie.getTitulo());
            preparedStatement.setString(2, movie.getGenero());
            preparedStatement.setString(3, movie.getDiretor());
            preparedStatement.setString(4, movie.getSinopse());
            preparedStatement.setBytes(5, movie.getMidia());
            preparedStatement.setInt(6, movie.getId());
            int results = preparedStatement.executeUpdate();
            return results > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a Movie record from the database based on the given ID.
     *
     * @param id The ID of the User to delete.
     * @return true if the deletion was successful; false otherwise.
     */
    @Override
    public boolean delete (Integer id) {
        String sql = "DELETE FROM filme WHERE id = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            int results = preparedStatement.executeUpdate();
            return results > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves a Movie record from the database by its ID.
     *
     * @param id The ID of the User to retrieve.
     * @return The User object if found; null otherwise.
     */
    public Movie get(Integer id) {
        String sql = "select id, titulo, genero, diretor, sinopse, midia from filme where id = ?";
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Integer filmeId = resultSet.getInt("id");
                    String titulo = resultSet.getString("titulo");
                    String genero = resultSet.getString("genero");
                    String diretor = resultSet.getString("diretor");
                    String sinopse = resultSet.getString("sinopse");
                    byte[] midia = resultSet.getBytes("midia");
                    return new Movie(filmeId, titulo,genero, diretor, sinopse, midia);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all Movie records from the database, ordered by name.
     *
     * @return A list of all User objects.
     */
    @Override
    public List<Movie> selectAll() {
        List<Movie> movies = new ArrayList<>();
        String sql = "select id, titulo, genero, diretor, sinopse, midia from filme order by titulo";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)){
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer filmeId = resultSet.getInt("id");
                    String titulo = resultSet.getString("titulo");
                    String genero = resultSet.getString("genero");
                    String diretor = resultSet.getString("diretor");
                    String sinopse = resultSet.getString("sinopse");
                    byte[] midia = resultSet.getBytes("midia");

                    Movie movie = new Movie(filmeId, titulo, genero, diretor, sinopse, midia);
                    movies.add(movie);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

}
