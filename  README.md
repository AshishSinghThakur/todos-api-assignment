# OpenWeatherMap API Tests

This project contains api automated test to verify todo task completion of FanCode Users.

## Setup

### Prerequisites

- Java Development Kit (JDK) installed (version 8 or higher)
- Maven
- IDE (e.g., IntelliJ IDEA, Eclipse) with Maven support
- Browsers (Chrome, firefox, etc.)

### Dependencies

- TestNG: For test execution and assertions
- RestAssured: For API testing and validation
- Extent Report: For Reporting

## Running the Tests

1. **Open the project in your IDE:**
   - Open your preferred IDE (e.g., IntelliJ IDEA, Eclipse) and import the project.

2. **Run the tests:**
     - Right-click on `testNG.xml`.
     - Select TestNG Suite.
     - Using Maven (command line):
     `mvn clean test` or `mvn -DsuiteXmlFile=testng.xml test`

4. **View Test Results:**
   - Test results can be viewed by opening the `api-assignment/extent-reports.html' file in any browser.

## Troubleshooting

### Dependencies:

Ensure that all dependencies specified in `pom.xml` are downloaded and available in your Maven repository.