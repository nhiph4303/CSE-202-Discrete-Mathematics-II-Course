package test_01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        for (Vertex u : graph){
            if (u!=null){
                maxLevel = Math.max(u.level, maxLevel);
            }
        }

        System.out.println(maxLevel + 1);

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
        for (int i = 0; i < n; ++i) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].addAdjVertex(vertices[v]);
            vertices[v].parent = vertices[u];
        }

        return vertices;
    }

    public static class Vertex {
        public int id;
        int level = 0;
        public boolean visited;
        public Vertex parent = null;
        public List<Vertex> adjList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjVertex(Vertex v) {
            adjList.add(v);
        }
    }
}
