
import java.util.*;

public class EICONP3 {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static int minVertex;
    static int countVertex;
    static int countEdge;

    public static void main(String[] args) {
        Vertex[] graph = readGraph();

        for (Vertex v : graph) {
            if (!v.visited) {
                minVertex = v.id;
                countVertex = 0;
                countEdge = 0;
                dfs(v);
                sb.append(v.id + " " + countVertex + " " + countEdge / 2 + "\n");
            }
        }

        System.out.println(sb);
    }

    public static void dfs(Vertex v) {
        v.visited = true;
        countVertex++;

        for (Vertex u : v.adjList) {
            countEdge++;
            if (!u.visited) {
                dfs(u);
            }
        }
    }

    public static Vertex[] readGraph() {
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

        return vertices;
    }

    public static class Vertex {

        int id;
        boolean visited;
        List<Vertex> adjList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjList(Vertex v) {
            adjList.add(v);
        }
    }
}
