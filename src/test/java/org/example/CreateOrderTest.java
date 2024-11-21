package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.example.util.Constants.BUN_R2_D3;
import static org.example.util.Constants.MEAT_PROTOSTOMIA;
import static org.hamcrest.Matchers.equalTo;

public class CreateOrderTest extends BaseOrderTest {
    @Test
    @DisplayName("Create Order with Authorization and Ingredients")
    @Description("Проверяем, что заказ можно создать с авторизацией и ингредиентами.")
    public void testCreateOrderWithAuthorizationAndIngredients() {
        List<String> ingredients = Arrays.asList(BUN_R2_D3.getConstant(), MEAT_PROTOSTOMIA.getConstant());

        ValidatableResponse response = orderClient.createOrderResponse(token, ingredients);
        response.statusCode(200)
                .assertThat()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Create Order without Authorization but with Ingredients")
    @Description("Проверяем, что можно создать заказ без авторизации, но с ингредиентами.")
    public void testCreateOrderWithoutAuthorizationWithIngredients() {
        List<String> ingredients = Arrays.asList(BUN_R2_D3.getConstant(), MEAT_PROTOSTOMIA.getConstant());

        ValidatableResponse response = orderClient.createOrderResponse(null, ingredients);
        response.statusCode(200)
                .assertThat()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Create Order with Authorization but without Ingredients")
    @Description("Проверяем, что нельзя создать заказ без ингредиентов даже с авторизацией.")
    public void testCreateOrderWithAuthorizationWithoutIngredients() {
        List<String> ingredients = Collections.emptyList();

        ValidatableResponse response = orderClient.createOrderResponse(token, ingredients);
        response.statusCode(400)
                .assertThat()
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Create Order without Authorization and without Ingredients")
    @Description("Проверяем, что нельзя создать заказ без ингредиентов и без авторизации.")
    public void testCreateOrderWithoutAuthorizationWithoutIngredients() {
        List<String> ingredients = Collections.emptyList();

        ValidatableResponse response = orderClient.createOrderResponse(null, ingredients);
        response.statusCode(400)
                .assertThat()
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Create Order with Invalid Ingredient Hash")
    @Description("Проверяем, что нельзя создать заказ с неверным хешем ингредиента.")
    public void testCreateOrderWithInvalidIngredientHash() {
        List<String> ingredients = List.of("invalidIngredientHash");

        ValidatableResponse response = orderClient.createOrderResponse(token, ingredients);
        response.statusCode(500);
    }
}
