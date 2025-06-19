
import java.util.*;

public class EIUSUFBF {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        Vertex[] graph = readGraph(n, m);

        for (int i = 0; i < graph.length; i++) {
            sb.append(graph[i].id + " ");
            for (Vertex v : graph[i].adjList) {
                if (v.adjList.size() < k) {
                    sb.append(v.id + " ");
                }
            }
        }

        System.out.println(sb);
    }

    static Vertex[] readGraph(int n, int m) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[v].addAdjList(vertices[u]);
            vertices[u].addAdjList(vertices[v]);
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
