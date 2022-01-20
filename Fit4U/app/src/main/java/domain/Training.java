package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Training {
    private int duration;
    private LinkedHashMap<Exercice, Integer> plan;
    private float totalCaloriesBurn;

    public Training(LinkedHashMap<Exercice,Integer> plan) {
        this.plan=plan;
        setDuration(plan);
        setTotalCaloriesBurn(plan);
    }

    private void setTotalCaloriesBurn(LinkedHashMap<Exercice, Integer> plan) {
        int totalCalories=0;
        for ( Map.Entry<Exercice,Integer> entry: plan.entrySet()) {
            float exerciceCalories= entry.getKey().getCaloriesBurnPerMin()*entry.getValue();
            totalCalories+=exerciceCalories;
        }
        this.totalCaloriesBurn=totalCalories;
    }




    private void setDuration(LinkedHashMap<Exercice, Integer> plan){
        int duration=0;
        for ( Map.Entry<Exercice,Integer> entry: plan.entrySet()) {
            duration+= entry.getValue();
        }
        this.duration=duration;
    }
}
