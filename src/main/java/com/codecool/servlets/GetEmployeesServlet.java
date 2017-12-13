package com.codecool.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/employees")
public class GetEmployeesServlet extends HttpServlet {
    protected void doGet( HttpServletRequest request,
                             HttpServletResponse response)
            throws ServletException, IOException {

        response.getWriter().write("<html><body>GETTTTT response</body></html>");
    }
}
