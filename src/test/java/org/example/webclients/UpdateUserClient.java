package org.example.webclients;

import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.example.util.Constants.USER_API;

public class UpdateUserClient {
    @Step("обновление данных пользователя")
    public ValidatableResponse updateUserResponse(@Nullable String token, @Nullable String newEmail, @Nullable String newName) {
        Map<String, Object> body = new HashMap<>();
        if (newEmail != null) body.put("email", newEmail);
        if (newName != null) body.put("name", newName);

        return given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .patch(USER_API.getConstant())
                .then();
    }
}
