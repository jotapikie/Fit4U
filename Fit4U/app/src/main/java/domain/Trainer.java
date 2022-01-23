package domain;

public class Trainer extends User {

    private String name;
    private int classification;

    public Trainer(String username, String password, int type, String name, int classification) {
        super(username, password, type);
        this.name=name;
        setClassification(classification);
    }

    private void setClassification(int classification) {
        if (classification<=5 && classification>=0){
            this.classification=classification;
        }else{
            throw new IllegalArgumentException("Wrong Classification Value");
        }
    }
}
