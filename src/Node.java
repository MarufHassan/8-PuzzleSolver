import java.util.ArrayList;
import java.util.List;
/**
 * Represent the Node used in BFS Algorithm
 */
public class Node {
    Board board;
    Node prev;  // previous node
    int move;

    public Node(Board board, Node prev) {
        this.board = board;
        this.prev = prev;
        if (this.prev == null)  this.move = 0;
        else                    this.move = prev.move + 1;
    }

    public boolean isGoal() {
        return board.isGoal();
    }

    public Iterable<Node> neighbors() {
        List<Node> nodes = new ArrayList<>();
        for (Board b : board.neighbors()) {
            nodes.add(new Node(b, prev));
        }
        return nodes;
    }

    @Override
    public boolean equals(Object obj) {
        Node that = (Node) obj;
        return this.board.equals(that.board);
    }

    @Override
    public int hashCode() {
        return board.hashCode();
    }
}
