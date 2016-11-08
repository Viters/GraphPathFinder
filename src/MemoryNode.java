/**
 * Created by sir.viters on 05.11.2016.
 */
class MemoryNode implements Comparable<MemoryNode> {
    private Node node;
    private double distance = Double.MAX_VALUE;
    private MemoryNode previousNode;

    MemoryNode(Node node) {
        this.node = node;
        previousNode = null;
    }

    Node getNode() {
        return node;
    }

    double getDistance() {
        return distance;
    }

    MemoryNode getPreviousNode() {
        return previousNode;
    }

    void setDistance(double distance) {
        this.distance = distance;
    }

    void setPreviousNode(MemoryNode previousNode) {
        this.previousNode = previousNode;
    }

    @Override
    public int compareTo(MemoryNode o) {
        if (distance > o.distance)
            return 1;
        else if(distance < o.distance)
            return -1;
        return 0;
    }
}
