package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;

public class GetOrdersTest extends BaseOrderTest {
    @Override
    public void setUp() {
        super.setUp();
        List<String> ingredients = Arrays.asList(bunHash, mainHash);

        ValidatableResponse response = orderClient.createOrderResponse(token, ingredients);
        response.statusCode(200)
                .assertThat()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Get Orders for Authorized User")
    @Description("Проверяем, что авторизованный пользователь может получить свои заказы.")
    public void testGetOrdersForAuthorizedUser() {
        ValidatableResponse response = orderClient.getOrders(token);

        response.statusCode(200);
        response.body("success", equalTo(true))
                .body("orders", not(empty()));
    }

    @Test
    @DisplayName("Get Orders for Unauthorized User")
    @Description("Проверяем, что неавторизованный пользователь не может получить свои заказы.")
    public void testGetOrdersForUnauthorizedUser() {
        ValidatableResponse response = orderClient.getOrders(null);

        response.statusCode(401);
        response.body("success", equalTo(false))
                .body("message", equalTo("You should be authorised")); // Сообщение может варьироваться в зависимости от реализации
    }
}
