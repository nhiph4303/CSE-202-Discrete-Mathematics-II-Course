package test_01;

import java.util.*;

public class EIMKF {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Vertex[] graph = readGraph();
        for (int i = 0; i<graph.length; i++){
            Vertex u = graph[i];
            Collections.sort(u.adjVertexList);
            sb.append(i).append(" ").append(u.getDegree()).append(" ");

            for (Vertex v : u.adjVertexList){
                sb.append(v.id).append(" ");
            }

            sb.append("\n");
        }
        System.out.println(sb.toString().trim());
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

            vertices[u].addAdjecentVertex(vertices[v]);
            vertices[v].addAdjecentVertex(vertices[u]);
        }

        return vertices;
    }

    public static class Vertex implements Comparable<Vertex> {

        public int id;
        public List<Vertex> adjVertexList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjecentVertex(Vertex v) {
            adjVertexList.add(v);
        }

        public int getDegree(){
            return adjVertexList.size();
        }

        @Override
        public int compareTo(Vertex v) {
            return this.id - v.id;
        }
    }
}
