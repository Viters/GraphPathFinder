/**
 * Created by sir.viters on 08.11.2016.
 */
class AStarNode extends MemoryNode implements Comparable<MemoryNode> {
    private double heurestic;
    private double estimatedDistance = Double.MAX_VALUE;

    AStarNode(Node node) {
        super(node);
    }

    double getHeurestic() {
        return heurestic;
    }

    void setHeurestic(double heurestic) {
        this.heurestic = heurestic;
    }

    double getEstimatedDistance() {
        return estimatedDistance;
    }

    void setEstimatedDistance(double estimatedDistance) {
        this.estimatedDistance = estimatedDistance;
    }

    @Override
    public int compareTo(MemoryNode o) {
        AStarNode ob = (AStarNode) o;
        if (estimatedDistance > ob.getEstimatedDistance())
            return 1;
        else if (estimatedDistance < ob.getEstimatedDistance())
            return -1;
        return 0;
    }
}
