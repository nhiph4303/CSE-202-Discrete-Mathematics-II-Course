package test_01;

import java.util.*;

public class EIUBFS2 {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Vertex[] graph = readGraph();
        bfs(graph[0]);
        System.out.println(sb);

    }

    public static void bfs(Vertex v) {
        Queue<Vertex> q = new ArrayDeque<>();
        v.visited = true;
        q.add(v);

        while (!q.isEmpty()) {
            Vertex x = q.poll();
            sb.append(x.id + " ");

            for (Vertex w : x.adjList) {
                if (!w.visited) {
                    w.visited = true;
                    q.add(w);
                }
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

            vertices[u].addAdjVertex(vertices[v]);
            vertices[v].addAdjVertex(vertices[u]);
        }

        for (Vertex x : vertices) {
            x.adjList.sort((v1, v2) -> v1.id - v2.id);
        }
        return vertices;
    }

    public static class Vertex {

        public int id;
        public boolean visited;
        public List<Vertex> adjList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjVertex(Vertex v) {
            adjList.add(v);

        }
    }
}
