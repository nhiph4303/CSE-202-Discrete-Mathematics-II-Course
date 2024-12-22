package practice;

import java.util.*;

public class EIMKF {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Vertex[] graph = readGraph();
        for (int i = 0; i < graph.length; i++) {
            Vertex v = graph[i];
            sb.append(v.id + " " + getListFriend(v).size() + " ");
            for (Vertex u : getListFriend(v)) {
                sb.append(u.id + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    public static List<Vertex> getListFriend(Vertex v) {
        List<Vertex> listFriend = new ArrayList<>();

        for (Vertex u : v.adjList) {
            listFriend.add(u);
        }
        Collections.sort(listFriend, (a, b) -> a.id - b.id);
        return listFriend;
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
        List<Vertex> adjList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjList(Vertex v) {
            adjList.add(v);
        }
    }
}
