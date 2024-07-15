package com.typicode.jsonplaceholder.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.junit.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.typicode.jsonplaceholder.utilities.RestAssuredBuilder;

import io.restassured.response.Response;

public class UsersPage {
    RestAssuredBuilder restBuilder = new RestAssuredBuilder();

    public void verifyUsersToDoCompletion(ExtentTest test) throws IOException, ParseException {
        test.info("Fetching users from the API.");
        Response response = restBuilder.getUsers();
        List<Map<String, Object>> users = response.jsonPath().getList("");
        test.info("Users fetched successfully.");

        test.info("Filtering users from the city 'FanCode'.");
        List<Map<String, Object>> fanCodeUsers = new ArrayList<>();
        for (Map<String, Object> user : users) {
            Map<String, Object> address = (Map<String, Object>) user.get("address");
            Map<String, String> geo = (Map<String, String>) address.get("geo");
            double lat = Double.parseDouble(geo.get("lat"));
            double lng = Double.parseDouble(geo.get("lng"));

            if (lat > -40 && lat < 5 && lng > 5 && lng < 100) {
                fanCodeUsers.add(user);
            }
        }
        test.info("Filtered users: " + fanCodeUsers.size());

        test.info("Fetching todos from the API.");
        Response todoResponse = restBuilder.getToDos();
        List<Map<String, Object>> todos = todoResponse.jsonPath().getList("");
        test.info("Todos fetched successfully.");

        test.info("Verifying task completion for each FanCode user.");
        for (Map<String, Object> user : fanCodeUsers) {
            int userId = (Integer) user.get("id");
            List<Map<String, Object>> userTodos = new ArrayList<>();
            for (Map<String, Object> todo : todos) {
                if ((Integer) todo.get("userId") == userId) {
                    userTodos.add(todo);
                }
            }
            int completedCount = 0;
            for (Map<String, Object> todo : userTodos) {
                if ((Boolean) todo.get("completed")) {
                    completedCount++;
                }
            }
            boolean isMoreThanHalfCompleted = completedCount > userTodos.size() / 2;
            test.info("User ID: " + userId + ", Completed Todos: " + completedCount + ", Total Todos: " + userTodos.size() + ", More than 50% completed: " + isMoreThanHalfCompleted);
            Assert.assertTrue("User ID: " + userId + " should have more than 50% tasks completed.", isMoreThanHalfCompleted);
        }
        test.info("Verification completed successfully.");
    }
}