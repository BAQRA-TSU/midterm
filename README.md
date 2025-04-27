# Stugement Project
A Spring Boot application that connects students with courses, allowing students to be enrolled in one or more courses. The application provides basic CRUD operations for students and courses, and a simple web interface for interacting with the data.

Table of Contents
Prerequisites

Project Setup

Running the Application

Project Structure

Endpoints

Database Setup

Usage

Acknowledgements

Prerequisites
Before running this application, make sure you have the following installed:

Java 17 or later

Maven

Spring Boot (Maven will download dependencies automatically)

Postman or a browser to test API endpoints

Project Setup
Clone the Repository

First, clone the repository to your local machine:

bash
Copy
Edit
git clone https://github.com/BAQRA-TSU/midterm.git
cd stugement
Build the Project

The project uses Maven for dependency management. To build the project, run the following command:

bash
Copy
Edit
mvn clean install
This will download the required dependencies and compile the project.

Database Configuration

The project uses an H2 database (file-based). The database configuration is already set in application.yml (or application.properties, depending on your preference). The database file will be stored in the project's data folder.

Running the Application
Once the project is set up, you can run the application with the following command:

bash
Copy
Edit
mvn spring-boot:run
This will start the Spring Boot application on http://localhost:8080.

Access the Application
Frontend Pages: Open http://localhost:8080 in your browser.

API Endpoints: Use Postman or your browser to test API endpoints.

Project Structure
Here is an overview of the project structure:

controller: Contains the Spring MVC controllers that handle HTTP requests.

entity: Contains the entity classes (Student, Course).

repository: Contains the repository interfaces for accessing data from the database.

static: Contains the static assets like html.

application.yml: Configuration file for application settings and database connection.

Endpoints
1. View All Students
   URL: GET /student

Description: Displays a list of all students.

Response: A list of students.

2. Add New Student
   URL: POST /student

Description: Displays a form to add a new student.

Response: A form with fields to enter student details and select courses.

URL: POST /student

Description: Adds a new student with selected courses.

Request Body: Form data (name, age, selected course).

Response: Redirects to the list of students.

3. Add New Course
   URL: POST /course

Description: Displays a form to add a new course.

Response: A form to enter course details (title, description).

URL: POST /course

Description: Adds a new course.

Request Body: Form data (title, description).

Response: Redirects to the list of courses.


Database Setup
This project uses an H2 database configured with a file-based mode. The database configuration is stored in src/main/resources/application.yml. The data is persisted in the data folder within the project.

Default Database Configuration in application.yml
yaml
spring:
datasource:
url: jdbc:h2:./data/students_courses_db
driverClassName: org.h2.Driver
username: sa
password:
dialect: org.hibernate.dialect.H2Dialect
h2:
console:
enabled: true
jpa:
hibernate:
ddl-auto: update
show-sql: true
properties:
hibernate:
format_sql: true
Usage
Run the Application using mvn spring-boot:run.

Access the Student Management Interface:

Navigate to http://localhost:8080 in your browser to view the students.

Use the form to add new students and courses.

Testing API Endpoints (Optional):

Use Postman or any HTTP client to test API endpoints.

For Adding Students via Postman, make sure to set the Content-Type header to application/json for JSON data.

Acknowledgements
Spring Boot for making it easy to set up and configure the application.

Thymeleaf for rendering dynamic HTML templates.

H2 Database for a lightweight, file-based database.

Contributions
Feel free to fork this repository and submit pull requests for any improvements!

