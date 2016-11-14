import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.util.HashMap;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Podaj trzy argumenty: adres pliku, początkowy wierzchołek, końcowy wierzchołek.");
            return;
        }

        String filePath = args[0];
        long startingElem = Long.parseLong(args[1]);
        long endingElem = Long.parseLong(args[2]);
        String extension = getFileExtension(filePath);

        HashMap<Long, Node> graph = null;
        if (Objects.equals(extension, "ser")) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
                graph = (HashMap<Long, Node>) inputStream.readObject();
                System.out.println("Dane deserializowane z pliku " + filePath);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (Objects.equals(extension, "xml")) {
            try (FileInputStream inputStream = new FileInputStream(filePath)) {
                XStream xstream = new XStream();
                graph = (HashMap<Long, Node>) xstream.fromXML(inputStream);
                System.out.println("Dane deserializowane z pliku " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Dane przetwarzane z pliku " + filePath);
            graph = parseNewData(filePath);
        }

        if (graph == null) {
            return;
        }

        Dijkstra dijkstra = runDijkstra(graph, startingElem, endingElem);

        AStar aStar = runAStar(graph, startingElem, endingElem);

        System.out.println();

        boolean waysEqual = true;
        if (aStar.getWay().size() == dijkstra.getWay().size())
            for (int i = 0; i < aStar.getWay().size(); ++i) {
                if (aStar.getWay().get(i).getNode().getId() != dijkstra.getWay().get(i).getNode().getId())
                    waysEqual = false;
            }

        if (waysEqual) {
            System.out.println("Trasy są zgodne: ");
            aStar.getWay().forEach(w -> System.out.println("ID: " + w.getNode().getId() + ", Lat: " + w.getNode().getLat() + ", Lon: " + w.getNode().getLon() + ", Dist: " + w.getDistance()));
            System.out.println();
        }

        if (dijkstra.getFinalDistance() == aStar.getFinalDistance())
            System.out.println("Trasy mają tę samą długość: " + aStar.getFinalDistance() + "m");
    }

    private static Dijkstra runDijkstra(HashMap<Long, Node> map, long startingElem, long endingElem) {
        Dijkstra dijkstra = new Dijkstra(map);

        long startTime = System.currentTimeMillis();
        dijkstra.run(startingElem, endingElem);
        long estimatedTime = System.currentTimeMillis() - startTime;

        System.out.println("Dijkstra time: " + estimatedTime + "ms");

        return dijkstra;
    }

    private static AStar runAStar(HashMap<Long, Node> map, long startingElem, long endingElem) {
        AStar aStar = new AStar(map);

        long startTime = System.currentTimeMillis();
        aStar.run(startingElem, endingElem);
        long estimatedTime = System.currentTimeMillis() - startTime;

        System.out.println("A* time: " + estimatedTime + "ms");

        return aStar;
    }

    private static HashMap<Long, Node> parseNewData(String filePath) {
        DataParser dataParser = new DataParser();
        dataParser.parseFile(filePath);

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("graf.ser"))) {
            outputStream.writeObject(dataParser.getNodes());
            System.out.println("Serializacja zapisana do pliku graf.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }

//        XStream xstream = new XStream();
//        try (ObjectOutputStream out = xstream.createObjectOutputStream(new FileOutputStream("graf.xml"))) {
//            out.writeObject(dataParser.getNodes());
//            System.out.println("Serializacja zapisana do pliku graf.xml");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return dataParser.getNodes();
    }

    private static String getFileExtension(String name) {
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }
}
