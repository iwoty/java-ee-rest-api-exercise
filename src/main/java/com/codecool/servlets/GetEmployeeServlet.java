package com.codecool.servlets;

import com.codecool.DAO.ConnectionToDB;
import com.codecool.DAO.EmployeeDAO;
import com.codecool.models.Employee;
import com.codecool.services.EmployeeToJSON;
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

@WebServlet(urlPatterns = "/employees/*")
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
                EmployeeToJSON jsonObject= new EmployeeToJSON(this.employeeDAO.getByID(productID));
                String productToJSON = jsonObject.employeeToJSON();
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

        String employeeID = idParser.getIDFromURI(request);

        try {
            employeeDAO.delete(employeeDAO.getByID(employeeID));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doPut( HttpServletRequest request,
                          HttpServletResponse response) {

        String id = request.getParameter("id");
        try {
            Employee employee = this.employeeDAO.getByID(id);
            employee.setFirstName(request.getParameter("firstName"));
            employee.setLastName(request.getParameter("lastName"));
            employee.setShopID(Integer.parseInt(request.getParameter("shopID")));
            this.employeeDAO.update(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void redirectTo404(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
