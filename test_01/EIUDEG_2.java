package test_01;

import java.util.*;

public class EIUDEG_2 {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Vertex[] graph = readGraph();
        for (int i = 1; i<graph.length; i++){
            sb.append(graph[i].getDegree() + " ");
        }

        System.out.println(sb);
    }

    public static Vertex[] readGraph() {
        int n = sc.nextInt();
        int m = sc.nextInt();

        Vertex[] vertices = new Vertex[n + 1];

        for (int i = 1; i <= n; ++i) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].addAdjVertex((vertices[v]));
            vertices[v].addAdjVertex((vertices[u]));
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

        public int getDegree() {
            return adjVertexList.size();
        }
    }
}
