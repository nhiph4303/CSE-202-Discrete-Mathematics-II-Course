package practice;

import java.util.*;

public class EIPEOYMK {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Vertex[] graph = readGraph();
        int root = sc.nextInt();

        bfs(graph[root]);
        Map<Integer, List<Integer>> levelMap = new HashMap<>();

        for (Vertex v : graph) {
            if (v.level != -1) {
                if (!levelMap.containsKey(v.level)) {
                    levelMap.put(v.level, new ArrayList<>());
                }
                levelMap.get(v.level).add(v.id);
            }
        }

        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            int k = sc.nextInt();
            List<Integer> friends = levelMap.get(k);

            if (friends == null || friends.isEmpty()) {
                sb.append("-1\n");
            } else {
                for (int id : friends) {
                    sb.append(id + " ");
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }

    public static void bfs(Vertex v) {
        Queue<Vertex> q = new ArrayDeque<>();
        q.add(v);
        v.visited = true;
        v.level = 0;

        while (!q.isEmpty()) {
            Vertex w = q.poll();

            for (Vertex x : w.adjList) {
                if (!x.visited) {
                    x.visited = true;
                    x.level = w.level + 1;
                    q.add(x);
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

        for (Vertex v : vertices) {
            v.adjList.sort((a, b) -> a.id - b.id);
        }
        return vertices;
    }

    public static class Vertex {
        int level = -1;
        int id;
        boolean visited = false;
        List<Vertex> adjList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjList(Vertex v) {
            adjList.add(v);
        }
    }
}
