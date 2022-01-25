package com.example.fit4u;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;

import domain.Training;
import domain.User;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "gym";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String USER_TABLE= "User";
    private static final String CLIENT_TABLE= "Client";
    private static final String COACH_TABLE= "Coach";
    private static final String TRAININGPLAN_TABLE= "TrainingPlan";
    private static final String EXERCISE_TABLE = "Exercise";
    private static final String EXERCISE_TRAININGPLAN_TABLE= "Exercise_TrainingPlan";
    private static final String INGREDIENT_TABLE= "Ingredient";
    private static final String MEAL_TABLE= "Meal";
    private static final String NUTRITIONALPLAN_MEAL_TABLE= "NutritionalPlan_Meal";
    private static final String NUTRITIONALPLAN_TABLE= "NutritionalPlan";


    private static final String USER_ID_COL = "id";
    private static final String USER_USERNAME_COL = "username";
    private static final String USER_PASSWORD_COL = "password";
    private static final String USER_TYPE_COL = "type";
    private static final String USER_FULLNAME_COL= "fullName";

    private static final String COACH_ID_COL= "id";
    private static final String COACH_USERID_COL= "userId";

    private static final String CLIENT_CLIENTID_COL= "clientID";
    private static final String CLIENT_COACHID_COL= "coachId";
    private static final String CLIENT_USERID_COL= "userId";


    private static final String TRAININGPLAN_ID_COL= "id";
    private static final String TRAININGPLAN_CLIENTID_COL= "clientId";
    private static final String TRAININGPLAN_DAYOFWEEK_COL= "dayOfWeek";

    private static final String EXERCISE_TRAININGPLAN_EXERCISEID_COL= "exerciseId";
    private static final String EXERCISE_TRAININGPLAN_TRAININGPLANID_COL= "trainingPlanId";

    private static final String EXERCISE_ID_COL= "id";
    private static final String EXERCISE_NAME_COL= "exerciseName";
    private static final String EXERCISE_REPS_COL= "reps";

    private static final String NUTRITIONALPLAN_ID_COL= "id";
    private static final String NUTRITIONALPLAN_CLIENTID_COL= "clientID";

    private static final String NUTRITIONALPLAN_MEAL_NUTRITIONALPLANID_COL= "nutritionalPlanId";
    private static final String NUTRITIONALPLAN_MEAL_MEALID_COL= "mealId";
    private static final String NUTRITIONALPLAN_MEAL_DAYOFWEEK_COL= "dayOfWeek";

    private static final String MEAL_ID_COL= "id";
    private static final String MEAL_NAME_COL= "name";
    private static final String MEAL_RECIPE_COL= "recipe";
    private static final String MEAL_CALORIES_COL= "calories";
    private static final String MEAL_TYPEOFMEAL_COL= "typeOfMeal";

    private static final String INGREDIENT_ID_COL= "id";
    private static final String INGREDIENT_MEALID_COL= "mealId";
    private static final String INGREDIENT_CALORIES_COL= "calories";







    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String createUserTable = "CREATE TABLE " + USER_TABLE + " ("
                + USER_ID_COL + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + USER_USERNAME_COL + " VARCHAR(255) NOT NULL,"
                + USER_PASSWORD_COL + " INTEGER(10) NOT NULL,"
                + USER_TYPE_COL + " INTEGER(10) NOT NULL,"
                + USER_FULLNAME_COL + " VARCHAR(255) NOT NULL"+ ")";


        String createCoachTable = "CREATE TABLE " + COACH_TABLE + " ("
                + COACH_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COACH_USERID_COL + " INTEGER(10) NOT NULL, FOREIGN KEY("+USER_ID_COL+") REFERENCES "+ USER_TABLE + "("+USER_ID_COL+")"+ ")";



        String createClientTable = "CREATE TABLE " + CLIENT_TABLE + " ("
                + CLIENT_CLIENTID_COL + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + CLIENT_COACHID_COL + " INTEGER(10),"
                + CLIENT_USERID_COL + " INTEGER(10) NOT NULL, FOREIGN KEY("+CLIENT_USERID_COL+") REFERENCES "+ USER_TABLE + "("+USER_ID_COL+"), FOREIGN KEY("+CLIENT_COACHID_COL+") REFERENCES "+ COACH_TABLE + "("+COACH_ID_COL+")"+ ")";


        String createTrainingPlanTable = "CREATE TABLE " + TRAININGPLAN_TABLE + " ("
                + TRAININGPLAN_ID_COL + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + TRAININGPLAN_CLIENTID_COL + " INTEGER(10) NOT NULL,"
                + TRAININGPLAN_DAYOFWEEK_COL + " INTEGER(10) NOT NULL, FOREIGN KEY("+TRAININGPLAN_CLIENTID_COL+") REFERENCES "+ CLIENT_TABLE + "("+CLIENT_CLIENTID_COL+")"+")";



        String createExerciseTrainingPlanTable = "CREATE TABLE " + EXERCISE_TRAININGPLAN_TABLE + " ("
                + EXERCISE_TRAININGPLAN_EXERCISEID_COL + " INTEGER(10) NOT NULL,"
                + EXERCISE_TRAININGPLAN_TRAININGPLANID_COL + " INTEGER(10) NOT NULL, PRIMARY KEY ("
                + EXERCISE_TRAININGPLAN_EXERCISEID_COL+", "+ EXERCISE_TRAININGPLAN_TRAININGPLANID_COL
                +"), FOREIGN KEY("+EXERCISE_TRAININGPLAN_EXERCISEID_COL+") REFERENCES "+ EXERCISE_TABLE+"("+EXERCISE_ID_COL+")"+")";


        String createExerciseTable = "CREATE TABLE " + EXERCISE_TABLE + " ("
                + EXERCISE_ID_COL + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + EXERCISE_NAME_COL + " VARCHAR(255) NOT NULL,"
                + EXERCISE_REPS_COL + " INTEGER(10) NOT NULL"+ ")";



        String createIngredientTable = "CREATE TABLE " + INGREDIENT_TABLE + " ("
                + INGREDIENT_ID_COL + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + INGREDIENT_MEALID_COL + " INTEGER(10) NOT NULL,"
                + INGREDIENT_CALORIES_COL + " FLOAT(10) NOT NULL, FOREIGN KEY("+ INGREDIENT_MEALID_COL+ ") REFERENCES "+MEAL_TABLE+"("+ MEAL_ID_COL+ ")"+")";


        String createNutritionalPlanTable = "CREATE TABLE " + NUTRITIONALPLAN_TABLE + " ("
                + NUTRITIONALPLAN_ID_COL + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + NUTRITIONALPLAN_CLIENTID_COL + " INTEGER(10) NOT NULL, FOREIGN KEY("
                + NUTRITIONALPLAN_CLIENTID_COL + ") REFERENCES "+ CLIENT_TABLE + "(" + CLIENT_CLIENTID_COL + ")"+")";


        String createNutritionalPlanMealTable = "CREATE TABLE " + NUTRITIONALPLAN_MEAL_TABLE + " ("
                + NUTRITIONALPLAN_MEAL_NUTRITIONALPLANID_COL + " INTEGER(10) NOT NULL, "
                + NUTRITIONALPLAN_MEAL_MEALID_COL + " INTEGER(10) NOT NULL,"
                + NUTRITIONALPLAN_MEAL_DAYOFWEEK_COL + " INTEGER(10) NOT NULL, PRIMARY KEY("
                + NUTRITIONALPLAN_MEAL_NUTRITIONALPLANID_COL+ ", "+NUTRITIONALPLAN_MEAL_MEALID_COL+"), FOREIGN KEY("
                + NUTRITIONALPLAN_MEAL_NUTRITIONALPLANID_COL+ ") REFERENCES "+NUTRITIONALPLAN_TABLE+"("
                + NUTRITIONALPLAN_ID_COL+"), FOREIGN KEY("+ NUTRITIONALPLAN_MEAL_MEALID_COL+ ") REFERENCES "+ MEAL_TABLE+"("+MEAL_ID_COL+")"+")";


        String createMealTable = "CREATE TABLE " + MEAL_TABLE + " ("
                + MEAL_ID_COL + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + MEAL_NAME_COL + " VARCHAR(255) NOT NULL,"
                + MEAL_RECIPE_COL + " VARCHAR(255) NOT NULL,"
                + MEAL_CALORIES_COL + " FLOAT(10) NOT NULL,"
                + MEAL_TYPEOFMEAL_COL + " INTEGER(10)"+ ")";


        db.execSQL(createClientTable);
        db.execSQL(createCoachTable);
        db.execSQL(createExerciseTable);
        db.execSQL(createExerciseTrainingPlanTable);
        db.execSQL(createIngredientTable);
        db.execSQL(createMealTable);
        db.execSQL(createNutritionalPlanTable);
        db.execSQL(createNutritionalPlanMealTable);
        db.execSQL(createTrainingPlanTable);
        db.execSQL(createUserTable);


    }

    // this method is use to add new course to our sqlite database.
    public void addNewUser(User user) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(USER_USERNAME_COL, user.getUsername());
        values.put(USER_PASSWORD_COL, user.getPassword());
        values.put(USER_TYPE_COL, user.getType());
        values.put(USER_FULLNAME_COL, user.getFullName());

        // after adding all values we are passing
        // content values to our table.
        db.insert(USER_TABLE, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }


    public Training getTraining(int clientID, int dayOfWeek){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+ );



    }


    public User checkLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE " + username + " =? AND " + password + " =?", null);
        if (c.getCount() > 0) {
            int s = c.getColumnIndex(USER_USERNAME_COL);
            int key = c.getColumnIndex(USER_ID_COL);
            int type = c.getColumnIndex(USER_TYPE_COL);

            User result= new User(c.getInt(key),c.getString(s), c.getInt(type));
            c.close();
            db.close();
            return result;

        } else {
            c.close();
            db.close();
            return null;
        }
    }

    @Override
    public void onUpgrade (SQLiteDatabase db,int oldVersion, int newVersion){
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + CLIENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + COACH_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EXERCISE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EXERCISE_TRAININGPLAN_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + INGREDIENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MEAL_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + NUTRITIONALPLAN_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + NUTRITIONALPLAN_MEAL_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TRAININGPLAN_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);

        onCreate(db);
    }
}

