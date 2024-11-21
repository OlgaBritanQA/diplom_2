package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class UpdateUserTest extends BaseUserTest {
    private String newEmail;
    private String newName;

    @Override
    public void setUp() {
        super.setUp();
        registerClient.registerUserResponse(registerRequest);
        newEmail = "new_" + email;
        newName = "updated_" + name;
    }

    @Test
    @DisplayName("Update Both Email and Name with Authorization")
    @Description("Проверяем, что авторизованный пользователь может изменить как имя, так и почту.")
    public void testUpdateEmailAndNameWithAuthorization() {
        ValidatableResponse authResponse = authClient.loginUserResponse(user);
        String token = authResponse.extract().path("accessToken");

        ValidatableResponse response = updateUserClient.updateUserResponse(token, newEmail, newName);
        response.statusCode(200)
                .assertThat()
                .body("success", equalTo(true))
                .body("user.email", equalTo(newEmail.toLowerCase()))
                .body("user.name", equalTo(newName));
    }

    @Test
    @DisplayName("Update Only Email with Authorization")
    @Description("Проверяем, что авторизованный пользователь может изменить только почту.")
    public void testUpdateEmailOnlyWithAuthorization() {
        ValidatableResponse loginResponse = authClient.loginUserResponse(user);
        String token = loginResponse.extract().path("accessToken");

        ValidatableResponse response = updateUserClient.updateUserResponse(token, newEmail, null);
        response.statusCode(200)
                .assertThat()
                .body("success", equalTo(true))
                .body("user.email", equalTo(newEmail.toLowerCase()))
                .body("user.name", equalTo(name));
    }

    @Test
    @DisplayName("Update Only Name with Authorization")
    @Description("Проверяем, что авторизованный пользователь может изменить только имя.")
    public void testUpdateNameOnlyWithAuthorization() {
        ValidatableResponse loginResponse = authClient.loginUserResponse(user);
        String token = loginResponse.extract().path("accessToken");

        ValidatableResponse response = updateUserClient.updateUserResponse(token, null, newName);
        response.statusCode(200)
                .assertThat()
                .body("success", equalTo(true))
                .body("user.email", equalTo(email.toLowerCase()))
                .body("user.name", equalTo(newName));
    }

    @Test
    @DisplayName("Attempt to Update Both Email and Name without Authorization")
    @Description("Проверяем, что неавторизованный пользователь не может изменить имя и почту одновременно.")
    public void testUpdateEmailAndNameWithoutAuthorization() {
        ValidatableResponse response = updateUserClient.updateUserResponse("", newEmail, newName);
        response.statusCode(401)
                .assertThat()
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }

    @Test
    @DisplayName("Attempt to Update Only Email without Authorization")
    @Description("Проверяем, что неавторизованный пользователь не может изменить только почту.")
    public void testUpdateEmailOnlyWithoutAuthorization() {
        ValidatableResponse response = updateUserClient.updateUserResponse("", newEmail, null);
        response.statusCode(401)
                .assertThat()
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }

    @Test
    @DisplayName("Attempt to Update Only Name without Authorization")
    @Description("Проверяем, что неавторизованный пользователь не может изменить только имя.")
    public void testUpdateNameOnlyWithoutAuthorization() {
        ValidatableResponse response = updateUserClient.updateUserResponse("", null, newName);
        response.statusCode(401)
                .assertThat()
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }
}
