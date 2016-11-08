import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Created by sir.viters on 08.11.2016.
 */
abstract class Alghoritm<T extends MemoryNode> {
    HashMap<Long, T> nodesMap;
    PriorityQueue<T> nodePriorityQueue;
    private ArrayList<MemoryNode> way;

    Alghoritm(HashMap<Long, Node> nodes) {
        nodesMap = new HashMap<>();
        copyNodes(nodes);
        nodePriorityQueue = new PriorityQueue<>();
        way = new ArrayList<>();
    }

    abstract void copyNodes(HashMap<Long, Node> nodes);

    ArrayList<MemoryNode> getWay() {
        return way;
    }

    double getFinalDistance() {
        return way.get(way.size() - 1).getDistance();
    }

    abstract void run(Long start, Long end);

    void getShortestWay(T end) {
        MemoryNode current = end;
        while (current != null) {
            way.add(current);
            current = current.getPreviousNode();
        }
        Collections.reverse(way);
    }
}
