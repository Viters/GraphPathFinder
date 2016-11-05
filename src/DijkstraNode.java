/**
 * Created by sir.viters on 05.11.2016.
 */
class DijkstraNode implements Comparable<DijkstraNode> {
    private Node node;
    private double distance = Double.MAX_VALUE;
    private DijkstraNode previousNode;

    DijkstraNode(Node node) {
        this.node = node;
        previousNode = null;
    }

    Node getNode() {
        return node;
    }

    double getDistance() {
        return distance;
    }

    DijkstraNode getPreviousNode() {
        return previousNode;
    }

    void setDistance(double distance) {
        this.distance = distance;
    }

    void setPreviousNode(DijkstraNode previousNode) {
        this.previousNode = previousNode;
    }

    @Override
    public int compareTo(DijkstraNode o) {
        if (distance > o.getDistance())
            return 1;
        else if(distance < o.getDistance())
            return -1;
        return 0;
    }
}
