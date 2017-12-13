package com.codecool;

import com.codecool.models.Employee;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectToJSON {

    public String employeeToJSON(Employee employee) throws InvocationTargetException, IllegalAccessException{
        JSONObject jsonObject = new JSONObject();
        String employeeJSONObject = null;
        Method[] methods = Employee.class.getDeclaredMethods();
        for (Method method : methods) {
            if(method.getName().startsWith("get")) {
                String[] methodName = method.getName().split("get");
                jsonObject.append(methodName[1].toLowerCase(), method.invoke(employee));
            }
        }return employeeJSONObject;
    }
}
