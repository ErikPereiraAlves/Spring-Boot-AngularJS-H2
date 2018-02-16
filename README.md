

# Spring boot + AngularJS + H2 database  + Spring Security (oAUTH2)

Test application that currently has the following entities: Product, User

Presentation tier developed with html and AngularJS.

Using oAUTH2 for some authentication, ie administration functionalities.

Home page:

http://localhost:8080/store/index.html

## About oAUTH:

It has four main roles.

    Resource Owner (That means, You)
    Client (Means the application you’re using, that accesses your data on the resource server)
    Resource Server (Where your data are stored)
    Authorization Server (Responsible for authenticating your identity and gives you an authorization token, so that you can request resource server for your data with this token. this token is called access_token)

    access_token = Responsible for accessing your resources from the resource server. This token usually has a little validity time. Gets expired often.

    refresh_token = Whenever your access_token gets expired, you use the refresh_token to request a new access_token. The request is made to the request Authorization server, using your efresh token, client id, and client secret. Its validation is configurable.

     OAuth2 should be used with HTTPS because it requires the client to exchange sensitive information with the server (tokens or credentials).

### References:

https://jugbd.org/2017/09/19/implementing-oauth2-spring-boot-spring-security/
http://websystique.com/spring-boot/spring-boot-angularjs-spring-data-jpa-crud-app-example/
http://www.baeldung.com/spring-security-authentication-with-a-database