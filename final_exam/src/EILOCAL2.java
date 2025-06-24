
import java.util.*;

public class EILOCAL2 {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static long max;

    public static void main(String[] args) {
        Vertex[] graph = readGraph();
        max = 0;
        dfs(graph[0], 0);
        System.out.println(max);
    }

    static void dfs(Vertex v, long sum) {
        v.visited = true;
        max = Math.max(max, sum);

        for (Edge e : v.edgeList) {
            if (!e.endPoint.visited) {
                dfs(e.endPoint, sum + e.weight);
            }
        }
    }

    static Vertex[] readGraph() {
        int n = sc.nextInt();
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();

            vertices[u].edgeList.add(new Edge(weight, vertices[v]));
            vertices[v].edgeList.add(new Edge(weight, vertices[u]));

        }

        return vertices;
    }

    static class Vertex {

        int id;
        boolean visited;
        List<Edge> edgeList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }
    }

    static class Edge {

        long weight;
        Vertex endPoint;

        public Edge(long weight, Vertex endPoint) {
            this.weight = weight;
            this.endPoint = endPoint;
        }
    }

}
