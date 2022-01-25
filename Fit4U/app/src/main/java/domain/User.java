package domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private int id;
    private String username;
    private String password;
    private String fullName;
    private int type;



    public User(String username, String password, String fullName, int type){
        this.username=username;
        this.setPassword(password);
        this.fullName=fullName;
        this.setType(type);

    }

    public User(int id, String username, int type){
        this.id=id;
        this.username=username;
        this.type=type;
    }

    private void setType(int type) {
        if (type==0||type==1){
            this.type=type;
        }else{
            throw new IllegalArgumentException("Wrong type of user");
        }
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


    public String getUsername(){ return this.username;}

    public String getPassword() { return this.password;}

    public String getFullName(){ return this.fullName;}

    public int getType(){ return this.type;}

    public int getID() { return this.id; }
}
