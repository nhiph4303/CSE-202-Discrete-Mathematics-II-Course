
import java.util.*;

public class EIPRF {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Vertex[] graph = readGraph();
        List<Vertex> path = new ArrayList<>();

        if (dfs(graph[0], path)) {
            for (Vertex v : path) {
                sb.append(v.id + " ");
            }

            System.out.println(sb);
        }

    }

    public static boolean dfs(Vertex v, List<Vertex> path) {
        v.visited = true;
        path.add(v);

        for (Vertex u : v.adjList) {
            if (!u.visited) {
                if (dfs(u, path)) {
                    return true;
                }
            } else if (u.id == 0) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
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
            //vertices[v].addAdjList(vertices[u]);
        }

        for (Vertex v : vertices) {
            v.adjList.sort((v1, v2) -> v1.id - v2.id);
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
