package domain;

public class Ingredient {
    private String name;
    private int caloriesPer100Grams;

    public Ingredient(String name, int caloriesPer100Grams) {
        this.name = name;
        this.caloriesPer100Grams = caloriesPer100Grams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCaloriesPer100Grams() {
        return caloriesPer100Grams;
    }

    public void setCaloriesPer100Grams(int caloriesPer100Grams) {
        this.caloriesPer100Grams = caloriesPer100Grams;
    }
}
