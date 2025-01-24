
# CRUD API with JWT Authentication

## Project Description

This project implements a **CRUD** application with user authentication that allows:

- **User registration**  
- **User login**  
- **Viewing a list of users**  
- **Deleting users by ID**  
- **Updating user details**  

Access to the endpoints for **viewing the user list**, **deleting a user**, and **updating user details** requires **authorization** using a **JWT** (JSON Web Token).  

The application uses a **PostgreSQL** database running in a **Docker** container, making it easy to set up and manage the environment.

---

## Requirements

- **Docker**: For running the application and database containers.  
- **Postman**: For testing API endpoints.  
- **Java 17**: Required version of Java.  
- **Maven**: For building the application.  
- **Spring Boot**: Framework for developing the backend application.

---

## Setup Instructions

### Step 1: Prepare the Environment

1. Make sure you have installed:
   - Docker  
   - Java 17  
   - Maven  
   - Postman  
2. Clone the project to your desired directory on your computer.

---

### Step 2: Start PostgreSQL Database in Docker

1. Open a terminal and navigate to the project directory (where the `pom.xml` file is located):  

```bash
cd "path/to/project"
```

2. Start the PostgreSQL database container:  

```bash
docker-compose up -d db
```

If the container starts successfully, the database will be ready.

---

### Step 3: Build and Run the Application

1. While in the project directory (where the `pom.xml` file is located), build the application using Maven:  

```bash
./mvnw clean install -DskipTests
```

2.Navigate to the `target` directory (inside the project folder). Before starting the application, make sure the .jar file has been built successfully. Then, start the application with the following command::

```bash
java -jar AuthCrudApiApp-0.0.1-SNAPSHOT.jar
```

---

### Step 4: Test the API with Postman

1. Open Postman and navigate to `http://localhost:8080` to use the available endpoints.

#### User Registration Endpoint:
`POST http://localhost:8080/register`  
In the body, provide the following data:
```json
{
  "username": "your_username",
  "password": "your_password"
}
```

#### User Login Endpoint:
`POST http://localhost:8080/login`  
In the body, provide the following data:
```json
{
  "username": "your_username",
  "password": "your_password"
}
```

After successful login, you will receive a JWT token, which acts as a key for accessing other endpoints.

#### Retrieve All Users Endpoint:
`GET http://localhost:8080/users`

#### Delete a User by ID Endpoint:
`DELETE http://localhost:8080/users/3`  
This will delete the user with ID `3` (example).

#### Update a User by ID Endpoint:
`PUT http://localhost:8080/users/3`  
In the body, provide the following data:
```json
{
  "username": "new_username",
  "password": "new_password"
}
```

---

### Configuration

To modify variables such as the JWT token secret or database connection details, you can edit the configuration file (`application.properties`).  
The `application.properties` file is located in `/target/classes`.

---

### Technologies Used

- **Spring Boot**: For creating the web application and managing the backend.  
- **JWT**: For user authentication and authorization.  
- **PostgreSQL**: As the database for storing user data.  
- **Docker**: For running the application and database in containers.  
- **Maven**: For managing dependencies and building the project.
