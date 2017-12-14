package com.codecool.servlets;

import com.codecool.DAO.ConnectionToDB;
import com.codecool.DAO.EmployeeDAO;
import com.codecool.DAO.ProductDAO;
import com.codecool.ProductToJSON;
import com.codecool.services.IDParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessControlException;
import java.sql.Connection;
import java.sql.SQLException;

public class GetEmployeeServlet extends HttpServlet {


    private Connection connection;
    private EmployeeDAO employeeDAO;
    private IDParser idParser;

    public GetEmployeeServlet() {
            this.connection = ConnectionToDB.getConnection();
            this.employeeDAO = new EmployeeDAO(connection);
            this.idParser = new IDParser();
        }

    protected void doGet( HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String productID = idParser.getIDFromURI(request);

        try {
            if(this.employeeDAO.getByID(productID) != null) {
                ProductToJSON jsonObject= new ProductToJSON(this.employeeDAO.getByID(productID));
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

        String employeeID = idParser.getIDFromURI(request);

        try {
            employeeDAO.delete(employeeDAO.getByID(productID));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
