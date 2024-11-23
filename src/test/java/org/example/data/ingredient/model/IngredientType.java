package org.example.data.ingredient.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum IngredientType {
    @JsonProperty("bun")
    BUN,
    @JsonProperty("main")
    MAIN,
    @JsonProperty("sauce")
    SAUCE
}
