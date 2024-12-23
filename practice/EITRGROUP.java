package practice;

import java.util.*;

public class EITRGROUP {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Vertex[] graph = readGraph();
        Vertex root = null;

        for (Vertex v : graph) {
            if (v.parent == null) {
                root = v;
                break;
            }
        }

        dfs(root);

        int maxLevel = -1;
        for (Vertex v : graph) {
            if (v != null && v.level > maxLevel) {
                maxLevel = v.level;
            }
        }
        sb.append(maxLevel + 1);
        System.out.println(sb);
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
        int m = sc.nextInt();

        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].addAdjList(vertices[v]);
            vertices[v].parent = vertices[u];
        }
        return vertices;
    }

    public static class Vertex {
        int level = 0;
        Vertex parent = null;
        int id;
        boolean visited = false;
        ArrayList<Vertex> adjList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjList(Vertex v) {
            adjList.add(v);
        }
    }
}
