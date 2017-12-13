package com.codecool.servlets;

import com.codecool.DAO.ConnectionToDB;
import com.codecool.DAO.EmployeeDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class GetEmployeesServlet extends HttpServlet {

    private Connection connection;
    private EmployeeDAO employeeDAO;

    public GetEmployeesServlet() {
        this.connection = ConnectionToDB.getConnection();
//        this.employeeDAO = new EmployeeDAO(connection);
    }

    protected void doGet( HttpServletRequest request,
                             HttpServletResponse response)
            throws ServletException, IOException {

//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//
//        EmployeeToJSON objectToJSON = new EmployeeToJSON();
//
//        try {
//
//            response.getWriter().write(objectToJSON.employeeToJSON());
//        } catch (InvocationTargetException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }

}
