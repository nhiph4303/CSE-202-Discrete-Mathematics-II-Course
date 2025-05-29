
import java.util.*;

public class EICONP1 {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static int vertexCount;
    static int minVertex;

    public static void main(String[] args) {
        Vertex[] graph = readGraph();

        for (Vertex v : graph) {
            if (!v.visited) {
                minVertex = v.id;
                vertexCount = 0;
                dfs(v);
                sb.append(minVertex + " " + vertexCount + "\n");
            }
        }
        System.out.println(sb);
    }

    public static void dfs(Vertex v) {
        v.visited = true;
        vertexCount++;
        for (Vertex u : v.adjList) {
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
