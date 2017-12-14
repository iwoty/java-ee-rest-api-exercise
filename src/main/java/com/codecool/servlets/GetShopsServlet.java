package com.codecool.servlets;

import com.codecool.DAO.ConnectionToDB;
import com.codecool.DAO.ProductDAO;
import com.codecool.DAO.ShopDAO;
import com.codecool.ProductToJSON;
import com.codecool.ShopToJSON;
import com.codecool.models.Product;
import com.codecool.models.Shop;

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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/shops")
public class GetShopsServlet extends HttpServlet {

    private Connection connection;
    private ShopDAO shopDAO;

    public GetShopsServlet() {
        this.connection = ConnectionToDB.getConnection();
        this.shopDAO = new ShopDAO(connection);
    }

    protected void doGet( HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Shop> shops = this.shopDAO.readAll();
            PrintWriter writer = response.getWriter();

            for(Shop shop : shops) {
                try {
                    ShopToJSON jsonObject = new ProductToJSON(shop);
                    writer.write(jsonObject.shopToJSON());
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
            this.shopDAO.create(new Shop(request.getParameter("location")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
