package com.codecool.servlets;

import com.codecool.DAO.ConnectionToDB;
import com.codecool.DAO.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns= {"/products"})
public class DeleteProductServlet extends HttpServlet {

    private Connection connection;
    private ProductDAO productDAO;

    public DeleteProductServlet() {
        this.connection = ConnectionToDB.getConnection();
        this.productDAO = new ProductDAO(connection);
    }

    protected void doDelete( HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        String[] URI = request.getRequestURI().split("/");
        String productID = URI[URI.length-1];

        try {
            productDAO.delete(productDAO.getByID(productID));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}