package domain;

import java.util.HashMap;

public class Meal {
    private int cookingTime;
    private String recipe;
    private HashMap<Ingredient, Integer> ingredients;
    private MealType type;

    public Meal(int cookingTime, String recipe, HashMap<Ingredient, Integer> ingredients, MealType type) {
        this.cookingTime = cookingTime;
        this.recipe = recipe;
        this.ingredients = ingredients;
        this.type = type;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public HashMap<Ingredient, Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(HashMap<Ingredient, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public MealType getType() {
        return type;
    }

    public void setType(MealType type) {
        this.type = type;
    }
}
