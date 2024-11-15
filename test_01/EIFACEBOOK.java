package test_01;

import java.util.*;

public class EIFACEBOOK {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Vertex[] graph = readGraph();

        for (int i = 0; i < graph.length; i++) {
            int count = 0;

            for (Vertex friend : graph[i].adjList) {
                if (!graph[i].id.equalsIgnoreCase(friend.id)) {
                    count++;
                }
            }

            sb.append(count + " ");
        }
        System.out.println(sb);
    }

    public static Vertex[] readGraph() {
        int n = sc.nextInt();
        long m = sc.nextLong();

        Vertex[] vertices = new Vertex[n];

        for (int i = 0; i < n; i++) {
            String id = sc.next();
            vertices[i] = new Vertex(id);
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;

            vertices[u].addAdjVertex(vertices[v]);
            vertices[v].addAdjVertex(vertices[u]);
        }
        return vertices;
    }

    public static class Vertex {

        public String id;
        public List<Vertex> adjList = new ArrayList<>();

        public Vertex(String id) {
            this.id = id;
        }

        public void addAdjVertex(Vertex v) {
            if (!adjList.contains(v)){
                adjList.add(v);
            }
        }

    }
}
