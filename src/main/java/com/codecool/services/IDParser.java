package com.codecool.services;

import javax.servlet.http.HttpServletRequest;

public class IDParser {

    public IDParser(){}

    public String getIDFromURI(HttpServletRequest request) {

        String[] URI = request.getRequestURI().split("/");
        String productID = URI[URI.length - 1];
        return productID;
    }
}
