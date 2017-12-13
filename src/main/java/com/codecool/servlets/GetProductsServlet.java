package com.codecool.servlets;

import com.codecool.ProductToJSON;
import com.codecool.models.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class GetProductsServlet extends HttpServlet {

    protected void doGet( HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        ProductToJSON productToJSON = new ProductToJSON();
        ArrayList<Product> products;
        for(Product product : products) {
            try {
                response.getWriter().write(productToJSON.productToJSON());
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
