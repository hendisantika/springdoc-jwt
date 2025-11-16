# SpringBoot 3: API token Authentication using JWT and Swagger UI

A Spring Boot 3 application demonstrating JWT-based authentication with interactive API documentation using SpringDoc
OpenAPI (Swagger UI).

## Prerequisites

- Java 21 or higher
- Maven 3.6+

## Technologies Used

- Spring Boot 3.5.7
- Spring Security 6
- SpringDoc OpenAPI 2.8.14
- JWT (JSON Web Token) 0.13.0
- H2 Database
- Lombok
- RabbitMQ

## What is Token Authentication?

For a user to access a resource, he must first authenticate through an authentication server to get a token. This token
is then used to access the resource APIs for a specified period of time.

### Spring Boot 3

is the newest version of the popular Spring Framework 6

Some points to note are the below:

1. It supports newer java versions >17
2. Migration from Java EE to Jakarta EE9, where the package namespace changed from javax to jakarta.
3. Hibernate 6 is supported with improved SQL, Performance and Modern JDK Support.

SpringBoot is widely used for Rapid Application Development, mostly in Microservices architecture.

It is easy to create Rest Controllers and expose business logic to the client through API methods.

Consider,

we want to secure our API methods, by forcing the client Application to first authenticate and then use our APIs, by
passing a token each time.

For this scenario, we will use
a [JWT token](https://cheatsheetseries.owasp.org/cheatsheets/JSON_Web_Token_for_Java_Cheat_Sheet.html) as we are not
going to use the system default session management, but rather a stateless environment for executing API calls.

You can take a look
at [OWASP Cheatsheet](https://owasp.deteact.com/cheat/cheatsheets/JSON_Web_Token_Cheat_Sheet_for_Java.html) for more
detailed information.

### JWT tokens
> JSON Web Token (JWT) is an open standard (RFC 7519) that defines a compact and self-contained way for securely
> transmitting
> information between parties as a JSON object. This information can be verified and trusted because it is digitally
> signed.
> JWTs can be signed using a secret (with the HMAC algorithm) or a public/private key pair using RSA.¹

### JWT Advantages

1. Tokens are small and can be passed between two entities quite easily.
2. Can be generated from anywhere, and they don't need to be verified on your server.
3. They can hold user permission information and expiration, related to the user access to the resources and for how
   long.

A major disadvantage is that if a JWT key is compromised, the entire system will be at risk.

## Building and Running the Application

### Build the Project

```bash
mvn clean install
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will start on port 8080.

### Access Swagger UI

Once the application is running, you can access the Swagger UI at:

```
http://localhost:8080/swagger-ui/index.html
```

or

```
http://localhost:8080/swagger-ui
```

### Default Users

The application comes with three pre-configured users:

| Username | Password | Roles       |
|----------|----------|-------------|
| user     | password | USER        |
| admin    | password | USER, ADMIN |
| yuji     | password | USER, ADMIN |

## API Endpoints

### Public Endpoints (No Authentication Required)

- `POST /api/v1/auth/authenticate` - Authenticate and get JWT token
- `/swagger-ui/**` - Swagger UI documentation
- `/v3/api-docs/**` - OpenAPI documentation

### Protected Endpoints (Authentication Required)

- `GET /api/books` - Get list of books
- Other API endpoints as defined in the controllers

### Open API Swagger

> Swagger UI allows anyone — be it your development team or your end consumers — to visualize and interact with the
> API's
> resources without having any of the implementation logic in place. It's automatically generated from your OpenAPI
> (formerly known as Swagger) Specification, with the visual documentation making it easy for back end implementation
> and client side consumption.²

![Swagger UI](img/Swagger-UI.png "Swagger UI")

## Token Authentication Flow

Trying to call a secured API will result in a Forbidden 403 response.

![403](img/403.png "403")

Let's go through the process:

### Step 1: Authenticate to Get JWT Token

Call the `/api/v1/auth/authenticate` endpoint with the following JSON payload:

```json
{
  "email": "yuji",
  "password": "password"
}
```

A successful response with correct login credentials should return a JWT token:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjc2NjU4MDc3LCJleHAiOjE2NzY2NTk1MTd9.m0EdnpStRzndsLaOki29OOuxPSFnkdHP-KPi80ftqrc"
}
```

### Step 2: Authorize in Swagger UI

Click the **Authorize** button in Swagger UI. This will add the JWT token as a Bearer Authentication token in each
request's header.

![Authenticate](img/authenticate.png "Authenticate")

![Authenticate](img/authenticate2.png "Authenticate")

![Authorize](img/authorize.png "Authorize")

### Step 3: Access Protected Endpoints

Call any secured API endpoint (e.g., to get book information). With a valid JWT token, you should receive a success
response (200 OK) with the requested data.

![200](img/200.png "Call secured API to get the information of a book")

As shown above, there is a success response 200 along with the Book JSON object.

## Development Notes

### Fixed Issues

1. **Lombok Configuration**: Added Maven compiler plugin configuration for proper Lombok annotation processing
2. **Spring Security**: Updated deprecated `sessionManagement()` method to use the new lambda-based configuration
3. **Field Naming**: Fixed field name casing in the Book model for consistency

### Security Configuration

The application uses JWT-based stateless authentication with the following features:

- CORS disabled (can be configured in `WebSecurityConfig`)
- CSRF disabled (suitable for stateless JWT authentication)
- Session management set to STATELESS
- JWT filter added before UsernamePasswordAuthenticationFilter

## Contributing

Feel free to submit issues or pull requests to improve the project.

## License

This project is open source and available for educational purposes.
