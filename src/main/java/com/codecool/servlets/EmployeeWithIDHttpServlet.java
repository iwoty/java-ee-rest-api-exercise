package com.codecool.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns= {"/employees/"})
public class EmployeeWithIDHttpServlet extends HttpServlet {

    protected void doDelete( HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doPut( HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

    }
}