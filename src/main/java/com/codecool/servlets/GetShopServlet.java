//package com.codecool;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
//
//@WebServlet(urlPatterns= {"/shops"})
//public class GetShopServlet extends HttpServlet {
//
//    protected void doGet(HttpServletRequest request,
//                         HttpServletResponse response)
//            throws ServletException, IOException {
//
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//        ShopToJSON productToJSON = new ShopToJSON();
//        try {
//            response.getWriter().write(productToJSON.shopToJSON());
//        } catch (InvocationTargetException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
//}
