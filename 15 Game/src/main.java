public class main {

    public static Piece[][] board = new Piece[4][4];





    public static void main(String[] args) {
        board = createBoard();
        while (!isWin()){
            play();
        }
    }

    private static Piece[][] createBoard() {
        Piece[][] temp = new Piece[4][4];

        int cont = 1;
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                if (cont != 16) {
                    temp[i][j] = new Piece(cont);
                    cont++;
                } else {
                    temp[i][j] = new Piece(0);
                }

            }
        }

        return temp;
    }

    private static boolean isWin() {
        for (Piece[] pieces : board) {
            for (Piece piece : pieces) {
                if (piece.getCurrentPosVal() != piece.getValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean swapPosition(int currentPosX, int currentPosY, int currentPosX2, int currentPosY2) {
        try {
            if(currentPosX-currentPosX2!=1||currentPosX-currentPosX2!=-1||currentPosY-currentPosY2!=1||currentPosY-currentPosY2!=-1){
                System.out.println("Impossible Move!");
                return false;
            }
            Piece tempPiece = board[currentPosX][currentPosY];
            Piece tempPiece2 = board[currentPosX2][currentPosY2];
            board[currentPosX][currentPosY] = tempPiece2;
            board[currentPosX2][currentPosY2] = tempPiece;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean play() {
        try {
            return swapPosition(BFS.play()[0][0], BFS.play()[0][1], BFS.play()[1][0], BFS.play()[1][1]);
        } catch (Exception e) {
            return false;
        }
    }
}
