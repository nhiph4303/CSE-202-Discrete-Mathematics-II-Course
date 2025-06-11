
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EIHCON {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();

        Vertex[] graph = readGraph(n, m);

        for (int i = 0; i < q; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            if (graph[u].adjList.contains(graph[v])) {
                sb.append("Y\n");
            } else {
                boolean flag = false;
                for (Vertex y : graph[u].adjList) {
                    if (y.adjList.contains(graph[v])) {
                        sb.append("Y\n");
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    sb.append("N\n");
                }
            }
        }

        System.out.println(sb);
    }

    static Vertex[] readGraph(int n, int m) {
        Vertex[] vertices = new Vertex[n + 1];
        for (int i = 1; i <= n; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

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
