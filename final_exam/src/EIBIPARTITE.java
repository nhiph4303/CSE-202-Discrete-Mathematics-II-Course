
import java.util.*;

public class EIBIPARTITE {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            Vertex[] graph = readGraph();

            for (Vertex v : graph) {
                if (!v.visited) {
                    dfs(v);
                }
                
                for (Vertex u : v.adjList) {
                    if (v.color == u.color) {
                        flag = false;
                        break;
                    }
                }
            }

            if (flag) {
                sb.append("Yes\n");
            } else {
                sb.append("No\n");
            }
        }
        System.out.println(sb);
    }

    public static void dfs(Vertex v) {
        v.visited = true;

        for (Vertex u : v.adjList) {
            if (!u.visited) {
                u.color = 3 - v.color;
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
        int color = 1;
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
