package com.codecool.DAO;

import com.codecool.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends AbstractDAO {

    private Connection connection;

    public ProductDAO(Connection connectionToDB) {
        connection = connectionToDB;
    }

    public List<Product> readAll() throws SQLException {
        List<Product> productList = new ArrayList<>();

        String query = "SELECT * FROM products;";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Product product = this.createProductFromResultSet(resultSet);
            productList.add(product);
        }
        return productList;
    }

    public Product getByID(String ID) throws SQLException {
        ResultSet rs = null;
        String query = "SELECT * FROM products WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, Integer.parseInt(ID));
        rs = statement.executeQuery();
        if(rs.next()) {
            return this.createProductFromResultSet(rs);
        }
        return null;
    }

    public void create(Product product) throws SQLException {
        String query = "INSERT INTO products (name, price) VALUES (?, ?);";
        PreparedStatement statement = connection.prepareStatement(query);
        setPreparedCreateStatement(statement, product);
        statement.executeUpdate();
    }

    private void setPreparedCreateStatement(PreparedStatement statement, Product product) throws SQLException {
        statement.setString(1, product.getName());
        statement.setFloat(2, product.getPrice());
    }

    public void update(Product product) throws SQLException {
        String query = "UPDATE products SET name = ?, price = ? WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(query);
        setPreparedUpdateStatement(statement, product);
        statement.executeUpdate();
    }

    private void setPreparedUpdateStatement(PreparedStatement statement, Product product) throws SQLException {
        statement.setString(1, product.getName());
        statement.setFloat(2, product.getPrice());
        statement.setInt(3, product.getID());
    }

    public void delete(Product product) throws SQLException {
        String query = "DELETE FROM products WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, product.getID());
        statement.executeUpdate();
    }

    Product createProductFromResultSet(ResultSet resultSet) throws SQLException {
        int ID = Integer.parseInt(resultSet.getString("id"));
        String name = resultSet.getString("name");
        float price = Float.parseFloat(resultSet.getString("price"));

        return new Product(ID, name, price);
    }
}