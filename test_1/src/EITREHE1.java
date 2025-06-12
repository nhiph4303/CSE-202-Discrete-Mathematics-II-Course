
import java.util.*;

public class EITREHE1 {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Vertex[] graph = readGraph();
        Vertex root = graph[0];

        dfs(root);

        int maxLevel = -1;
        for (Vertex v : graph) {
            if (v != null && v.level > maxLevel) {
                maxLevel = v.level;
            }
        }
        sb.append(maxLevel);
        System.out.println(sb);
    }

    static void dfs(Vertex v) {
        v.visited = true;

        for (Vertex w : v.adjList) {
            if (!w.visited) {
                w.level = v.level + 1;
                dfs(w);
            }
        }
    }

    static Vertex[] readGraph() {
        int n = sc.nextInt();

        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; ++i) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < n - 1; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].addAdjList(vertices[v]);
            vertices[v].addAdjList(vertices[u]);
        }

        return vertices;
    }

    static class Vertex {
        int id;
        boolean visited = false;
        int level = 0;
        List<Vertex> adjList = new ArrayList<>();

        Vertex(int id) {
            this.id = id;
        }

        void addAdjList(Vertex v) {
            adjList.add(v);
        }
    }
}
