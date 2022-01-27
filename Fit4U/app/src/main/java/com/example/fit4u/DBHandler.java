package com.example.fit4u;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedHashMap;

import domain.Exercice;
import domain.Training;
import domain.User;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "gym";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String USER_TABLE = "User";
    private static final String CLIENT_TABLE = "Client";
    private static final String COACH_TABLE = "Coach";
    private static final String TRAININGPLAN_TABLE = "TrainingPlan";
    private static final String EXERCISE_TABLE = "Exercise";
    private static final String INGREDIENT_TABLE = "Ingredient";
    private static final String MEAL_TABLE = "Meal";
    private static final String NUTRITIONALPLAN_MEAL_TABLE = "NutritionalPlan_Meal";
    private static final String NUTRITIONALPLAN_TABLE = "NutritionalPlan";


    private static final String USER_ID_COL = "id";
    private static final String USER_USERNAME_COL = "username";
    private static final String USER_PASSWORD_COL = "password";
    private static final String USER_TYPE_COL = "type";
    private static final String USER_FULLNAME_COL = "fullName";

    private static final String COACH_ID_COL = "id";
    private static final String COACH_USERID_COL = "userId";

    private static final String CLIENT_CLIENTID_COL = "clientID";
    private static final String CLIENT_COACHID_COL = "coachId";
    private static final String CLIENT_USERID_COL = "userId";


    private static final String TRAININGPLAN_ID_COL = "id";
    private static final String TRAININGPLAN_COACHID_COL = "coachId";
    private static final String TRAININGPLAN_CLIENTID_COL = "clientId";


    private static final String EXERCISE_ID_COL = "id";
    private static final String EXERCISE_DAYOFWEEK_COL = "dayOfWeek";
    private static final String EXERCISE_TRAININGPLANID_COL = "trainingPlanid";
    private static final String EXERCISE_NAME_COL = "exerciseName";
    private static final String EXERCISE_REPS_COL = "reps";
    private static final String EXERCISE_CALORIESPERMIN_COL = "caloriesPerMin";

    private static final String NUTRITIONALPLAN_ID_COL = "id";
    private static final String NUTRITIONALPLAN_CLIENTID_COL = "clientID";

    private static final String NUTRITIONALPLAN_MEAL_NUTRITIONALPLANID_COL = "nutritionalPlanId";
    private static final String NUTRITIONALPLAN_MEAL_MEALID_COL = "mealId";
    private static final String NUTRITIONALPLAN_MEAL_DAYOFWEEK_COL = "dayOfWeek";

    private static final String MEAL_ID_COL = "id";
    private static final String MEAL_NAME_COL = "name";
    private static final String MEAL_RECIPE_COL = "recipe";
    private static final String MEAL_CALORIES_COL = "calories";
    private static final String MEAL_TYPEOFMEAL_COL = "typeOfMeal";

    private static final String INGREDIENT_ID_COL = "id";
    private static final String INGREDIENT_MEALID_COL = "mealId";
    private static final String INGREDIENT_CALORIES_COL = "calories";

    private static SQLiteDatabase MyDB ;


    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + USER_TABLE + " ("
                + USER_ID_COL + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + USER_USERNAME_COL + " VARCHAR(255) NOT NULL,"
                + USER_PASSWORD_COL + " INTEGER(10) NOT NULL,"
                + USER_FULLNAME_COL + " VARCHAR(255) NOT NULL,"
                + USER_TYPE_COL + " INTEGER(10) NOT NULL" + ")";


        String createCoachTable = "CREATE TABLE " + COACH_TABLE + " ("
                + COACH_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COACH_USERID_COL + " INTEGER(10) NOT NULL, FOREIGN KEY(" + USER_ID_COL + ") REFERENCES " + USER_TABLE + "(" + USER_ID_COL + ")" + ")";


        String createClientTable = "CREATE TABLE " + CLIENT_TABLE + " ("
                + CLIENT_CLIENTID_COL + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + CLIENT_COACHID_COL + " INTEGER(10),"
                + CLIENT_USERID_COL + " INTEGER(10) NOT NULL, FOREIGN KEY(" + CLIENT_USERID_COL + ") REFERENCES " + USER_TABLE + "(" + USER_ID_COL + "), FOREIGN KEY(" + CLIENT_COACHID_COL + ") REFERENCES " + COACH_TABLE + "(" + COACH_ID_COL + ")" + ")";


        String createTrainingPlanTable = "CREATE TABLE " + TRAININGPLAN_TABLE + " ("
                + TRAININGPLAN_ID_COL + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + TRAININGPLAN_CLIENTID_COL + " INTEGER(10) NOT NULL, "
                + TRAININGPLAN_COACHID_COL + " INTEGER(10) NOT NULL, FOREIGN KEY(" + TRAININGPLAN_CLIENTID_COL + ") REFERENCES " + CLIENT_TABLE + "(" + CLIENT_CLIENTID_COL + "), FOREIGN KEY("
                + TRAININGPLAN_COACHID_COL + ") REFERENCES " + COACH_TABLE + "(" + COACH_ID_COL + "))";


        String createExerciseTable = "CREATE TABLE " + EXERCISE_TABLE + " ("
                + EXERCISE_ID_COL + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + EXERCISE_DAYOFWEEK_COL + " INTEGER(10) NOT NULL, "
                + EXERCISE_TRAININGPLANID_COL + " INTEGER(10) NOT NULL, "
                + EXERCISE_NAME_COL + " VARCHAR(255) NOT NULL,"
                + EXERCISE_REPS_COL + " INTEGER(10) NOT NULL, "
                + EXERCISE_CALORIESPERMIN_COL + " FLOAT(10) NOT NULL, FOREIGN KEY(" + EXERCISE_TRAININGPLANID_COL + ") REFERENCES " + TRAININGPLAN_TABLE + "("
                + TRAININGPLAN_ID_COL + "))";


        String createIngredientTable = "CREATE TABLE " + INGREDIENT_TABLE + " ("
                + INGREDIENT_ID_COL + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + INGREDIENT_MEALID_COL + " INTEGER(10) NOT NULL,"
                + INGREDIENT_CALORIES_COL + " FLOAT(10) NOT NULL, FOREIGN KEY(" + INGREDIENT_MEALID_COL + ") REFERENCES " + MEAL_TABLE + "(" + MEAL_ID_COL + ")" + ")";


        String createNutritionalPlanTable = "CREATE TABLE " + NUTRITIONALPLAN_TABLE + " ("
                + NUTRITIONALPLAN_ID_COL + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + NUTRITIONALPLAN_CLIENTID_COL + " INTEGER(10) NOT NULL, FOREIGN KEY("
                + NUTRITIONALPLAN_CLIENTID_COL + ") REFERENCES " + CLIENT_TABLE + "(" + CLIENT_CLIENTID_COL + ")" + ")";


        String createNutritionalPlanMealTable = "CREATE TABLE " + NUTRITIONALPLAN_MEAL_TABLE + " ("
                + NUTRITIONALPLAN_MEAL_NUTRITIONALPLANID_COL + " INTEGER(10) NOT NULL, "
                + NUTRITIONALPLAN_MEAL_MEALID_COL + " INTEGER(10) NOT NULL,"
                + NUTRITIONALPLAN_MEAL_DAYOFWEEK_COL + " INTEGER(10) NOT NULL, PRIMARY KEY("
                + NUTRITIONALPLAN_MEAL_NUTRITIONALPLANID_COL + ", " + NUTRITIONALPLAN_MEAL_MEALID_COL + "), FOREIGN KEY("
                + NUTRITIONALPLAN_MEAL_NUTRITIONALPLANID_COL + ") REFERENCES " + NUTRITIONALPLAN_TABLE + "("
                + NUTRITIONALPLAN_ID_COL + "), FOREIGN KEY(" + NUTRITIONALPLAN_MEAL_MEALID_COL + ") REFERENCES " + MEAL_TABLE + "(" + MEAL_ID_COL + ")" + ")";


        String createMealTable = "CREATE TABLE " + MEAL_TABLE + " ("
                + MEAL_ID_COL + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + MEAL_NAME_COL + " VARCHAR(255) NOT NULL,"
                + MEAL_RECIPE_COL + " VARCHAR(255) NOT NULL,"
                + MEAL_CALORIES_COL + " FLOAT(10) NOT NULL,"
                + MEAL_TYPEOFMEAL_COL + " INTEGER(10)" + ")";


        db.execSQL("DROP TABLE IF EXISTS " + CLIENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + COACH_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EXERCISE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + INGREDIENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MEAL_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + NUTRITIONALPLAN_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + NUTRITIONALPLAN_MEAL_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TRAININGPLAN_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);

        db.execSQL(createClientTable);
        db.execSQL(createCoachTable);
        db.execSQL(createExerciseTable);
        db.execSQL(createIngredientTable);
        db.execSQL(createMealTable);
        db.execSQL(createNutritionalPlanTable);
        db.execSQL(createNutritionalPlanMealTable);
        db.execSQL(createTrainingPlanTable);
        db.execSQL(createUserTable);
        db.execSQL("INSERT INTO User VALUES(1, \"user1\",\"password\",\"user1FullName\",1);");
        db.execSQL("INSERT INTO User VALUES(2, \"user2\",\"password\",\"user2FullName\",1);");
        db.execSQL("INSERT INTO User VALUES(3, \"user3\",\"password\",\"user3FullName\",1);");
        db.execSQL("INSERT INTO User VALUES(4, \"user4\",\"password\",\"user4FullName\",1);");
        db.execSQL("INSERT INTO User VALUES(5, \"user5\",\"password\",\"user5FullName\",2);");
        db.execSQL("INSERT INTO User VALUES(6, \"user6\",\"password\",\"user6FullName\",2);");
        db.execSQL("INSERT INTO Coach VALUES(1, 5);");
        db.execSQL("INSERT INTO Coach VALUES(2, 6);");
        db.execSQL("INSERT INTO Client VALUES(1, 1,1);");
        db.execSQL("INSERT INTO Client VALUES(2, NULL,2)");
        db.execSQL("INSERT INTO Client VALUES(3, 2,3)");
        db.execSQL("INSERT INTO Client VALUES(4, 2,4)");

        db.execSQL("INSERT INTO TrainingPlan VALUES(1, 1,1);");
        db.execSQL("INSERT INTO TrainingPlan VALUES(2, 1,1);");
        db.execSQL("INSERT INTO TrainingPlan VALUES(3, 2,1);");
        db.execSQL("INSERT INTO TrainingPlan VALUES(4, 3,2);");
        db.execSQL("INSERT INTO TrainingPlan VALUES(5, 4,2);");
        db.execSQL("INSERT INTO Exercise VALUES(1, 5,1,\"ex1\",2,55);");
        db.execSQL("INSERT INTO Exercise VALUES(2, 4,1,\"ex2\",3,10);");
        db.execSQL("INSERT INTO Exercise VALUES(3, 5,2,\"ex3\",4,15);");
        db.execSQL("INSERT INTO Exercise VALUES(4, 2,2,\"ex4\",1,30);");
        db.execSQL("INSERT INTO Exercise VALUES(5, 1,3,\"ex5\",3,60);");
        db.execSQL("INSERT INTO Exercise VALUES(6, 7,4,\"ex6\",5,51);");
        db.execSQL("INSERT INTO Exercise VALUES(7, 2,4,\"ex7\",2,20);");
        db.execSQL("INSERT INTO Exercise VALUES(8, 3,5,\"ex8\",2,25);");
        db.execSQL("INSERT INTO NutritionalPlan VALUES(1, 1);");
        db.execSQL("INSERT INTO NutritionalPlan VALUES(2, 2);");
        db.execSQL("INSERT INTO NutritionalPlan VALUES(3, 3);");
        db.execSQL("INSERT INTO NutritionalPlan VALUES(4, 4);");
        db.execSQL("INSERT INTO NutritionalPlan_Meal VALUES(1, 1,4);");
        db.execSQL("INSERT INTO NutritionalPlan_Meal VALUES(2, 2,3);");
        db.execSQL("INSERT INTO NutritionalPlan_Meal VALUES(3, 3,2);");
        db.execSQL("INSERT INTO NutritionalPlan_Meal VALUES(4, 4,1);");
        db.execSQL("INSERT INTO Meal VALUES(1, \"nomeReceita1\",\"receita1\",300,2);");
        db.execSQL("INSERT INTO Meal VALUES(2, \"nomeReceita2\",\"receita2\",400,3);");
        db.execSQL("INSERT INTO Meal VALUES(3, \"nomeReceita3\",\"receita3\",400,1);");
        db.execSQL("INSERT INTO Meal VALUES(4, \"nomeReceita4\",\"receita4\",400,4);");
        db.execSQL("INSERT INTO Ingredient VALUES(1, 1,10);");
        db.execSQL("INSERT INTO Ingredient VALUES(2, 2,20);");
        db.execSQL("INSERT INTO Ingredient VALUES(3, 3,40);");
        db.execSQL("INSERT INTO Ingredient VALUES(4, 4,80);");


        System.out.println("||||||||||||||||||||||||insert feito");
    }

    public void addNewUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USER_USERNAME_COL, user.getUsername());
        values.put(USER_PASSWORD_COL, user.getPassword());
        values.put(USER_TYPE_COL, user.getType());
        values.put(USER_FULLNAME_COL, user.getFullName());

        db.insert(USER_TABLE, null, values);

        db.close();
    }

    public Training getTraining(int clientID, int dayOfWeek) {
        try {
            Cursor c = MyDB.rawQuery("SELECT E." + EXERCISE_NAME_COL + ", E." + EXERCISE_REPS_COL + ", E." + EXERCISE_CALORIESPERMIN_COL + " FROM " + EXERCISE_TABLE
                    + " E inner join " + TRAININGPLAN_TABLE + " TP on E." + EXERCISE_TRAININGPLANID_COL + " = TP." + TRAININGPLAN_ID_COL + " WHERE E."
                    + EXERCISE_DAYOFWEEK_COL + " = '" + dayOfWeek + "' and TP." + TRAININGPLAN_CLIENTID_COL + " = '" + clientID + "';", null);

            if (c.getCount()>0) {
                int exerciseReps = c.getColumnIndex(EXERCISE_REPS_COL);
                int exerciseName = c.getColumnIndex(EXERCISE_NAME_COL);
                int exerciseCaloriesPerMin = c.getColumnIndex(EXERCISE_CALORIESPERMIN_COL);

                LinkedHashMap<Exercice, Integer> trainingExs = new LinkedHashMap();
                while (c.moveToNext()) {
                    trainingExs.put(new Exercice(c.getString(exerciseName), c.getFloat(exerciseCaloriesPerMin)), c.getInt(exerciseReps));
                }

                return new Training(trainingExs);
            }else{
                c.close();
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;

        }

    }


    public User checkLogin(String username, String password) {
        try {
            MyDB= this.getReadableDatabase();
            Cursor c = MyDB.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE " + USER_USERNAME_COL + " = '" + username + "' AND " + USER_PASSWORD_COL + " = '" + password + "'", null);
            if (c.getCount() > 0) {
                c.moveToNext();
                int s = c.getColumnIndex(USER_USERNAME_COL);
                int key = c.getColumnIndex(USER_ID_COL);
                int type = c.getColumnIndex(USER_TYPE_COL);

                User result = new User(c.getInt(key), c.getString(s), c.getInt(type));
                System.out.println("\n\n\n\n\nResult "+result.getUsername()+ " \nFULL NAME "+result.getUsername());
                c.close();

                return result;
            } else {
                c.close();


                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CLIENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + COACH_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EXERCISE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + INGREDIENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MEAL_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + NUTRITIONALPLAN_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + NUTRITIONALPLAN_MEAL_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TRAININGPLAN_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);

        onCreate(db);
    }
}

