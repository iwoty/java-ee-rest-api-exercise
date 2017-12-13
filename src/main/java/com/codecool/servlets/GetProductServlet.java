package com.codecool.servlets;

import com.codecool.ProductToJSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class GetProductServlet extends HttpServlet {

    protected void doGet( HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        ProductToJSON productToJSON = new ProductToJSON();
        try {
            response.getWriter().write(productToJSON.productToJSON());
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
