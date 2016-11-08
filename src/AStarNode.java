/**
 * Created by sir.viters on 08.11.2016.
 */
class AStarNode extends MemoryNode implements Comparable<MemoryNode> {
    private double estimatedDistance = Double.MAX_VALUE;

    AStarNode(Node node) {
        super(node);
    }

    double calculateHeuristics(Node node) {
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

    void setEstimatedDistance(double estimatedDistance) {
        this.estimatedDistance = estimatedDistance;
    }

    @Override
    public int compareTo(MemoryNode o) {
        AStarNode ob = (AStarNode) o;
        if (estimatedDistance > ob.estimatedDistance)
            return 1;
        else if (estimatedDistance < ob.estimatedDistance)
            return -1;
        return 0;
    }
}
