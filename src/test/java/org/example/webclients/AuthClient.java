package org.example.webclients;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.data.register.User;

import static io.restassured.RestAssured.given;
import static org.example.util.Constants.LOGIN_API;

public class AuthClient {
    @Step("получение ответа при авторизации пользователя")
    public ValidatableResponse loginUserResponse(User user){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(LOGIN_API.getConstant())
                .then();
    }
}
