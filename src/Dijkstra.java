import java.util.*;

/**
 * Created by sir.viters on 05.11.2016.
 */
class Dijkstra {
    private HashMap<Long, MemoryNode> dijkstraNodes;
    private PriorityQueue<MemoryNode> nodePriorityQueue;
    private ArrayList<MemoryNode> way;

    Dijkstra(HashMap<Long, Node> nodes) {
        dijkstraNodes = new HashMap<>();
        nodes.forEach((k, v) -> dijkstraNodes.put(k, new MemoryNode(v)));
        nodePriorityQueue = new PriorityQueue<>();
        way = new ArrayList<>();
    }

    void run(Long start, Long end) {
        MemoryNode dijkstraStart = dijkstraNodes.get(start);
        dijkstraStart.setDistance(0.0);
        nodePriorityQueue.add(dijkstraStart);
        dijkstra(end);
        getShortestWay(end);
    }

    ArrayList<MemoryNode> getWay() {
        return way;
    }
    
    double getFinalDistance() {
        return way.get(way.size() - 1).getDistance();
    }

    private void dijkstra(Long end) {
        MemoryNode current;
        while (!nodePriorityQueue.isEmpty()) {
            current = nodePriorityQueue.poll();
            for (Connection connection : current.getNode().getNeighbours()) {
                MemoryNode neighbour = dijkstraNodes.get(connection.getNode().getId());
                double distanceFromBeg = current.getDistance() + connection.getDistance();
                if (neighbour.getDistance() > distanceFromBeg) {
                    neighbour.setDistance(distanceFromBeg);
                    neighbour.setPreviousNode(current);
                    if (neighbour.getNode().getId() == end)
                        return;
                    nodePriorityQueue.add(neighbour);
                }
            }
        }
    }

    private void getShortestWay(Long end) {
        MemoryNode current = dijkstraNodes.get(end);
        while (current != null) {
            way.add(current);
            current = current.getPreviousNode();
        }
        Collections.reverse(way);
    }
}
