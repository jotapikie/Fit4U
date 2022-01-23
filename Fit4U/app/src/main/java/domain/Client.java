package domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client extends User{

    private TrainingPlan trainingPlan;
    private String email;
    private String name;
    private float currentWeight;
    private float height;
    private float pretendedWeight;
    private static final String regex = "^(.+)@(.+)$";

    public Client(String username, String password, int type, String name, float currentWeight, float height, float pretendedWeight) {
        super(username, password, type);
        setEmail(email);
        this.name=name;
        setCurrentWeight(currentWeight);
        setHeight(height);
        setPretendedWeight(pretendedWeight);
    }


    private void setEmail(String email) {
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(email);
        boolean b = m.find();
        if (b){
            this.email=email;
        }else{
            throw new IllegalArgumentException("Email not valid");
        }
    }


    private void setHeight(float height) {
        if (height > 1.00 && height < 3.00  ){
            this.height=height;
        }else{
            throw new IllegalArgumentException("Height is not valid");
        }
    }


    private void setCurrentWeight(float weight){
        if (weight > 0 && weight < 500.00 ){
            this.currentWeight= weight;
        }else{
            throw new IllegalArgumentException("Current Weight not valid");
        }
    }


    private void setPretendedWeight(float pretendedWeight){
        if (pretendedWeight > 0 && pretendedWeight < 500){
            this.pretendedWeight=pretendedWeight;
        }else{
            throw new IllegalArgumentException("Pretended Weight Not Valid");
        }
    }




}
