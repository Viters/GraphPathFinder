import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sir.viters on 08.11.2016.
 */
class AStar extends Alghoritm<AStarNode> {
    AStar(HashMap<Long, Node> nodes) {
        super(nodes);
    }

    @Override
    void copyNodes(HashMap<Long, Node> nodes) {
        nodes.forEach((k, v) -> nodesMap.put(k, new AStarNode(v)));
    }

    void run(Long start, Long end) {
        AStarNode aStarStart = nodesMap.get(start);
        AStarNode aStarEnd = nodesMap.get(end);
        aStarStart.setDistance(0.0);
        aStarStart.setEstimatedDistance(aStarStart.calculateHeuristics(aStarEnd.getNode()));
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
                AStarNode neighbour = nodesMap.get(connection.getNode().getId());
                if (visitedNodes.contains(neighbour))
                    continue;

                double distanceFromBeg = current.getDistance() + connection.getDistance();

                if (distanceFromBeg < neighbour.getDistance()) {
                    double heuristics;
                    if (neighbour.getHeuristics() != -1) {
                        heuristics = neighbour.getHeuristics();
                    }
                    else {
                        heuristics = neighbour.calculateHeuristics(end.getNode());
                        neighbour.setHeuristics(heuristics);
                    }
                    neighbour.setPreviousNode(current);
                    neighbour.setDistance(distanceFromBeg);
                    neighbour.setEstimatedDistance(neighbour.getDistance() + heuristics);
                }
                if (!nodePriorityQueue.contains(neighbour)) {
                    nodePriorityQueue.add(neighbour);
                }
            }

        }
    }
}
