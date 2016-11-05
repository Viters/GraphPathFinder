public class Main {

    public static void main(String[] args) {
	    DataParser dataParser = new DataParser();
        dataParser.parseFile("input.txt");
        Dijkstra dijkstra = new Dijkstra(dataParser.getNodes());
        dijkstra.run(226009912L, 277478868L);
        dijkstra.getWay().forEach(w -> System.out.println(w.getNode().getId()));
        System.out.println(dijkstra.getFinalDistance());
    }
}
