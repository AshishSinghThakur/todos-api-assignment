package com.typicode.jsonplaceholder.tests;



import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.typicode.jsonplaceholder.pages.UsersPage;
import com.typicode.jsonplaceholder.utilities.ExtentReport;

import java.io.IOException;


public class UserToDoTests {
	UsersPage usersPage = new UsersPage();

    @Test
    public void verifyUsersToDoCompletion() throws IOException, ParseException {
		ExtentTest test = ExtentReport.getTest();
		usersPage.verifyUsersToDoCompletion(test);
	} 
}