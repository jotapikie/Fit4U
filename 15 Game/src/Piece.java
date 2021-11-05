public class Piece implements Comparable<Piece> {

    private int value;
    private int currentPosVal;

    public Piece(int value) {
        this.value = value;
    }

    public Piece() {
        this.value = -1;

    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setcurrentPosVal(int currentPosVal) {
        this.currentPosVal = currentPosVal;
    }


    public int getValue() {
        return value;
    }

    public int getCurrentPosVal() {
        return currentPosVal;
    }

    @Override
    public int compareTo(Piece o) {
        return 0;
    }
}
