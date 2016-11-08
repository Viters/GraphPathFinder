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

    double calcDistance(Node node) {
        double lon1 = this.getNode().getLon();
        double lat1 = this.getNode().getLat();
        double lon2 = node.getLon();
        double lat2 = node.getLat();

        double latDiff = lat2 - lat1;
        double lonDiff = lon2 - lon1;
        double latDistance = Math.toRadians(latDiff);
        double lonDistance = Math.toRadians(lonDiff);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(lat1))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        return 6371e3 * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }

    @Override
    public int compareTo(MemoryNode o) {
        if (distance > o.getDistance())
            return 1;
        else if(distance < o.getDistance())
            return -1;
        return 0;
    }
}
