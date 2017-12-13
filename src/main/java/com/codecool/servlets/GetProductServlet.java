package com.codecool.servlets;

import com.codecool.DAO.ConnectionToDB;
import com.codecool.DAO.ProductDAO;
import com.codecool.ProductToJSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessControlException;
import java.sql.Connection;
import java.sql.SQLException;

public class GetProductServlet extends HttpServlet {

    private Connection connection;
    private ProductDAO productDAO;

    public GetProductServlet() {
        this.connection = ConnectionToDB.getConnection();
        this.productDAO = new ProductDAO(connection);
    }

    protected void doGet( HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        ProductToJSON JSONObject = new ProductToJSON();
        String[] URI = request.getRequestURI().split("/");
        String productID = URI[URI.length-1];

        try {
            if(this.productDAO.getByID(productID)!=null) {
                String productToJSON = JSONObject.productToJSON(this.productDAO.getByID(productID));
                response.getWriter().write(productToJSON);
            }
            else{
                response.getWriter().write("chuj");
            }
        } catch (SQLException | AccessControlException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
