package com.codecool.DAO;

import com.codecool.models.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO extends AbstractDAO {

    private Connection connection;

    public EmployeeDAO(Connection connectionToDB) {
        connection = connectionToDB;
    }

    public List<Employee> readAll() throws SQLException {
        List<Employee> employeeList = new ArrayList<>();

        String query = "SELECT * FROM employees;";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Employee employee = this.createEmployeeFromResultSet(resultSet);
            employeeList.add(employee);
        }
        return employeeList;
    }

    public Employee getByID(String ID) throws SQLException {
        ResultSet rs = null;
        String query = "SELECT * FROM employees WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, Integer.parseInt(ID));
        rs = statement.executeQuery();

        if (rs.next()) {
            return this.createEmployeeFromResultSet(rs);
        }
        return null;
    }

    public void create(Employee employee) throws SQLException {
        String query = "INSERT INTO employees (first_name, last_name, shop_id) VALUES (?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(query);
        setPreparedCreateStatement(statement, employee);
        statement.executeUpdate();
    }

    private void setPreparedCreateStatement(PreparedStatement statement, Employee employee) throws SQLException {
        statement.setString(1, employee.getFirstName());
        statement.setString(2, employee.getLastName());
        statement.setInt(3, employee.getShopID());
    }

    public void update(Employee employee) throws SQLException {
        String query = "UPDATE employees SET first_name = ?, last_name = ?, shop_id = ? WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(query);
        setPreparedUpdateStatement(statement, employee);
        statement.executeUpdate();
    }

    private void setPreparedUpdateStatement(PreparedStatement statement, Employee employee) throws SQLException {
        statement.setString(1, employee.getFirstName());
        statement.setString(2, employee.getLastName());
        statement.setInt(3, employee.getShopID());
        statement.setInt(4, employee.getID());
    }

    public void delete(Employee employee) throws SQLException {
        String query = "DELETE FROM employees WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, employee.getID());
        statement.executeUpdate();
    }

    Employee createEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
        int ID = Integer.parseInt(resultSet.getString("id"));
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        int shopID = Integer.parseInt(resultSet.getString("shop_id"));

        return new Employee(ID, firstName, lastName, shopID);
    }
}