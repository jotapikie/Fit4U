package com.example.fit4u.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import com.example.fit4u.DBHandler;
import com.example.fit4u.R;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import domain.Ingredient;
import domain.Meal;
import domain.MealDay;
import domain.NutritionalPlan;

public class SeeNutritionalPlanActivity extends AppCompatActivity {
    DBHandler dbHandler = new DBHandler(getBaseContext());
    Calendar calendar= Calendar.getInstance();
    LinkedHashMap mealList;
    private TextView kcalBreakfast;
    private TextView kcalMorningSnack;
    private TextView kcalLunch;
    private TextView kcalAfternoonSnack;
    private TextView kcalDinner;

    private TextView breakfastText;
    private TextView morningSnackText;
    private TextView lunchText;
    private TextView afternoonSnackText;
    private TextView dinnerText;

    private Meal breakFast;
    private Meal morningSnack;
    private Meal lunch;
    private Meal noonSnack;
    private Meal dinner;

    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    private int clientID;
    private MealDay mealDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_nutitional_plan);
        kcalBreakfast= findViewById(R.id.kcalBreakfast);
        kcalMorningSnack= findViewById(R.id.kcalMorningSnack);
        kcalLunch= findViewById(R.id.kcalLunch);
        kcalAfternoonSnack= findViewById(R.id.kcalAfternoonSnack);
        kcalDinner= findViewById(R.id.kcalDinner);

        breakfastText= findViewById(R.id.breakfastTXT);
        morningSnackText= findViewById(R.id.snack1TXT);
        lunchText= findViewById(R.id.lunchTXT);
        afternoonSnackText= findViewById(R.id.snack2TXT);
        dinnerText= findViewById(R.id.dinnerTXT);

        setup();

    }


    protected void setup(){
        Bundle bundle = getIntent().getExtras();
        clientID = bundle.getInt("clientID");
        //mealDay = dbHandler.getMealDay(clientID, dayOfWeek);
        mealList=mealDay.getPlan();
        breakFast= (Meal) mealList.keySet().iterator().next();
        morningSnack= (Meal) mealList.keySet().iterator().next();
        lunch= (Meal) mealList.keySet().iterator().next();
        noonSnack= (Meal) mealList.keySet().iterator().next();
        dinner= (Meal) mealList.keySet().iterator().next();

        kcalBreakfast.setText((Integer) mealList.values().iterator().next());
        kcalMorningSnack.setText((Integer) mealList.values().iterator().next());
        kcalLunch.setText((Integer) mealList.values().iterator().next());
        kcalAfternoonSnack.setText((Integer) mealList.values().iterator().next());
        kcalDinner.setText((Integer) mealList.values().iterator().next());

        String breakFastString= new String();
        for (Map.Entry<Ingredient, Integer> entry: breakFast.getIngredients().entrySet()){
            breakFastString= breakFastString+"\n"+ entry.getKey().getName()+ " -"+ entry.getValue()+"g";
        }
        breakfastText.setText(breakFastString);

        String snack1String= new String();
        for (Map.Entry<Ingredient, Integer> entry: morningSnack.getIngredients().entrySet()){
            snack1String= snack1String+"\n"+ entry.getKey().getName()+ " -"+ entry.getValue()+"g";
        }
        morningSnackText.setText(snack1String);

        String lunchString= new String();
        for (Map.Entry<Ingredient, Integer> entry: lunch.getIngredients().entrySet()){
            lunchString= lunch+"\n"+ entry.getKey().getName()+ " -"+ entry.getValue()+"g";
        }
        lunchText.setText(lunchString);

        String snack2String= new String();
        for (Map.Entry<Ingredient, Integer> entry: noonSnack.getIngredients().entrySet()){
            snack2String= snack2String+"\n"+ entry.getKey().getName()+ " -"+ entry.getValue()+"g";
        }
        afternoonSnackText.setText(snack2String);

        String dinnerString= new String();
        for (Map.Entry<Ingredient, Integer> entry: dinner.getIngredients().entrySet()){
            dinnerString= dinnerString+"\n"+ entry.getKey().getName()+ " -"+ entry.getValue()+"g";
        }
        dinnerText.setText(dinnerString);




    }
}