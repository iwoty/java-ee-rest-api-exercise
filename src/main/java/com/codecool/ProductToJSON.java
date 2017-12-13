package com.codecool;

import com.codecool.models.Product;
import org.json.JSONObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProductToJSON {

    public String productToJSON(Product product) throws InvocationTargetException, IllegalAccessException{
        JSONObject jsonObject = new JSONObject();
        String employeeJSONObject = null;
        Method[] methods = Product.class.getDeclaredMethods();
        for (Method method : methods) {
            if(method.getName().startsWith("get")) {
                String[] methodName = method.getName().split("get");
                jsonObject.append(methodName[1].toLowerCase(), method.invoke(product).toString());
                employeeJSONObject = jsonObject.toString();
            }
        }return employeeJSONObject;
    }
}
