public class Main {

    public static void main(String[] args) {
	    DataParser dataParser = new DataParser();
        dataParser.parseFile("big.txt");

        System.out.println("Dijkstra:");

        Dijkstra dijkstra = new Dijkstra(dataParser.getNodes());

        long startTime = System.currentTimeMillis();
        dijkstra.run(2996088770L, 1387833107L);
        long estimatedTime = System.currentTimeMillis() - startTime;

        System.out.println("Time: " + estimatedTime + "ms");
        dijkstra.getWay().forEach(w -> System.out.println(w.getNode().getId()));
        System.out.println(dijkstra.getFinalDistance());

        System.out.println();
        System.out.println("A*:");

        AStar aStar = new AStar(dataParser.getNodes());

        startTime = System.currentTimeMillis();
        aStar.run(2996088770L, 1387833107L);
        estimatedTime = System.currentTimeMillis() - startTime;

        System.out.println("Time: " + estimatedTime + "ms");
        aStar.getWay().forEach(w -> System.out.println(w.getNode().getId()));
        System.out.println(aStar.getFinalDistance());
    }
}
