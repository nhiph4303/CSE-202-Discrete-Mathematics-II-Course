import java.io.*;
import java.util.*;

public class a {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        Vertex[] graph = readGraph();
        int start = sc.nextInt() - 1;  // Đọc điểm xuất phát (S)
        int end = sc.nextInt() - 1;    // Đọc điểm đích (T)

        // Gọi hàm Dijkstra để tính thời gian và số cạnh
        dijkstra(graph, start);

        // In ra kết quả
        if (graph[end].cost != Integer.MAX_VALUE) {
            sb.append(graph[end].cost).append(" ").append(graph[end].edgesCount);
        } else {
            sb.append("-1");
        }

        System.out.println(sb);
    }

    // Hàm Dijkstra tính thời gian và số lượng cạnh
    public static void dijkstra(Vertex[] graph, int start) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>((e1, e2) -> {
            // So sánh thời gian (cost)
            if (e1.cost != e2.cost) {
                return Integer.compare(e1.cost, e2.cost);
            }
            // Nếu thời gian bằng nhau, so sánh số cạnh (edgesCount)
            return Integer.compare(e1.edgesCount, e2.edgesCount); 
        });

        graph[start].cost = 0;
        graph[start].edgesCount = 0;
        pq.add(graph[start]);

        while (!pq.isEmpty()) {
            Vertex polledV = pq.poll();

            if (polledV.visited) {
                continue;
            }
            polledV.visited = true;

            for (Edge e : polledV.adjList) {
                Vertex endpoint = e.endpoint;
                int newCost = polledV.cost + e.weight;
                int newEdgesCount = polledV.edgesCount + 1;

                // Điều kiện cập nhật: tìm được đường đi nhanh hơn hoặc có cùng thời gian nhưng ít giao cắt hơn
                if (newCost < endpoint.cost || (newCost == endpoint.cost && newEdgesCount < endpoint.edgesCount)) {
                    endpoint.cost = newCost;
                    endpoint.edgesCount = newEdgesCount;
                    pq.add(endpoint);
                }
            }
        }
    }

    // Hàm đọc đồ thị
    static Vertex[] readGraph() {
        int n = sc.nextInt();
        int m = sc.nextInt();

        Vertex[] vertices = new Vertex[n];

        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            int w = sc.nextInt();

            vertices[u].addAdjList(w, vertices[v]);
            vertices[v].addAdjList(w, vertices[u]);
        }

        return vertices;
    }

    // Lớp Edge
    public static class Edge {
        int weight;
        Vertex endpoint;

        public Edge(int weight, Vertex endpoint) {
            this.weight = weight;
            this.endpoint = endpoint;
        }
    }

    // Lớp Vertex
    public static class Vertex {
        int id;
        boolean visited;
        List<Edge> adjList = new ArrayList<>();
        int cost = Integer.MAX_VALUE;
        int edgesCount = Integer.MAX_VALUE;

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjList(int weight, Vertex endpoint) {
            adjList.add(new Edge(weight, endpoint));
        }
    }
}
