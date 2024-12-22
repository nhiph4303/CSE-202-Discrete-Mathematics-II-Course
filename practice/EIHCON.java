package practice;

import java.util.*;

public class EIHCON {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();

        Vertex[] graph = readGraph(n, m);

        getResult(graph, q);
    }

    public static void getResult(Vertex[] graph, int q) {
        for (int i = 0; i < q; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            Vertex vertexV = graph[v];
            Vertex vertexU = graph[u];

            if (vertexV.adjList.contains(vertexU)) {
                sb.append("Y\n");
            } else {
                boolean flag = false;
                for (Vertex vertexY : vertexV.adjList) {
                    if (vertexY.adjList.contains(vertexU)) {
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

    public static Vertex[] readGraph(int n, int m) {
        Vertex[] vertices = new Vertex[n + 1];
        for (int i = 1; i <= n; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].addAdjList(vertices[v]);
        }
        return vertices;
    }

    public static class Vertex {
        int id;
        List<Vertex> adjList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjList(Vertex v) {
            adjList.add(v);
        }
    }
}
