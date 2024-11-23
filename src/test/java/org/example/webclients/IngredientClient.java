package org.example.webclients;

import io.restassured.response.Response;
import org.example.data.ingredient.IngredientResponse;

import static io.restassured.RestAssured.given;
import static org.example.util.Constants.INGREDIENTS_API;

public class IngredientClient {
    public IngredientResponse getIngredients() {
        Response response = given().get(INGREDIENTS_API.getConstant());

        if (response.getStatusCode() != 200) {
            throw new AssertionError("Faild to fetch ingredients HTTP error code: " + response.getStatusCode());
        }

        return response.getBody().as(IngredientResponse.class);
    }
}
