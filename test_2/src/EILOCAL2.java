
import java.util.*;

public class EILOCAL2 {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static long max;

    public static void main(String[] args) {
        Node[] graph = readGraph();
        max = 0;
        dfs(graph[0], 0);
        System.out.println(max);
    }

    static void dfs(Node n, long sum) {
        n.visited = true;
        max = Math.max(max, sum);

        for (Edge e : n.edges) {
            if (!e.endPoint.visited) {
                dfs(e.endPoint, sum + e.weight);
            }
        }
    }

    static Node[] readGraph() {
        int n = sc.nextInt();
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();

            nodes[u].edges.add(new Edge(nodes[v], weight));
            nodes[v].edges.add(new Edge(nodes[u], weight));
        }

        return nodes;
    }

    static class Node {
        int id;
        boolean visited;
        List<Edge> edges = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }
    }

    static class Edge {
        long weight;
        Node endPoint;

        public Edge(Node n, long w) {
            this.endPoint = n;
            this.weight = w;
        }
    }
}
