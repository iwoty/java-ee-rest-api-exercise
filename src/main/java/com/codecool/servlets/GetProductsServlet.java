package com.codecool.servlets;

import com.codecool.DAO.ConnectionToDB;
import com.codecool.DAO.ProductDAO;
import com.codecool.ProductToJSON;
import com.codecool.models.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GetProductsServlet extends HttpServlet {

    private Connection connection;
    private ProductDAO productDAO;

    public GetProductsServlet() {
        this.connection = ConnectionToDB.getConnection();
        this.productDAO = new ProductDAO(connection);
    }

    protected void doGet( HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        ProductToJSON productToJSON = new ProductToJSON();
        try {
            List<Product> products = this.productDAO.readAll();
            for(Product product : products) {
                try {
                    response.getWriter().write(productToJSON.productToJSON(product));
                } catch (InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
