package test_01;

import java.util.*;

public class EIUSUFBF {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int l = sc.nextInt();

        Vertex[] graph = readGraph(n, m);

        for (int i = 0; i < graph.length; i++) {
            List<Integer> waitList = new ArrayList<>();
            for (Vertex x : graph[i].adjList) {
                if (x.adjList.size() < l) {
                    waitList.add(x.id);
                }
            }

            Collections.sort(waitList);

            sb.append(i).append(" ");
            for (int x : waitList) {
                sb.append(x).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString().trim());
    }

    public static Vertex[] readGraph(int n, int m) {
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
