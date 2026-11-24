package com.example;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** JDBC access for the employees table. Connection settings can be overridden with environment variables. */
public final class EmployeeDAO {
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/company_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private final String url;
    private final String username;
    private final String password;

    public EmployeeDAO() {
        this.url = setting("DB_URL", DEFAULT_URL);
        this.username = setting("DB_USER", "root");
        this.password = setting("DB_PASSWORD", "");
    }

    private static String setting(String name, String fallback) {
        String value = System.getenv(name);
        return value == null || value.isBlank() ? fallback : value;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public void addEmployee(int id, String name, String department, BigDecimal salary) throws SQLException {
        String sql = "INSERT INTO employees (emp_id, name, department, salary) VALUES (?, ?, ?, ?)";
        try (Connection connection = connect(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, department);
            statement.setBigDecimal(4, salary);
            statement.executeUpdate();
        }
    }

    public List<Employee> getAllEmployees() throws SQLException {
        String sql = "SELECT emp_id, name, department, salary FROM employees ORDER BY emp_id";
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet results = statement.executeQuery()) {
            while (results.next()) {
                employees.add(new Employee(results.getInt("emp_id"), results.getString("name"),
                        results.getString("department"), results.getBigDecimal("salary")));
            }
        }
        return employees;
    }

    public record Employee(int id, String name, String department, BigDecimal salary) { }
}
