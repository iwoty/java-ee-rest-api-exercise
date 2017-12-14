package com.codecool.servlets;

import com.codecool.DAO.ConnectionToDB;
import com.codecool.DAO.EmployeeDAO;
import com.codecool.services.EmployeeToJSON;
import com.codecool.models.Employee;

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

@WebServlet(urlPatterns = "/employees")
public class GetEmployeesServlet extends HttpServlet {

    private Connection connection;
    private EmployeeDAO employeeDAO;

    public GetEmployeesServlet() {
        this.connection = ConnectionToDB.getConnection();
        this.employeeDAO = new EmployeeDAO(connection);
    }

    protected void doGet( HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Employee> employees = this.employeeDAO.readAll();
            PrintWriter writer = response.getWriter();

            for(Employee employee : employees) {
                try {
                    EmployeeToJSON jsonObject = new EmployeeToJSON(employee);
                    writer.write(jsonObject.employeeToJSON());
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
            this.employeeDAO.create(new Employee(request.getParameter("firstName"),
                                    (request.getParameter("lastName")),
                                    (Integer.parseInt(request.getParameter("shopID")))));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
