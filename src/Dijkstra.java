import java.util.*;

/**
 * Created by sir.viters on 05.11.2016.
 */
class Dijkstra extends Alghoritm<MemoryNode> {
    Dijkstra(HashMap<Long, Node> nodes) {
        super(nodes);
    }

    @Override
    void copyNodes(HashMap<Long, Node> nodes) {
        nodes.forEach((k, v) -> nodesMap.put(k, new MemoryNode(v)));
    }

    void run(Long start, Long end) {
        MemoryNode dijkstraStart = nodesMap.get(start);
        MemoryNode dijkstraEnd = nodesMap.get(end);
        dijkstraStart.setDistance(0.0);
        nodePriorityQueue.add(dijkstraStart);
        dijkstra(dijkstraEnd);
        getShortestWay(dijkstraEnd);
    }

    private void dijkstra(MemoryNode end) {
        MemoryNode current;
        while (!nodePriorityQueue.isEmpty()) {
            current = nodePriorityQueue.poll();

            if (current == end)
                return;

            for (Connection connection : current.getNode().getNeighbours()) {
                MemoryNode neighbour = nodesMap.get(connection.getNode().getId());
                double distanceFromBeg = current.getDistance() + connection.getDistance();
                if (neighbour.getDistance() > distanceFromBeg) {
                    neighbour.setDistance(distanceFromBeg);
                    neighbour.setPreviousNode(current);
                    nodePriorityQueue.add(neighbour);
                }
            }
        }
    }
}
