package com.typicode.jsonplaceholder.utilities;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class RestAssuredBuilder {
	String appId = "";
	String baseURI = "http://jsonplaceholder.typicode.com";
	String usersURI = baseURI + "/users";
	String todoURI = baseURI + "/todos";


	public Response getUsers() throws IOException, ParseException {
		Response response = given().get(usersURI);
		assert response.getStatusCode() == 200;
		return response;
	}
	
	public Response getToDos() throws IOException, ParseException {
		Response response = given().get(todoURI);
		assert response.getStatusCode() == 200;
		return response;
	}
}