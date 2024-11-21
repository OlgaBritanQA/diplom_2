package org.example;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.data.register.RegisterRequest;
import org.example.data.register.User;
import org.example.webclients.AuthClient;
import org.example.webclients.RegisterClient;
import org.junit.After;
import org.junit.Before;

import static org.example.util.Constants.BASE_URL;

public class BaseTest {
    protected static RegisterClient registerClient;
    protected static AuthClient authClient;
    protected static String email;
    protected static String password;
    protected static String name;
    protected static RegisterRequest registerRequest;
    protected static User user;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL.getConstant();
        registerClient = new RegisterClient();
        authClient = new AuthClient();
        email = RandomStringUtils.randomAlphabetic(10) + "@gmail.com";
        password = RandomStringUtils.randomAlphabetic(8);
        name = RandomStringUtils.randomAlphabetic(20);
        registerRequest = new RegisterRequest(email, password, name);
        user = new User(email, password);
    }

    @After
    public void tearDown() {
        ValidatableResponse dataUser = authClient.loginUserResponse(user);
        String token = dataUser.extract().path("accessToken");

        if (token != null) {
            ValidatableResponse deleteResponse = registerClient.deleteUserResponse(token);
            deleteResponse.statusCode(202);
        }
    }
}
