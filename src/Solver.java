import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Solver {

    private Node root;  // root node in the solution tree

    public Solver(Board initial) {
        root = solve(initial);
    }

    /**
     * find a solution to the initial board (using the Breadth First Search)
     */
    private Node solve(Board initial) {
        if (initial.dimension() == 1) return new Node(initial, null);

        Set<Node> nodes = new HashSet<>();
        Queue<Node> q = new LinkedList<>();

        Node init = new Node(initial, null);    // first node
        q.add(init);
        nodes.add(init);

        while (!q.isEmpty()) {
            Node node = q.poll();   // Retrieves and removes the head of this queue

            if (node.isGoal()) {    // goal board found
                return node;
            }

            // traversed all possible neighbouring board
            for (Node tmpNode : node.neighbors()) {
                if (!nodes.contains(tmpNode)) {  // the board is not traversed previously
                    q.add(new Node(tmpNode.board, node));
                    nodes.add(tmpNode);
                }
            }
        }
        return null;    // all possible boards traversed. No solution.
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return root != null;
    }

    // min number of moves to solve initial board; -1 if no solution
    public int moves() {
        if(!isSolvable())	return -1;
        else				return root.move;
    }

    // sequence of boards in the shortest solution; null if no solution
    public Iterable<Board> findSolution() {
        if(!isSolvable())	return null;

        List<Board> lists = new ArrayList<>();
        Node x = root;
        while(x.prev != null) {
            lists.add(x.board);
            x = x.prev;
        }
        Collections.reverse(lists);
        return lists;
    }
}
