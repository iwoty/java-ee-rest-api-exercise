package com.codecool.servlets;

import com.codecool.DAO.ConnectionToDB;
import com.codecool.DAO.ProductDAO;
import com.codecool.ProductToJSON;
import com.codecool.services.IDParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessControlException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/products/*")
public class GetProductServlet extends HttpServlet {

    private Connection connection;
    private ProductDAO productDAO;
    private IDParser idParser;

    public GetProductServlet() {
        this.connection = ConnectionToDB.getConnection();
        this.productDAO = new ProductDAO(connection);
        this.idParser = new IDParser();
    }

    protected void doGet( HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String productID = idParser.getIDFromURI(request);

        try {
            if(this.productDAO.getByID(productID) != null) {
                ProductToJSON jsonObject= new ProductToJSON(this.productDAO.getByID(productID));
                String productToJSON = jsonObject.productToJSON();
                response.getWriter().write(productToJSON);
            } else{
                response.getWriter().write("chuj");
            }
        } catch (SQLException | AccessControlException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    protected void doDelete( HttpServletRequest request,
                             HttpServletResponse response)
            throws ServletException, IOException {

        String productID = idParser.getIDFromURI(request);

        try {
            productDAO.delete(productDAO.getByID(productID));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
