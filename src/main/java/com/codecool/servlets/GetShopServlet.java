package com.codecool.servlets;

import com.codecool.DAO.ConnectionToDB;
import com.codecool.DAO.EmployeeDAO;
import com.codecool.DAO.ShopDAO;
import com.codecool.models.Employee;
import com.codecool.models.Shop;
import com.codecool.services.EmployeeToJSON;
import com.codecool.services.IDParser;
import com.codecool.services.ShopToJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns= "/shops/*")
public class GetShopServlet extends HttpServlet {

    private Connection connection;
    private ShopDAO shopDAO;
    private IDParser idParser;
    private EmployeeDAO employeeDAO;

    public GetShopServlet() {
        this.connection = ConnectionToDB.getConnection();
        this.shopDAO = new ShopDAO(connection);
        this.idParser = new IDParser();
        this.employeeDAO = new EmployeeDAO(connection);
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String[] URI = request.getRequestURI().split("/");
        if(URI.length == 4 && URI[3].equals("employees")) {
            String shopID = URI[2];
            getEmployeesByShop(response, shopID);
        }else {

            String shopID = idParser.getIDFromURI(request);
            try {
                if(this.shopDAO.getByID(shopID) != null) {
                    ShopToJSON jsonObject = new ShopToJSON(this.shopDAO.getByID(shopID));
                    String shopToJSON = jsonObject.shopToJSON();
                    response.getWriter().write(shopToJSON);
                }else {
                    System.out.println("No such ID");
                }
            } catch (InvocationTargetException | IllegalAccessException | SQLException e) {
                e.printStackTrace();
            }
        }

    }

    protected void doDelete( HttpServletRequest request,
                             HttpServletResponse response)
            throws ServletException, IOException {

        String shopID = idParser.getIDFromURI(request);

        try {
            this.shopDAO.delete(shopDAO.getByID(shopID));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void doPut( HttpServletRequest request,
                          HttpServletResponse response) {

        String id = request.getParameter("id");
        try {
            Shop shop = this.shopDAO.getByID(id);
            shop.setLocation(request.getParameter("location"));
            this.shopDAO.update(shop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getEmployeesByShop(HttpServletResponse response, String shopID)
            throws ServletException, IOException {


        try {
            if (!this.employeeDAO.getEmployeesByShopID(shopID).isEmpty()) {
                List<Employee> employees = this.employeeDAO.getEmployeesByShopID(shopID);
                for (Employee employee : employees) {
                    EmployeeToJSON jsonObject = new EmployeeToJSON(employee);
                    response.getWriter().write(jsonObject.employeeToJSON());
                }
            } else {
                System.out.println("No such ID");
            }
        } catch (InvocationTargetException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
    }
}
