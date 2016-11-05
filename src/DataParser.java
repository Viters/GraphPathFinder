import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sir.viters on 05.11.2016.
 */
class DataParser {
    private HashMap<Long, Node> nodes;

    DataParser() {
        nodes = new HashMap<>();
    }

    HashMap<Long, Node> getNodes() {
        return nodes;
    }

    void parseFile(String path) {
        try (BufferedReader br =
                     new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (Objects.equals(line, "{")) {
                    String data = "";
                    while (!Objects.equals((line = br.readLine()), "}")) {
                        data += line;
                    }
                    parseObject(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseObject(String data) {
        Pattern p = Pattern.compile("[\\d.]+");
        Matcher m = p.matcher(data);
        Node node = null;
        Long currentNeighbour = 0L;
        int iter = 1;
        while (m.find()) {
            if (iter == 1) {
                long id = Long.parseLong(m.group());
                node = getOrCreateNode(id);
            } else if (iter == 2) {
                double lat = Double.parseDouble(m.group());
                node.setLat(lat);
            } else if (iter == 3) {
                double lon = Double.parseDouble(m.group());
                node.setLon(lon);
            } else if (iter >= 4 && iter % 2 == 0) {
                currentNeighbour = Long.parseLong(m.group());
            } else if (iter >= 5 && iter % 2 != 0) {
                double distance = Double.parseDouble(m.group());
                Node neighbour = getOrCreateNode(currentNeighbour);
                node.addNeighbour(neighbour, distance);
            }

            iter++;
        }
    }

    private Node getOrCreateNode(long id) {
        Node node;
        node = nodes.get(id);
        if (node == null) {
            node = new Node(id);
            nodes.put(id, node);
        }
        return node;
    }
}
