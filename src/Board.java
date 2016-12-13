import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private final static int[] di = {0, -1, 0, +1}; // directional Array
    private final static int[] dj = {+1, 0, -1, 0}; // directional Array

    private final int blocks[][];       // N * N array of blocks
    private int N;                      // board dimension
    private int emptyRow;               // empty space for row
    private int emptyCol;               // empty space for column

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        N = blocks.length;
        this.blocks = blocks;

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (blocks[row][col] == 0) {
                    emptyRow = row;
                    emptyCol = col;
                }
            }
        }
    }

    // board dimension N
    public int dimension() {
        return N;
    }

    // row position of a specified block
    private int row(int block) {
        return (block - 1) / dimension();
    }

    // col position of a specified block
    private int col(int block) {
        return (block - 1) % dimension();
    }

    // current block position
    private int block(int row, int col) {
        return blocks[row][col];
    }

    // does the goal board reach?
    public boolean isGoal() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (isBlockInWrongPlace(row, col)) return false;
            }
        }
        return true;
    }


    private boolean isBlockInWrongPlace(int row, int col) {
        int block = block(row, col);

        return !isSpace(block) && block != goalFor(row, col);
    }

    private boolean isSpace(int block) {
        return block == 0;
    }

    // goal block
    private int goalFor(int row, int col) {
        return row * dimension() + col + 1;
    }

    // make a copy of the board
    private int[][] copy(int[][] blocks) {
        int[][] copy = new int[N][N];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                copy[row][col] = blocks[row][col];
            }
        }
        return copy;
    }

    // swap two block
    private int[][] swap(int row1, int col1, int row2, int col2) {
        int[][] copy = copy(blocks);
        int tmp = copy[row1][col1];
        copy[row1][col1] = copy[row2][col2];
        copy[row2][col2] = tmp;

        return copy;
    }

    // Check array boundary
    private boolean isValid(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }

    // possible neighboring boards from the empty space
    public Iterable<Board> neighbors() {
        List<Board> boards = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int row = emptyRow + di[i];
            int col = emptyCol + dj[i];
            if (isValid(row, col)) {
                boards.add(new Board(swap(emptyRow, emptyCol, row, col)));
            }
        }
        return boards;
    }

    // does this board equal y?
    @Override
    public boolean equals(Object y) {
        Board that = (Board) y;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.blocks[i][j] != that.blocks[i][j])
                    return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(blocks);
    }

    /**
     * String representation of the board
     * Output format
     * 0  1  3
     * 4  2  5
     * 7  8  6
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                s.append(String.format("%2d ", blocks[row][col]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}
