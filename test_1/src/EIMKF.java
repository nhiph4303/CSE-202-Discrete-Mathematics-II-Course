
import java.util.*;

public class EIMKF {

    static StringBuilder sb = new StringBuilder();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Vertex[] graph = readGraph();
        for (int i = 0; i < graph.length; i++) {
            Vertex v = graph[i];

            sb.append(v.id + " " + v.adjList.size() + " ");

            for (Vertex y : v.adjList) {
                sb.append(y.id + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static Vertex[] readGraph() {
        int n = sc.nextInt();
        int m = sc.nextInt();

        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].addAdjList(vertices[v]);
            vertices[v].addAdjList(vertices[u]);
        }

        for (Vertex v : vertices) {
            v.adjList.sort((v1, v2) -> v1.id - v2.id);
        }
        return vertices;
    }

    static class Vertex {

        public int id;
        public List<Vertex> adjList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjList(Vertex v) {
            adjList.add(v);
        }

    }

}
