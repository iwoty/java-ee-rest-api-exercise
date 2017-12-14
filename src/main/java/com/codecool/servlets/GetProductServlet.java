package com.codecool.servlets;

import com.codecool.DAO.ConnectionToDB;
import com.codecool.DAO.ProductDAO;
import com.codecool.models.Product;
import com.codecool.services.IDParser;
import com.codecool.services.ProductToJSON;

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
                redirectTo404(response);
            }
        } catch (SQLException | AccessControlException | InvocationTargetException | IllegalAccessException | IOException e) {
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

    protected void doPut( HttpServletRequest request,
                          HttpServletResponse response) {

        String id = request.getParameter("id");
        try {
            Product product = this.productDAO.getByID(id);
            product.setName(request.getParameter("name"));
            product.setPrice(Integer.parseInt(request.getParameter("price")));
            this.productDAO.update(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void redirectTo404(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
