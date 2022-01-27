package domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Exercice implements Parcelable {
    private final String name;
    private final float caloriesBurnPerMin;


    public Exercice(String name, float caloriesBurnPerMin){
        this.name= name;
        this.caloriesBurnPerMin=caloriesBurnPerMin;
    }

    public Exercice(Parcel source) {
        name = source.readString();
        caloriesBurnPerMin = source.readFloat();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeFloat(caloriesBurnPerMin);
    }

    public String getName() {
        return name;
    }

    public float getCaloriesBurnPerMin() {
        return caloriesBurnPerMin;
    }

    public static final Creator<Exercice> CREATOR = new Creator<Exercice>() {
        @Override
        public Exercice[] newArray(int size) {
            return new Exercice[size];
        }

        @Override
        public Exercice createFromParcel(Parcel source) {
            return new Exercice(source);
        }
    };








}
