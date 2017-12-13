package com.codecool.servlets.DAO;

import com.codecool.servlets.models.Product;

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

    }
}