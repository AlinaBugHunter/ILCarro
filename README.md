# Automated Testing for Car Rental Web Application

This project focuses on the implementation of automated testing for a **Car Rental Web Application**. The web application allows users to search for, order, add, and remove cars available for rent. This repository contains both **UI tests** using **Selenium**, organized using the **Page Object Model (POM)**, and **API tests** using **OkHttp**.

The goal of this project is to ensure the functionality and reliability of the car rental service by automating the testing process, reducing manual effort, and ensuring the stability of key features. The project also utilizes **DTOs (Data Transfer Objects)** to handle data efficiently between the client and server in API testing.

## Technologies Used

![Java](https://img.shields.io/badge/Java-%23F7B731?style=flat&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-%232C3E50?style=flat&logo=apache-maven&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-%232255D3?style=flat&logo=testng&logoColor=white)
![Selenium WebDriver](https://img.shields.io/badge/Selenium_WebDriver-%23E4D03A?style=flat&logo=selenium&logoColor=white)
![Page Object Model](https://img.shields.io/badge/Page_Object_Model-%23333?style=flat&logo=none&logoColor=white)
![OkHttp](https://img.shields.io/badge/OkHttp-%238A3E2F?style=flat&logo=okhttp&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-%23000000?style=flat&logo=swagger&logoColor=white)
![DTO](https://img.shields.io/badge/DTO-%23007ACC?style=flat&logo=java&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-%23000?style=flat&logo=lombok&logoColor=white)
![Gson](https://img.shields.io/badge/Gson-%23D2B300?style=flat&logo=gson&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-%23000000?style=flat&logo=intellij-idea&logoColor=white)

- **Java**: Programming language used for writing tests.
- **Maven**: Build automation tool used for managing project dependencies.
- **TestNG**: Testing framework used for organizing and running tests.
- **Selenium WebDriver**: Framework for automating web UI interactions.
- **Page Object Model (POM)**: Design pattern to enhance maintainability of UI tests by abstracting web elements and actions into separate page objects.
- **OkHttp**: HTTP client for making API requests and validating responses.
- **Swagger**: Tool for API documentation and testing.
- **DTO (Data Transfer Object)**: Object used to transfer data between client and server in API tests efficiently.
- **Lombok**: Library that reduces boilerplate code (e.g., getters, setters, constructors).
- **Gson**: Library for serializing Java objects to JSON and deserializing JSON to Java objects.
- **IntelliJ IDEA**: Integrated development environment (IDE) used for Java development.

## Features
### UI Testing with Selenium
- **Search Functionality**: Verifies that users can search for available rental cars based on various filters.
- **Car Booking**: Ensures that users can successfully book a car for a defined period.
- **Add Cars**: Tests the functionality for adding new cars to the fleet.
- **Page Object Model**: Uses the POM pattern to structure the UI tests, making them easier to maintain and scale.

### API Testing with OkHttp
- **Get Car Details**: Ensures that the API correctly returns details for a specific rental car.
- **Add Cars**: Verifies the ability to add a new car to the rental fleet via the API.
- **Remove Cars**: Tests the API's ability to remove a car from the rental system.
- **DTO Usage**: Uses DTOs to manage and transfer car data efficiently between the client and server during API interactions.
