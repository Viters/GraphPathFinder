import java.util.*;

/**
 * Created by sir.viters on 05.11.2016.
 */
class Dijkstra {
    private HashMap<Long, DijkstraNode> dijkstraNodes;
    private PriorityQueue<DijkstraNode> nodePriorityQueue;
    private ArrayList<DijkstraNode> way;

    Dijkstra(HashMap<Long, Node> nodes) {
        dijkstraNodes = new HashMap<>();
        nodes.forEach((k, v) -> dijkstraNodes.put(k, new DijkstraNode(v)));
        nodePriorityQueue = new PriorityQueue<>();
        way = new ArrayList<>();
    }

    void run(Long start, Long end) {
        DijkstraNode dijkstraStart = dijkstraNodes.get(start);
        dijkstraStart.setDistance(0.0);
        nodePriorityQueue.add(dijkstraStart);
        dijkstra();
        getShortestWay(end);
    }

    ArrayList<DijkstraNode> getWay() {
        return way;
    }
    
    double getFinalDistance() {
        return way.get(way.size() - 1).getDistance();
    }

    private void dijkstra() {
        DijkstraNode current;
        while (!nodePriorityQueue.isEmpty()) {
            current = nodePriorityQueue.poll();
            for (Connection connection : current.getNode().getNeighbours()) {
                DijkstraNode neighbour = dijkstraNodes.get(connection.getNode().getId());
                double distanceFromBeg = current.getDistance() + connection.getDistance();
                if (neighbour.getDistance() > distanceFromBeg) {
                    neighbour.setDistance(distanceFromBeg);
                    neighbour.setPreviousNode(current);
                    nodePriorityQueue.add(neighbour);
                }
            }
        }
    }

    private void getShortestWay(Long end) {
        DijkstraNode current = dijkstraNodes.get(end);
        while (current != null) {
            way.add(current);
            current = current.getPreviousNode();
        }
        Collections.reverse(way);
    }
}
