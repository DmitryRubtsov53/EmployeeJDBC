package org.example;

import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException {

        final String user = "postgres";
        final String password = "1";
        final String url = "jdbc:postgresql://localhost:5432/skypro";


        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Задание № 1 ДЗ JDBC.................................................................

             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE id = ?")) {

            statement.setInt(1, 2);

            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                String emplId = "id: " + resultSet.getInt("id");
                String first_n = "/" + resultSet.getString("first_name");
                String last_n = resultSet.getString("last_name") + "/";
                String pol = resultSet.getString("gender");
                String emplAge = "age: " + resultSet.getInt(5);
                String cityId = "city: " + resultSet.getInt(6);

                System.out.println(emplId + " " + first_n + " " + last_n +
                        " " + pol + ", " + emplAge + ", " + cityId);

            }
        }
    }
}
