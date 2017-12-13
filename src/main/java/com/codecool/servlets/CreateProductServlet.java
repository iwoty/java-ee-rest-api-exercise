package com.codecool.servlets;

import com.codecool.DAO.ConnectionToDB;
import com.codecool.DAO.ProductDAO;
import com.codecool.models.Product;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class CreateProductServlet extends HttpServlet {

    private Connection connection;
    private ProductDAO productDAO;

    public CreateProductServlet() {
        this.connection = ConnectionToDB.getConnection();
        this.productDAO = new ProductDAO(connection);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) {
        try {
            this.productDAO.create(new Product(request.getParameter("name"), Float.parseFloat(request.getParameter("price"))));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
