package com.erikalves.application.oauth2;


import com.erikalves.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;


/*
Authorization Server (Responsible for authenticating your identity and gives you an authorization token,
so that you can request resource server for your data with this token. this token is called access_token)

          OAuth2 configuration.
 */

@Configuration  // This annotation indicates that the annotated class contains bean definitions that will be loaded into Spring context
@EnableAuthorizationServer  //let us use this application as an auth server
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {


    //This is a Spring bean for handling the authenticated requests.
    @Autowired
    private AuthenticationManager authenticationManager;

    // This is the UserDetailsService implementation.
    //This will enable us to use the users from our database in our auth server.
    @Autowired
    private UserOauthService userOauthService;

    @Value("${gigy.oauth.tokenTimeout:3600}")
    private int expiration;



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userOauthService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    /*
      So far using in-memory client detail. TODO: retrieve those from database.

    There are four different grant types defined by OAuth2. These grant types define interactions between the client and the auth/resource server:


    1- Authorisation code - redirection-based flow for confidential client, the client communicates with the server via user-agent (web browser etc.), typical for web servers

    2- Implicit - typically used with public clients running in a web browser using a scripting language, does not contain refresh tokens, this grant does not contain authentication and relies on redirection URI specified during client registration to the auth server

    3- Resource owner password credentials - used with trusted clients (e.g. clients written by the same company that owns the auth server), user credentials are passed to the client and then to the auth server and exchanged for access and refresh tokens

    4- Client credentials - used when the client itself is the resource owner (one client does not operate with multiple users), client credentials are exchanged directly for the tokens

     */


    //configures the clients (applications connecting to the server)
    // Creating only one client: in-memory client called ‘vendas-client’
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("vendas-client")  //CLIENT_ID
                .authorizedGrantTypes("client-credentials", "password","refresh_token")
                .authorities("ROLE_USER")
                .scopes("read", "write", "trust")
                .resourceIds("oauth2-resource")  //resource
                .accessTokenValiditySeconds(expiration)
                .secret("vendas-secret").refreshTokenValiditySeconds(50000);  //CLIENT_SECRET
    }




    //This one is used to hook up the users into the auth server (these come from our database - or from our in-memory mock just created earlier)
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.authenticationManager(authenticationManager).allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

        endpoints.userDetailsService(userOauthService);

    }


}