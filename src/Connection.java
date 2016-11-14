import java.io.Serializable;

/**
 * Created by sir.viters on 05.11.2016.
 */
class Connection implements Serializable {
    private final Node node;
    private final double distance;

    Connection(Node node, double distance) {
        this.node = node;
        this.distance = distance;
    }

    Node getNode() {
        return node;
    }

    double getDistance() {
        return distance;
    }
}
