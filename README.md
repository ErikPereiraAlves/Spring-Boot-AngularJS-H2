

# Spring boot + AngularJS + H2 database  + Spring Security (oAUTH2)  + x.509 certificate authorization and authentication.

Test application that currently has the following entities: Product, User

Presentation tier developed with html and AngularJS.

Using oAUTH2 for some authentication, ie administration functionalities.

Project home page:

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
https://gigsterous.github.io/engineering/2017/03/01/spring-boot-4.html
http://www.baeldung.com/x-509-authentication-in-spring-security


## SSL certificates

    * the keystore in javax.net.ssl.keyStore is meant to contain your private keys and certificates,
            whereas the javax.net.ssl.trustStore is meant to contain the CA certificates you're willing to trust when a remote party presents its certificate.

    * Keystore is used by a server to store private keys, and truststore is used by third party client to store public keys provided by server to access.

    * If you are implementing SSL on Server side you need a KeyStore to store your server certificate and private key.

    * Keystore is used to store your credential (server or client) while truststore is used to store others credential (Certificates from CA).

    * Only difference between trustStore and keyStore is what they store and there purpose.
            In SSL handshake purpose of trustStore is to verify credentials and purpose of keyStore is to provide credential.

    * keyStore in Java stores private key and certificates corresponding to there public keys and require if you are SSL Server or SSL requires client authentication.

    * TrustStore stores certificates from third party, your Java application communicate or certificates signed by CA(certificate authorities like Verisign, Thawte, Geotrust or GoDaddy) which can be used to identify third party.



1. Generate a certificate using keygen command in windows (find keytool on Java Jdk installation and run CMD as admin):

keytool -genkey -keystore server.keystore -alias erik-x509certificate-20180216 -keyalg RSA -keysize 2048 -validity 3950

2. Self certify the certificate:

keytool -selfcert -alias erik-x509certificate-20180216 -keystore server.keystore -validity 3950

3. Export certificate to folder:

keytool -export -alias erik-x509certificate-20180216 -keystore server.keystore -rfc -file erik-x509certificate-20180216.cer

4. Import Certificate into client Truststore:

keytool -importcert -alias erik-x509certificate-20180216 -file C:\Program Files\Java\jdk-9.0.1\bin\erik-x509certificate-20180216.cer -keystore .truststore