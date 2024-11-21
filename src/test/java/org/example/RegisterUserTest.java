package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.data.register.RegisterRequest;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class RegisterUserTest extends BaseUserTest {
    @Test
    @DisplayName("Test Create User")
    @Description("Проверяем, что пользователь создан")
    public void testRegisterUserSuccess() {
        ValidatableResponse response = registerClient.registerUserResponse(registerRequest);
        response.statusCode(200)
                .assertThat()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Test Create Duplicate User")
    @Description("Проверяем, что пользователь с повторяющимися данными не создаётся")
    public void testRegisterDuplicate() {
        registerClient.registerUserResponse(registerRequest).statusCode(200);

        ValidatableResponse duplicateResponse = registerClient.registerUserResponse(registerRequest);
        duplicateResponse.statusCode(403)
                .body("message", equalTo("User already exists"));
    }

    @Test
    @DisplayName("Test Create User, without Email")
    @Description("Проверяем, что пользователь без почты не создаётся")
    public void testRegisterWithoutEmail() {
        ValidatableResponse duplicateResponse = registerClient.registerUserResponse(new RegisterRequest(password, name));
        duplicateResponse.statusCode(403)
                .body("message", equalTo("Email, password and name are required fields"));
    }
}
