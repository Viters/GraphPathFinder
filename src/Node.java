import java.util.ArrayList;

/**
 * Created by sir.viters on 05.11.2016.
 */
class Node {
    private final long id;
    private double lon;
    private double lat;
    private ArrayList<Connection> neighbours;

    Node(long id) {
        this.id = id;
        neighbours = new ArrayList<>();
    }

    long getId() {
        return id;
    }

    double getLon() {
        return lon;
    }

    double getLat() {
        return lat;
    }

    void setLon(double lon) {
        this.lon = lon;
    }

    void setLat(double lat) {
        this.lat = lat;
    }

    ArrayList<Connection> getNeighbours() {
        return neighbours;
    }

    void addNeighbour(Node node, double distance) {
        Connection connection = new Connection(node, distance);
        neighbours.add(connection);
    }
}
