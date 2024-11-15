package test_01;

import java.util.*;

public class EIHCON {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();

        Vertex[] graph = readGraph(n, m);
        getResult(q, graph);
    }

    public static void getResult(int q, Vertex[] graph) {
        for (int i = 0; i < q; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            Vertex vertexU = graph[u];
            Vertex vertexV = graph[v];

            if (vertexU.adjVertexList.contains(vertexV)) {
                sb.append("Y\n");
            } else {
                boolean flag = false;
                for (Vertex vertexY : vertexU.adjVertexList) {
                    if (vertexY.adjVertexList.contains(vertexV)) {
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
        for (int i = 1; i <= n; ++i) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[v].addAdjVertex(vertices[u]);
        }

        return vertices;
    }

    public static class Vertex {

        public int id;
        public List<Vertex> adjVertexList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjVertex(Vertex v) {
            adjVertexList.add(v);
        }
    }
}
