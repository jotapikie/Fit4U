package domain;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Training {
    private int duration;
    private HashMap<Exercice, Integer> plan;
    private int totalCaloriesBurn;

    public Training(HashMap<Exercice,Integer> plan) {
        this.plan=plan;
        setDuration(plan);
        setTotalCaloriesBurn();
    }




    private void setDuration(HashMap<Exercice, Integer> plan){
        int duration=0;
        for ( Map.Entry<Exercice,Integer> entry: plan.entrySet()) {
            duration+= entry.getValue();
        }
        this.duration=duration;
    }

    private void setTotalCaloriesBurn(){
        int totalCalories=0;
        for ( Map.Entry<Exercice,Integer> entry: plan.entrySet()) {
            totalCalories+= entry.getValue()*entry.getKey().getCaloriesBurnPerMin();
        }
        this.totalCaloriesBurn=totalCalories;
    }

    public HashMap<Exercice, Integer> getPlan() {
        return plan;
    }

    public int getTotalCaloriesBurn() {
        return totalCaloriesBurn;
    }

    public Pair<Integer,Integer> getDuration() {
        int hours = duration / 60; //since both are ints, you get an int
        int minutes = duration % 60;
        return new Pair<Integer,Integer>(hours,minutes);
    }

    public int getTotalCaloriesBurned(){
        return this.totalCaloriesBurn;
    }
}
