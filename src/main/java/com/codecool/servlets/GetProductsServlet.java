package com.codecool.servlets;

import com.codecool.DAO.ConnectionToDB;
import com.codecool.DAO.ProductDAO;
import com.codecool.services.ProductToJSON;
import com.codecool.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/products")
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

        try {
            List<Product> products = this.productDAO.readAll();
            PrintWriter writer = response.getWriter();

            for(Product product : products) {
                try {
                    ProductToJSON jsonObject = new ProductToJSON(product);
                    writer.write(jsonObject.productToJSON());
                } catch (InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
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
