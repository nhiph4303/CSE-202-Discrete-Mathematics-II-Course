package practice;

import java.util.*;

public class EICONP1 {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Vertex[] graph = readGraph();

        for (Vertex v :graph){
            List<Vertex> compList = new ArrayList<>();

            if (!v.visited){
                dfs(v,compList);
                
                int minVertex = Integer.MAX_VALUE;
                for (Vertex u : compList){
                    minVertex = Math.min(minVertex, u.id);
                }
                sb.append(minVertex + " " + compList.size() +"\n");
            }
        }
        System.out.println(sb);
    }

    public static void dfs(Vertex v, List<Vertex> compList) {
        v.visited = true;
        compList.add(v);

        for (Vertex u : v.adjList) {
            if (!u.visited) {
                dfs(u, compList);
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
