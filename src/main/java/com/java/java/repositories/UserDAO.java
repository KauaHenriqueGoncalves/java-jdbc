package com.java.java.repositories;

import com.java.java.model.User;
import com.java.java.model.enums.JobPosition;
import com.java.java.utils.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class connects to the database and provides methods to insert, update, delete,
 * retrieve, and list User records.
 */
public class UserDAO implements CrudRepository<User> {

    ConnectionFactory connectionFactory = new ConnectionFactory();
    Connection connection;

    /**
     * Constructs a new UserDAO and establishes a connection to the database.
     */
    public UserDAO() {
        connection = connectionFactory.getConnection();
    }

    /**
     * Inserts a new User record into the database.
     *
     * @param user The User object to be inserted.
     * @return true if the insertion was successful; false otherwise.
     */
    @Override
    public boolean insert(User user) {
        String sql = "insert into usuario (cpf, nome, data_nascimento, funcao) values(?,?,?,?::funcao_type)";

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getCpf());
            preparedStatement.setString(2, user.getName());
            Date dateSQl = new java.sql.Date(user.getDateOfBirth().getTime());
            preparedStatement.setDate(3, dateSQl);
            preparedStatement.setString(4, user.getJobPosition().toString());

            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Updates an existing User record in the database.
     *
     * @param user The User object with updated values.
     * @return true if the update was successful; false otherwise.
     */
    @Override
    public boolean update(User user) {
        String sql = "update usuario set cpf = ?, nome = ?, data_nascimento = ?, funcao = ?::funcao_type where id = ?";

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getCpf());
            preparedStatement.setString(2, user.getName());
            Date dateSQl = new java.sql.Date(user.getDateOfBirth().getTime());
            preparedStatement.setDate(3, dateSQl);
            preparedStatement.setString(4, user.getJobPosition().toString());
            preparedStatement.setInt(5, user.getId());

            int results = preparedStatement.executeUpdate();
            return results > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a User record from the database based on the given ID.
     *
     * @param id The ID of the User to delete.
     * @return true if the deletion was successful; false otherwise.
     */
    @Override
    public boolean delete(Integer id) {
        String sql = "delete from usuario where id = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int results = preparedStatement.executeUpdate();
            return results > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves a User record from the database by its ID.
     *
     * @param id The ID of the User to retrieve.
     * @return The User object if found; null otherwise.
     */
    @Override
    public User get(Integer id) {
        String sql = "select id, cpf, nome, data_nascimento, funcao from usuario where id = ?";
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Integer userId = resultSet.getInt("id");
                    String cpf = resultSet.getString("cpf");
                    String nome = resultSet.getString("nome");
                    Date dataNascimento = resultSet.getDate("data_nascimento");
                    String funcao = resultSet.getString("funcao");

                    JobPosition jobPosition = null;

                    if (funcao.equals("admin")) {
                        jobPosition = JobPosition.admin;
                    }
                    else {
                        jobPosition = JobPosition.comum;
                    }

                    return new User(userId, cpf, nome, dataNascimento, jobPosition);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all User records from the database, ordered by name.
     *
     * @return A list of all User objects.
     */
    @Override
    public List<User> selectAll() {
        List<User> users = new ArrayList<>();
        String sql = "select id, cpf, nome, data_nascimento, funcao from usuario order by nome";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)){
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String cpf = resultSet.getString("cpf");
                    String nome = resultSet.getString("nome");
                    Date dataNascimento = resultSet.getDate("data_nascimento");
                    String funcao = resultSet.getString("funcao");

                    JobPosition jobPosition = null;

                    if (funcao.equals("admin")) {
                        jobPosition = JobPosition.admin;
                    }
                    else {
                        jobPosition = JobPosition.comum;
                    }

                    users.add(new User(id, cpf, nome, dataNascimento, jobPosition));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Checks whether a User with the given CPF already exists in the database.
     *
     * @param cpf The CPF to check.
     * @return true if the CPF exists; false otherwise.
     */
    public boolean cpfExists(String cpf) {
        String sql = "select cpf from usuario where cpf = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cpf);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
