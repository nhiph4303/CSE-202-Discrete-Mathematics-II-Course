
import java.util.*;

public class EIUDEG {

    static StringBuilder sb = new StringBuilder();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Vertex[] graph = readGraph();
        for (int i = 1; i < graph.length; i++) {
            sb.append(graph[i].adjList.size() + " ");
        }

        System.out.println(sb);
    }

    static Vertex[] readGraph() {
        int n = sc.nextInt();
        int m = sc.nextInt();

        Vertex[] vertices = new Vertex[n + 1];
        for (int i = 1; i <= n; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].addAdjList(vertices[v]);
            vertices[v].addAdjList(vertices[u]);
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
