package com.codecool.servlets;

import com.codecool.EmployeeToJSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class GetEmployeeServlet extends HttpServlet {

    protected void doGet( HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        EmployeeToJSON objectToJSON = new EmployeeToJSON();
        try {
            response.getWriter().write(objectToJSON.employeeToJSON());
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
