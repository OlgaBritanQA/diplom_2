package org.example.util;

import lombok.Getter;

@Getter
public enum Constants {
    BASE_URL("https://stellarburgers.nomoreparties.site"),
    REGISTER_API("/api/auth/register"),
    LOGIN_API("/api/auth/login"),
    USER_API("/api/auth/user"),
    INGREDIENTS_API("/api/ingredients"),
    ORDER_API("/api/orders");

    private final String constant;

    Constants(final String constant) {
        this.constant = constant;
    }
}
