package org.example;

import io.restassured.response.ValidatableResponse;
import org.example.webclients.OrderClient;

public class BaseOrderTest extends BaseTest {
    protected static OrderClient orderClient;
    protected String token;

    @Override
    public void setUp() {
        super.setUp();
        registerClient.registerUserResponse(registerRequest);
        orderClient = new OrderClient();
        ValidatableResponse dataUser = authClient.loginUserResponse(user);
        token = dataUser.extract().path("accessToken");
    }
}
