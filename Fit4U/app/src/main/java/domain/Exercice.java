package domain;

public class Exercice {
    private final String name;
    private final float caloriesBurnPerMin;


    public Exercice(String name, float caloriesBurnPerMin){
        this.name= name;
        this.caloriesBurnPerMin=caloriesBurnPerMin;
    }

    public String getName() {
        return name;
    }

    public float getCaloriesBurnPerMin() {
        return caloriesBurnPerMin;
    }








}
