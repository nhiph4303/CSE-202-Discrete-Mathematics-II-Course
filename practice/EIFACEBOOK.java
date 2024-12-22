package practice;

import java.util.*;

public class EIFACEBOOK {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Vertex[] graph = readGraph();
        for (int i = 1; i<graph.length;i++){
            int count = 0;
            for (Vertex v: graph[i].adjList){
                if (!graph[i].id.equalsIgnoreCase(v.id)){
                    count++;
                }
            }

            sb.append(count +" ");
          
        }
        System.out.println(sb);
    }

    public static Vertex[] readGraph() {
        int n = sc.nextInt();
        int m = sc.nextInt();

        Vertex[] vertices = new Vertex[n + 1];
        for (int i = 1; i <= n; i++) {
            vertices[i] = new Vertex(sc.next());
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
        String id;
        List<Vertex> adjList = new ArrayList<>();

        public Vertex(String id) {
            this.id = id;
        }

        public void addAdjList(Vertex v) {
            if (!adjList.contains(v)) {
                adjList.add(v);
            }
        }
    }
}
