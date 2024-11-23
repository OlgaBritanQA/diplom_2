package org.example;

import io.restassured.response.ValidatableResponse;
import org.example.data.ingredient.IngredientResponse;
import org.example.data.ingredient.model.Ingredient;
import org.example.data.ingredient.model.IngredientType;
import org.example.webclients.IngredientClient;
import org.example.webclients.OrderClient;

import java.util.List;
import java.util.stream.Collectors;

public class BaseOrderTest extends BaseTest {
    protected static OrderClient orderClient;
    protected static IngredientClient ingredientClient;
    protected String token;
    protected String bunHash;
    protected String mainHash;

    @Override
    public void setUp() {
        super.setUp();
        registerClient.registerUserResponse(registerRequest);
        orderClient = new OrderClient();
        ingredientClient = new IngredientClient();
        ValidatableResponse dataUser = authClient.loginUserResponse(user);
        token = dataUser.extract().path("accessToken");

        /*
          Получаем хэш для Булочки и Основного ингредиента.
          Берем все ингредиенты, фильтруем по Булочкам и, если лист с Булочками не пустой, берем первую.
          Тоже самое делаем для Основного ингредиента.
         */
        IngredientResponse ingredientResponse = ingredientClient.getIngredients();
        List<Ingredient> listIngredients = ingredientResponse.getData();

        bunHash = getIngredientHashByType(listIngredients, IngredientType.BUN);
        mainHash = getIngredientHashByType(listIngredients, IngredientType.MAIN);
    }

    private String getIngredientHashByType(List<Ingredient> listIngredients, IngredientType type) {
        List<Ingredient> filteredIngredients = listIngredients.stream()
                .filter(ingredient -> type == ingredient.getType())
                .collect(Collectors.toList());

        if (filteredIngredients.isEmpty()) {
            throw new AssertionError("Ingredient does not exist");
        }

        return filteredIngredients.get(0).getId();
    }
}
