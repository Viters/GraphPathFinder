public class Main {

    public static void main(String[] args) {
	    DataParser dataParser = new DataParser();
        dataParser.parseFile("small.txt");

        System.out.println("Dijkstra:");

        Dijkstra dijkstra = new Dijkstra(dataParser.getNodes());

        long startTime = System.currentTimeMillis();
        dijkstra.run(226009912L, 2503558048L);
        long estimatedTime = System.currentTimeMillis() - startTime;

        System.out.println("Time: " + estimatedTime + "ms");
        dijkstra.getWay().forEach(w -> System.out.println(w.getNode().getId()));
        System.out.println(dijkstra.getFinalDistance());

        System.out.println();
        System.out.println("A*:");

        AStar aStar = new AStar(dataParser.getNodes());

        startTime = System.currentTimeMillis();
        aStar.run(226009912L, 2503558048L);
        estimatedTime = System.currentTimeMillis() - startTime;

        System.out.println("Time: " + estimatedTime + "ms");
        aStar.getWay().forEach(w -> System.out.println(w.getNode().getId()));
        System.out.println(aStar.getFinalDistance());
    }
}
