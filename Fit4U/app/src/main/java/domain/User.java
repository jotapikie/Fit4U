package domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private String username;
    private String password;



    public User(String username, String password){
        this.setUsername(username);
        this.setPassword(password);

    }

    private void setPassword(String password) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(password);
        boolean b = m.find();
        if (password.length()>8 && b && containsUpperCaseLetter(password)){
            this.password=password;
        }else{
            throw new IllegalArgumentException("Password must be more than 6 chars long, contain special character and one uppercase letter");
        }
    }

    public boolean containsUpperCaseLetter(String s){
        for(int i=0;i<s.length();i++){
            if(Character.isUpperCase(s.charAt(i))){
                return true;
            }
        }
        return false;
    }

    private void setUsername(String username) {
        if (!Gym.usernameExists(username)){
            this.username=username;
        }else{
            throw new IllegalArgumentException("Username Already Exists, try a different one");
        }
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword() { return this.password;}
}
