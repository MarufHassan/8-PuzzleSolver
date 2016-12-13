import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(args[0]));
        int N;
        N = scanner.nextInt();
        int blocks[][] = new int[N][N];

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                blocks[row][col] = scanner.nextInt();
            }
        }

        Board board = new Board(blocks);
        Solver solver = new Solver(board);

        System.out.println("Initial Board");
        System.out.println(board);

        if (solver.isSolvable()) {
            System.out.println("Minimum number of moves taken: " + solver.moves());
            System.out.println("Solution steps: ");
            for (Board b : solver.findSolution()) {
                System.out.println(b);
            }
        } else {
            System.out.println("Solution is not possible");
        }
    }
}
