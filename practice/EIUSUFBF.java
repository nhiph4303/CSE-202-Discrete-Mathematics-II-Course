package practice;

import java.util.*;

public class EIUSUFBF {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int l = sc.nextInt();

        Vertex[] graph = readGraph(n,m);
        suggestFriend(n, graph, l);
        System.out.println(sb);

    }

    public static void suggestFriend(int n, Vertex[] list, int l){
        for(int i = 0; i < n; i++){
            Collections.sort(list[i].adjList, (a,b) -> a.id - b.id);
            sb.append(list[i].id);
            for(Vertex x: list[i].adjList){
                if(x.getNumberOfFriend() < l){
                    sb.append(" ").append(x.id);
                }
            }
            sb.append("\n");
        }
    }

    public static Vertex[] readGraph(int n, int m) {
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
        private int id;
        private ArrayList<Vertex> adjList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjList(Vertex v) {
            adjList.add(v);
        }

        public int getNumberOfFriend(){
            return adjList.size();
        }
    }
}
