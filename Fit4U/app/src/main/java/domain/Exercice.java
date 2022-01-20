package domain;

public class Exercice {
    private final String name;
    private final float caloriesBurnPerMin;
    private final String description;


    public Exercice(String name, float caloriesBurnPerMin, String description){
        this.name= name;
        this.caloriesBurnPerMin=caloriesBurnPerMin;
        this.description=description;
    }

    public String getName() {
        return name;
    }

    public float getCaloriesBurnPerMin() {
        return caloriesBurnPerMin;
    }

    public String getDescription() {
        return description;
    }







}
