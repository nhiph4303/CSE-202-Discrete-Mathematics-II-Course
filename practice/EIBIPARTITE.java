package practice;

import java.util.*;

public class EIBIPARTITE {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        for (int i = 0; i<n; i++){
            Vertex[] graph = readGraph();

            for (Vertex v : graph){
                if (!v.visited){
                    bfs(v);
                }
            }

            sb.append(isBipartite(graph) ? "Yes\n" : "No\n");
        }
        System.out.println(sb);
    }

    public static boolean isBipartite(Vertex[] graph) {
        for (Vertex v : graph) {
            for (Vertex u : v.adjList) {
                if (v.color == u.color) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void bfs(Vertex v) {
        Queue<Vertex> q = new ArrayDeque<>();
        v.visited = true;
        q.add(v);

        while (!q.isEmpty()) {
            Vertex u = q.poll();

            for (Vertex w : u.adjList) {
                if (!w.visited) {
                    w.visited = true;
                    q.add(w);
                    w.color = 3 - u.color;
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

            vertices[u].addAdjList(vertices[v]);
            vertices[v].addAdjList(vertices[u]);
        }

        return vertices;
    }

    public static class Vertex {
        int color = 1;
        int id;
        boolean visited;
        ArrayList<Vertex> adjList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjList(Vertex v) {
            adjList.add(v);
        }
    }
}
