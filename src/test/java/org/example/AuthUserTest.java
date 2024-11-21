package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.data.register.User;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class AuthUserTest extends BaseUserTest {
    @Override
    public void setUp() {
        super.setUp();
        registerClient.registerUserResponse(registerRequest);
    }

    @Test
    @DisplayName("Test Successful Login")
    @Description("Проверяем успешный логин курьера")
    public void testAuthUser() {
        ValidatableResponse response = authClient.loginUserResponse(user);
        response.statusCode(200);
        Assert.assertTrue("success", true);
    }

    @Test
    @DisplayName("Test Error Login")
    @Description("Проверяем что не будет авторизации с неверными данными")
    public void testErrorAuthUser() {
        ValidatableResponse response = authClient.loginUserResponse(
                new User(
                        RandomStringUtils.randomAlphabetic(10) + "@gmail.com",
                        RandomStringUtils.randomAlphabetic(8)
                ));
        response.statusCode(401)
                .body("message", equalTo("email or password are incorrect"));
    }
}
