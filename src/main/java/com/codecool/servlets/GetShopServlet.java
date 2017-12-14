package com.codecool.servlets;

import com.codecool.DAO.ConnectionToDB;
import com.codecool.DAO.ProductDAO;
import com.codecool.ShopToJSON;
import com.codecool.services.IDParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns= "/shops/*")
public class GetShopServlet extends HttpServlet {

    private Connection connection;
    private ProductDAO shopDAO;
    private IDParser idParser;

    public GetShopServlet() {
        this.connection = ConnectionToDB.getConnection();
        this.shopDAO = new shopDAO(connection);
        this.idParser = new IDParser();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String shopID = idParser.getIDFromURI(request);

        try {
            if(this.shopDAO.getByID(shopID) != null) {
                ShopToJSON jsonObject = new ShopToJSON(this.shopDAO.getByID(shopID));
                String shopToJSON = jsonObject.shopToJSON();
                response.getWriter().write(shopToJSON);
            }else {
                System.out.println("dupa");
            }
        } catch (InvocationTargetException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
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
}
