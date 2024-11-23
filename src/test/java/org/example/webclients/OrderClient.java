package org.example.webclients;

import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.example.util.Constants.ORDER_API;

public class OrderClient {
    @Step("получение ответа при создании заказа")
    public ValidatableResponse createOrderResponse(@Nullable String token, @Nullable List<String> ingredients) {
        Map<String, Object> requestBody = new HashMap<>();

        if (ingredients != null && !ingredients.isEmpty()) {
            requestBody.put("ingredients", ingredients);
        }

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", token == null ? "Bearer " : token)
                .body(requestBody)
                .when()
                .post(ORDER_API.getConstant())
                .then();
    }

    @Step("получение заказов")
    public ValidatableResponse getOrders(@Nullable String token) {
        return given()
                .header("Authorization", token == null ? "Bearer " : token)
                .when()
                .get(ORDER_API.getConstant())
                .then();
    }
}
