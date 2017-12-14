package com.codecool.servlets;

import com.codecool.DAO.ConnectionToDB;
import com.codecool.DAO.ProductDAO;
import com.codecool.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

//@WebServlet(urlPatterns= {"/products/"})
public class EditProductServlet extends HttpServlet {

    private Connection connection;
    private ProductDAO productDAO;

    public EditProductServlet() {
        this.connection = ConnectionToDB.getConnection();
        this.productDAO = new ProductDAO(connection);
    }

    protected void doPut( HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {


        System.out.println(request.getRequestURI());
        String[] URI = request.getRequestURI().split("/");
        for(String chuj: URI){

        System.out.println(chuj);
        }
        String productID = URI[URI.length-1].split("?")[0];
//        System.out.println(productID);
//        for(String key : request.getParameterMap().keySet()) {
//            if(key.equals("name")) {
//
//            }
//        }
        System.out.println(request.getParameter("name"));
        System.out.println(request.getParameter("price"));
    }
}