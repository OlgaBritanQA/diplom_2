package org.example.data.ingredient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.data.ingredient.model.Ingredient;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class IngredientResponse {
    private Boolean success;
    private List<Ingredient> data;
}
