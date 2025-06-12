
import java.util.*;

public class EIBIPARTITE {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            Vertex[] graph = readGraph();
            for (Vertex v : graph) {
                if (!v.visited) {
                    dfs(v);
                }
            }

            sb.append(isBi(graph) ? "Yes\n" : "No\n");
        }
        System.out.println(sb);
    }

    static boolean isBi(Vertex[] graph) {
        for (Vertex v : graph) {
            for (Vertex u : v.adjList) {
                if (v.color == u.color) {
                    return false;
                }
            }
        }
        return true;
    }

    static void dfs(Vertex v) {
        v.visited = true;

        for (Vertex u : v.adjList) {
            if (!u.visited) {
                u.color = 3 - v.color;
                dfs(u);
            }
        }
    }

    static Vertex[] readGraph() {
        int n = sc.nextInt();
        int m = sc.nextInt();

        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; ++i) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < m; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].addAdjList(vertices[v]);
            vertices[v].addAdjList(vertices[u]);
        }

        return vertices;
    }

    static class Vertex {

        public int id;
        public boolean visited;
        public int color = 1;
        List<Vertex> adjList = new ArrayList<>();

        Vertex(int id) {
            this.id = id;
        }

        void addAdjList(Vertex vertex) {
            adjList.add(vertex);
        }
    }
}
