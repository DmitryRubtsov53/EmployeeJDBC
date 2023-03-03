package org.example;

import org.example.model.City;
import org.example.model.Employee;
import org.example.service.EmployeeDAO;
import org.example.service.EmployeeDAOImpl;

import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException {

        final String user = "postgres";
        final String password = "1";
        final String url = "jdbc:postgresql://localhost:5432/skypro";


        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Задание № 1 ДЗ JDBC.................................................................

             PreparedStatement statement = connection.prepareStatement(
                     "SELECT id, first_name, last_name, gender, age, city_name " +
                     "FROM city INNER JOIN employee ON employee.city_id=city.city_id WHERE id=?")) {

            statement.setInt(1, 2);

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
// Задача № 2 ДЗ JDBC
            System.out.println("----------------------------------------------------------");
            EmployeeDAO employeeDAO = new EmployeeDAOImpl(connection);
            Employee Sasha = new Employee("Sasha","Orlov","male",25,
                    new City(1,"Ufa"));

            employeeDAO.create(Sasha);
            employeeDAO.readById(5);
            System.out.println("----------------------------------------------------------");
            employeeDAO.readAll();
            System.out.println("----------------------------------------------------------");
            employeeDAO.updateById(5);
            employeeDAO.readById(5);
            System.out.println("----------------------------------------------------------");
            employeeDAO.deleteById(2);
            employeeDAO.readAll();
        }
    }
}
