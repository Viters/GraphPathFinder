public class Main {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Podaj trzy argumenty!");
            return;
        }

        long startingElem = Long.parseLong(args[1]);
        long endingElem = Long.parseLong(args[2]);

	    DataParser dataParser = new DataParser();
        dataParser.parseFile(args[0]);

        Dijkstra dijkstra = new Dijkstra(dataParser.getNodes());

        long startTime = System.currentTimeMillis();
        dijkstra.run(startingElem, endingElem);
        long estimatedTime = System.currentTimeMillis() - startTime;

        System.out.println("Dijkstra time: " + estimatedTime + "ms");

        AStar aStar = new AStar(dataParser.getNodes());

        startTime = System.currentTimeMillis();
        aStar.run(startingElem, endingElem);
        estimatedTime = System.currentTimeMillis() - startTime;

        System.out.println("A* time: " + estimatedTime + "ms");

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
}
