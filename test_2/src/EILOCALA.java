import java.util.*;

public class EILOCALA {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static long max;
    static int nodeMin;

    public static void main(String[] args) {
        Node[] graph = readGraph();

        max = 0;
        nodeMin = Integer.MAX_VALUE;
        dfs(graph[0], 0);

        for (Node v : graph) {
            v.visited = false;
        }

        max = 0;
        int storeNodeMin = nodeMin;
        nodeMin = Integer.MAX_VALUE;

        dfs(graph[storeNodeMin], 0);
        System.out.println(Math.min(storeNodeMin, nodeMin) + " " + max);
    }

    static void dfs(Node n, long sum) {
        n.visited = true;
        if (sum > max) {
            max = sum;
            nodeMin = n.id;
        } else if (sum == max) {
            nodeMin = Math.min(nodeMin, n.id);
        }
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
