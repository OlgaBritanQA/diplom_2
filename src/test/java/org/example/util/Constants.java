package org.example.util;

import lombok.Getter;

@Getter
public enum Constants {
    BASE_URL("https://stellarburgers.nomoreparties.site"),
    REGISTER_API("/api/auth/register"),
    LOGIN_API("/api/auth/login"),
    USER_API("/api/auth/user"),
    ORDER_API("/api/orders"),

    //Хеш ингидиентов, взят постманом из https://stellarburgers.nomoreparties.site/api/ingredients
    MEAT_PROTOSTOMIA("61c0c5a71d1f82001bdaaa6f"),
    BUN_R2_D3("61c0c5a71d1f82001bdaaa6d");

    private final String constant;

    Constants(final String constant) {
        this.constant = constant;
    }
}
