import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Created by sir.viters on 08.11.2016.
 */
public class AStar {
    private HashMap<Long, AStarNode> aStarNodes;
    private PriorityQueue<AStarNode> nodePriorityQueue;
    private ArrayList<MemoryNode> way;

    AStar(HashMap<Long, Node> nodes) {
        aStarNodes = new HashMap<>();
        nodes.forEach((k, v) -> aStarNodes.put(k, new AStarNode(v)));
        nodePriorityQueue = new PriorityQueue<>();
        way = new ArrayList<>();
    }

    ArrayList<MemoryNode> getWay() {
        return way;
    }

    double getFinalDistance() {
        return way.get(way.size() - 1).getDistance();
    }

    void run(Long start, Long end) {
        AStarNode aStarStart = aStarNodes.get(start);
        AStarNode aStarEnd = aStarNodes.get(end);
        aStarStart.setDistance(0.0);
        aStarStart.setHeurestic(aStarStart.calcDistance(aStarEnd.getNode()));
        aStarStart.setEstimatedDistance(aStarStart.getHeurestic());
        nodePriorityQueue.add(aStarStart);
        aStar(aStarEnd);
        getShortestWay(aStarEnd);
    }

    private void aStar(AStarNode end) {
        AStarNode current;
        ArrayList<AStarNode> visitedNodes = new ArrayList<>();
        while (!nodePriorityQueue.isEmpty()) {
            current = nodePriorityQueue.poll();
            if (current == end)
                return;
            visitedNodes.add(current);
            for (Connection connection : current.getNode().getNeighbours()) {
                AStarNode neighbour = aStarNodes.get(connection.getNode().getId());
                if (visitedNodes.contains(neighbour))
                    continue;

                double distanceFromBeg = current.getDistance() + connection.getDistance();

                if (!nodePriorityQueue.contains(neighbour))
                    nodePriorityQueue.add(neighbour);
                else if (distanceFromBeg >= neighbour.getDistance())
                    continue;

                neighbour.setHeurestic(neighbour.calcDistance(end.getNode()));
                neighbour.setPreviousNode(current);
                neighbour.setDistance(distanceFromBeg);
                neighbour.setEstimatedDistance(neighbour.getDistance() + neighbour.getHeurestic());
            }
        }
    }

    private void getShortestWay(AStarNode end) {
        MemoryNode current = end;
        while (current != null) {
            way.add(current);
            current = current.getPreviousNode();
        }
        Collections.reverse(way);
    }
}
