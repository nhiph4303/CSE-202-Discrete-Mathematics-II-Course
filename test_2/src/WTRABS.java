
import java.util.*;

public class WTRABS {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {

        Vertex[] graph = readGraph();
        dfs(graph[0]);

        for (Vertex v : graph) {
            if (v.water != 0) {
                sb.append(v.id + " " + v.water + "\n");
            }
        }
        System.out.println(sb);
    }

    public static void dfs(Vertex v) {
        v.visited = true;

        if (!v.adjList.isEmpty()) {
            double transferedWater = v.water / v.adjList.size();

            for (Vertex u : v.adjList) {
                if (!u.visited) {
                    u.water += transferedWater;
                    dfs(u);
                }
            }
            v.water = 0;
        }
    }

    public static Vertex[] readGraph() {
        int n = sc.nextInt();
        int m = n - 1;

        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; ++i) {
            vertices[i] = new Vertex(i);
            vertices[i].water = sc.nextDouble();
        }

        for (int i = 0; i < m; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[v].addAdjList(vertices[u]);
        }
        return vertices;
    }

    public static class Vertex {

        public int id;
        public boolean visited;
        public List<Vertex> adjList = new ArrayList<>();
        public double water;

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjList(Vertex vertex) {
            adjList.add(vertex);
        }
    }
}
