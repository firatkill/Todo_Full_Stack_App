
# Todo Spring Boot Backend

A Spring Boot backend for a Todo application that provides endpoints for basic operations like creating, updating, deleting, and retrieving todos.

App is secured by spring boot security and jwt authentication.

---

## Technologies Used

- Spring Boot
- Spring Security
- MySQL Database
- JWT (JSON Web Token)
- Spring Data JPA (Java Persistence API)
- Spring Data JDBC (Java Database Connectivity)
- Spring Web
- Lombok

## Getting Started

### Prerequisites

- Java 11 or higher
- Spring Boot 3+
- MySQL Database

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/firatkill/Todo_fullStack_app.git
   ```

2. Navigate to the project directory:

   ```bash
   cd Todo_fullStack_app
   ```

3. Navigate to the backend project directory:

   ```bash
   cd backend_springboot
   ```

4. Build the project:

   ```bash
   mvn clean install
   ```

5. Run the application:

   ```bash
   mvn spring-boot:run
   ```

- The application automatically creates the database and is ready to use.

## Configuration

- Database configurations can be found in `application.properties`.
- JWT secret key is set in `JWTService.java` under `com/todoApp/todoApp/Security/JWT/JWTService.java`.

## Contribution

Pull requests are welcome.

---
# API Usage

## User Authentication Endpoints

### Register User

```http
  POST /api/auth/register
```

**Request Body:**

| Parameter    | Type     | Explanation                  |
| :----------- | :------- | :--------------------------- |
| `password`   | `string` | **Required** password        |
| `email`      | `string` | **Required** User's email|
| `username`   | `string` | username        |

---

### User Login


```http
  POST /api/auth/login
```
**Request Body:**

| Parameter    | Type     | Explanation                  |
| :----------- | :------- | :--------------------------- |
| `email`   | `string` | **Required** email        |
| `password`   | `string` | **Required** password        |


**Successful Response :**
Returns 'user' object that has 2 parameters:

| Parameter | Type     | Explanation                |
| :-------- | :------- | :-------------------------------- |
| `user_id` | `long` | User's id   |
| `name` | `string` | User's name   |
| `access_token` | `string` | JWT access token |


- For the first login, returns an user object with JWT access token. 
- *For all other requests*, **in the Request Header**, a jwt token must be provided with '**Authorization**' parameter and all other requests must be sent with this header:
- JWT Token ref Expires 10minutes after creation.


**In Request Headers:**

| Header | Value     | Explanation                |
| :-------- | :------- | :-------------------------------- |
| `Authorization` | `Bearer {token}` | **Required** JWT access token   |


---
---

## Todo Endpoints 

#### Create Todo

```http
  POST /api/user/todos
```

**Request Body:**

| Parameter    | Type     | Explanation                  |
| :----------- | :------- | :--------------------------- |
| `todoName`      | `string` | **Required** Todo name      |
| `todoDescription`| `string` | Todo description default null           |
| `todoColor`| `string` | Todo Color default white      |
| `date`| `string` | **Required** Todo date             |
| `completed`| `boolean` | Todo description default false            |
---

#### Get a Todo by ID

```http
  GET /api/user/todos/{todoId}
```

**Path Parameters:**

| Parameter  | Type     | Explanation          |
| :--------- | :------- | :------------------- |
| `todoId`   | `long`   | **Required** todo ID |

**Response Body:**

Returns a `TodoResponse` object:

| Parameter    | Type     | Explanation                  |
| :----------- | :------- | :--------------------------- |
| `id`      | `long` |  Todo id      |
| `todoName`      | `string` |  Todo name      |
| `todoDescription`| `string` | Todo description             |
| `todoColor`| `string` | Todo Color             |
| `date`| `string` |  Todo date             |
| `completed`| `boolean` | Todo description             |
| `createdAt`| `Instant` | Todo creation datetime             |
| `userId`| `long` | User's id             |


---

#### Get All Todos

```http
  GET /api/user/todos
```
**Response Body:**

Returns an array of `TodoResponse` objects:


---


#### Update a Todo

```http
  PUT /api/user/todos/{todoId}
```

**Path Parameters:**

| Parameter  | Type     | Explanation          |
| :--------- | :------- | :------------------- |
| `todoId`   | `long`   | **Required** todo ID |

**Request Body:**


| Parameter    | Type     | Explanation                  |
| :----------- | :------- | :--------------------------- |
| `todoName`      | `string` | **Required** Todo name      |
| `todoDescription`| `string` | Todo description default null           |
| `todoColor`| `string` | Todo Color default white      |
| `date`| `string` | **Required** Todo date             |
| `completed`| `boolean` | Todo description default false            |


**Response Body:**

Returns a `TodoResponse` object.

---

#### Delete a Todo

```http
  DELETE /api/user/todos/{todoId}
```

**Path Parameters:**

| Parameter  | Type     | Explanation          |
| :--------- | :------- | :------------------- |
| `todoId`   | `long`   | **Required** todo ID |

---
