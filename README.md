# SpringBoot 3: API token Authentication using JWT and Swagger UI

### What is Token Authentication?

For a user to access a resource, he must first authenticate through an authentication server to get a token. This token
is then used to access the resource APIs for a specified period of time.

### Spring Boot 3

is the newest version of the popular Spring Framework 6

Some points to note are the below …

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
detailed information …

**JWT tokens**
> JSON Web Token (JWT) is an open standard (RFC 7519) that defines a compact and self-contained way for securely
> transmitting
> information between parties as a JSON object. This information can be verified and trusted because it is digitally
> signed.
> JWTs can be signed using a secret (with the HMAC algorithm) or a public/private key pair using RSA.¹

### JWT Advantages

1. Tokens are small and can be passed between two entities quite easily.
2. Can be generated from anywhere, and they don’t need to be verified on your server.

3. They can hold user permission information and expiration, related to the user access to the resources and for how
   long.

A major disadvantage is that if a JWT key is compromised, the entire system will be at risk.

### Open API Swagger

> Swagger UI allows anyone — be it your development team or your end consumers — to visualize and interact with the
> API’s
> resources without having any of the implementation logic in place. It’s automatically generated from your OpenAPI
> (formerly known as Swagger) Specification, with the visual documentation making it easy for back end implementation
> and client side consumption.²

![Swagger UI](img/Swagger-UI.png "Swagger UI")

### Token Authentication Flow

Trying to call a secured API, will result in a Forbidden 403 response.

![403](img/403.png "403")

Let's go through the process …

Step 1. Call /authenticate method with input

```shell
{
  "email": "yuji",
  "password": "password"
}
```

A successful response with correct login credentials should give the expected JWT token. Sample below…

```shell
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjc2NjU4MDc3LCJleHAiOjE2NzY2NTk1MTd9.m0EdnpStRzndsLaOki29OOuxPSFnkdHP-KPi80ftqrc"
}
```

Step 2. Click Authorize button. This will add JWT token as a Bearer Authentication token in each request’s header

Authenticate

![Authenticate](img/authenticate.png "Authenticate")

![Authenticate](img/authenticate2.png "Authenticate")

![Authorize](img/authorize.png "Authorize")

Step 3. Call secured API to get the information of a book

![200](img/200.png "Call secured API to get the information of a book")

As it is shown above, there is a success response 200 along with the Book JSON object.
