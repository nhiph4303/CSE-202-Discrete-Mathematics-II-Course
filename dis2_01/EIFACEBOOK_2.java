package dis2_01;

import java.util.*;

public class EIFACEBOOK_2 {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Vertex[] graph = readGraph();
        for (int i = 1; i < graph.length; i++) {
            int count = 0;
            for (Vertex friend : graph[i].adjList) {
                if (!friend.gender.equalsIgnoreCase(graph[i].gender)) {
                    count++;
                }
            }
            sb.append(count + " ");
        }
        System.out.println(sb);
    }

    public static Vertex[] readGraph() {
        int n = sc.nextInt();
        int m = sc.nextInt();

        Vertex[] vertices = new Vertex[n+1];

        for (int i = 1; i <= n; i++) {
            String gender = sc.next();
            vertices[i] = new Vertex(gender);
        }
        

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].addAdjVertex(vertices[v]);
            vertices[v].addAdjVertex(vertices[u]);
        }
        return vertices;
    }

    public static class Vertex {

        public String gender;
        public List<Vertex> adjList = new ArrayList<>();

        public Vertex(String gender) {
            this.gender = gender;
        }

        public void addAdjVertex(Vertex v) {
            if (!adjList.contains(v)) {
                adjList.add(v);
            }
        }
    }
}
