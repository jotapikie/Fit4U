package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class MealDay {

    private LinkedHashMap<Meal, Float> plan;
    private float totalCaloriesDay;
    private int totalCookingTimeDay;

    public MealDay(LinkedHashMap<Meal,Float> plan) {
        this.plan=plan;
        setCookingDuration(plan);
        setTotalCaloriesDay(plan);
    }


    public LinkedHashMap<Meal, Float> getPlan() {
        return plan;
    }

    private void setTotalCaloriesDay(LinkedHashMap<Meal, Float> plan) {
        int totalCalories=0;
        for ( Map.Entry<Meal,Float> entry: plan.entrySet()) {
            totalCalories+=entry.getValue();
        }
        this.totalCaloriesDay=totalCalories;
    }




    private void setCookingDuration(LinkedHashMap<Meal, Float> plan){
        int duration=0;
        for ( Map.Entry<Meal,Float> entry: plan.entrySet()) {
            duration+= (float) entry.getKey().getCookingTime();
        }
        this.totalCookingTimeDay=duration;
    }
}
