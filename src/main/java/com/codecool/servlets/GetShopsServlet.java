//package com.codecool.servlets;
//
//import com.codecool.EmployeeToJSON;
//import com.codecool.ShopToJSON;
//import com.codecool.models.Shop;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
//import java.util.ArrayList;
//
//public class GetShopsServlet extends HttpServlet {
//
//    protected void doGet( HttpServletRequest request,
//                          HttpServletResponse response)
//            throws ServletException, IOException {
//
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//        ShopToJSON objectToJSON = new ShopToJSON();
//        ArrayList<Shop> shops;
//        for(Shop shop : shops) {
//            try {
//                response.getWriter().write(objectToJSON.shopToJSON());
//            } catch (InvocationTargetException | IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
