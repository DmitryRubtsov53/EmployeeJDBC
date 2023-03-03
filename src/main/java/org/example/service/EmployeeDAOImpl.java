package org.example.service;

import org.example.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO{

    Connection connection;

    public EmployeeDAOImpl(Connection connection) {
        this.connection = connection;
            }

    @Override
    public void create(Employee employee) {

        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO employee (first_name, last_name, gender, age, city_id) VALUES (?, ?, ?, ?, ?)")) {
            statement.setString(1, employee.getFirst_name());
            statement.setString(2, employee.getLast_name());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCity().getCity_id());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readById(int id) {

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id, first_name, last_name, gender, age, city_name " +
                        "FROM city INNER JOIN employee ON employee.city_id=city.city_id WHERE id=?")) {

            statement.setInt(1, id);

            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String emplId = "id: " + resultSet.getInt("id");
                String first_n = "/" + resultSet.getString("first_name");
                String last_n = resultSet.getString("last_name") + "/";
                String pol = resultSet.getString("gender");
                String emplAge = "age: " + resultSet.getInt(5);
                String cityId = "city: " + resultSet.getString("city_name");

                System.out.println(emplId + " " + first_n + " " + last_n +
                        " " + pol + ", " + emplAge + ", " + cityId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void readAll() {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id, first_name, last_name, gender, age, city_name FROM city INNER JOIN employee ON employee.city_id=city.city_id")) {

            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String emplId = "id: " + resultSet.getInt("id");
                String first_n = "/" + resultSet.getString("first_name");
                String last_n = resultSet.getString("last_name") + "/";
                String pol = resultSet.getString("gender");
                String emplAge = "age: " + resultSet.getInt(5);
                String cityId = "city: " + resultSet.getString("city_name");

                System.out.println(emplId + " " + first_n + " " + last_n +
                        " " + pol + ", " + emplAge + ", " + cityId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE employee SET first_name='Danila', last_name='Karpov', age=30 WHERE id=?")) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        @Override
    public void deleteById(int id) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM employee WHERE id=?")) {

                statement.setInt(1, id);
                statement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
}
