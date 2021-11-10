import java.util.ArrayList;
import java.util.List;

public class Board {

    public static class TilePos {
        public int x;
        public int y;

        public TilePos(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public int[][] solvedPuzzle;
    public int rows;
    public int columns;
    public int[][] tiles;
    private final int display_width;
    private TilePos blank;

    public Board(int rows, int columns, int[][] tiles) {
        this.tiles = tiles;
        this.rows = rows;
        this.columns = columns;
        int cnt = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (tiles[i][j] == 0) {
                    blank = new TilePos(i, j);
                }
            }
        }
        solvedPuzzle = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                solvedPuzzle[i][j] = cnt;
                cnt++;
            }
        }
        solvedPuzzle[rows-1][columns-1]=0;
        display_width = Integer.toString(cnt).length();

    }



    public Board(int rows, int columns, int[][] tiles, TilePos blank) {
        this.tiles = tiles;
        this.rows = rows;
        this.columns = columns;
        int cnt = 1;
        this.blank=blank;
        solvedPuzzle = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                solvedPuzzle[i][j] = cnt;
                cnt++;
            }
        }
        solvedPuzzle[rows-1][columns-1]=0;
        display_width = Integer.toString(cnt).length();

    }

    public List<TilePos> allTilePos() {
        ArrayList<TilePos> out = new ArrayList<TilePos>();
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                out.add(new TilePos(i, j));
            }
        }
        return out;
    }


    public int tile(TilePos p) {
        return tiles[p.x][p.y];
    }


    public TilePos getBlank() {
        return blank;
    }


    public TilePos whereIs(int x) {
        for (TilePos p : allTilePos()) {
            if (tile(p) == x) {
                return p;
            }
        }
        return null;
    }


    @Override
    public boolean equals(Object o) {

        for (TilePos p : allTilePos()) {
            if (this.tile(p) != ((Board) o).tile(p)) {
                return false;
            }
        }
        return true;

    }

    @Override
    public int hashCode() {
        int out = 0;
        for (TilePos p : allTilePos()) {
            out = (out * rows * columns) + this.tile(p);
        }
        return out;
    }

    public void show() {
        System.out.println("-----------------");
        for (int i = 0; i < columns; i++) {
            System.out.print("| ");
            for (int j = 0; j < rows; j++) {
                int n = tiles[i][j];
                String s;
                if (n > 0) {
                    s = Integer.toString(n);
                } else {
                    s = "";
                }
                while (s.length() < display_width) {
                    s += " ";
                }
                System.out.print(s + "| ");
            }
            System.out.print("\n");
        }
        System.out.print("-----------------\n\n");
    }


    public List<TilePos> allValidMoves() {
        ArrayList<TilePos> out = new ArrayList<TilePos>();
        for (int dx = -1; dx < 2; dx++) {
            for (int dy = -1; dy < 2; dy++) {
                TilePos tp = new TilePos(blank.x + dx, blank.y + dy);
                if (isValidMove(tp)) {
                    out.add(tp);
                }
            }
        }
        return out;
    }


    public boolean isValidMove(TilePos p) {
        if ((p.x < 0) || (p.x >= columns)) {
            return false;
        }
        if ((p.y < 0) || (p.y >= rows)) {
            return false;
        }
        int dx = blank.x - p.x;
        int dy = blank.y - p.y;
        if ((Math.abs(dx) + Math.abs(dy) != 1) || (dx * dy != 0)) {
            return false;
        }
        return true;
    }


    public void move(TilePos p) {
        assert tiles[blank.x][blank.y] == 0;
        tiles[blank.x][blank.y] = tiles[p.x][p.y];
        tiles[p.x][p.y] = 0;
        blank=p;
    }




    /**
     * returns a new puzzle with the move applied
     *
     * @param p
     * @return
     */
    public static Board moveClone(Board.TilePos p, Board b) {
        int[][] matrix = new int[b.rows][b.columns];
        for (int i = 0; i < b.rows; i++) {
            for (int j = 0; j < b.columns; j++) {
                matrix[i][j]=b.tiles[i][j];
            }
        }
        Board out = new Board(b.rows, b.columns, matrix, new Board.TilePos(b.getBlank().x,b.getBlank().y));
        out.move(p);
        return out;
    }




    public int numberMisplacedTiles() {
        int wrong = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if ((tiles[i][j] > 0) && (tiles[i][j] != solvedPuzzle[i][j])) {
                    wrong++;
                }
            }
        }
        return wrong;
    }

    public boolean isSolved() {
        return numberMisplacedTiles() == 0;
    }


    public List<Board> allAdjacentPuzzles() throws CloneNotSupportedException {
        List<Board> out = new ArrayList<>();
        for (TilePos move : allValidMoves()) {

            out.add(moveClone(move, this));
        }
        return out;
    }

    /**
     * another A* heuristic.
     * Total manhattan distance (L1 norm) from each non-blank tile to its correct position
     *
     * @return
     */
    public int manhattanDistance() {
        int sum = 0;
        for (TilePos p : allTilePos()) {
            int val = tile(p);
            if (val > 0) {
                Board.TilePos correct = new Board(this.rows, this.columns, solvedPuzzle).whereIs(val);
                sum += Math.abs(correct.x = p.x);
                sum += Math.abs(correct.y = p.y);
            }
        }
        return sum;
    }


    /**
     * distance heuristic for A*
     *
     * @return
     */
    public int estimateErrorByMisplaced() {
        return numberMisplacedTiles();
    }

    /**
     * distance heuristic for A*
     *
     * @return
     */
    public int estimateErrorByManhattan() {
        return manhattanDistance();
    }
}
