package test_01;

import java.util.*;

public class EITREHE1 {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Vertex[] graph = readGraph();

        dfs(graph[0]);

        int maxLevel = -1;
        for (Vertex v : graph) {

            maxLevel = Math.max(maxLevel, v.level);

        }
        System.out.println(maxLevel);

    }

    public static void dfs(Vertex v) {
        v.visited = true;

        for (Vertex u : v.adjList) {
            if (!u.visited) {
                u.level = v.level + 1;
                dfs(u);
            }
        }
    }

    public static Vertex[] readGraph() {
        int n = sc.nextInt();

        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; ++i) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].addAdjVertex(vertices[v]);
            vertices[v].addAdjVertex(vertices[u]);

        }

        return vertices;
    }

    public static class Vertex {
        public int id;
        int level = 0;
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
