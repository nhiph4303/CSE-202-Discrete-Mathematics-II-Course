import java.util.*;

public class EIFBF {

    static StringBuilder sb = new StringBuilder();
    static Scanner sc = new Scanner(System.in);
    static int male;
    static int female;
    static int maxVertex;

    public static void main(String[] args) {
        Vertex[] graph = readGraph();
        List<int[]> results = new ArrayList<>();

        for (int i = 1; i < graph.length; i++) {
            if (!graph[i].visited) {
                maxVertex = -1;
                male = 0;
                female = 0;
                dfs(graph[i]);
                results.add(new int[]{maxVertex, male, female});
            }
        }

        // Sorting based on the representative vertex
        results.sort((a, b) -> a[0] - b[0]);

        // Outputting results
        for (int[] result : results) {
            sb.append(result[0] + " " + result[1] + " " + result[2] + "\n");
        }
        System.out.println(sb);
    }

    static void dfs(Vertex v) {
        v.visited = true;
        maxVertex = Math.max(maxVertex, v.id);

        if (v.gender.equalsIgnoreCase("Nam")) {
            male++;
        } else {
            female++;
        }

        for (Vertex u : v.adjList) {
            if (!u.visited) {
                dfs(u);
            }
        }
    }

    static Vertex[] readGraph() {
        int n = sc.nextInt();
        int m = sc.nextInt();

        Vertex[] vertices = new Vertex[n + 1];
        for (int i = 1; i <= n; i++) {
            String gender = sc.next();
            vertices[i] = new Vertex(i, gender);
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].addAdjList(vertices[v]);
            vertices[v].addAdjList(vertices[u]);
        }
        return vertices;
    }

    static class Vertex {
        int id;
        boolean visited;
        String gender;
        List<Vertex> adjList = new ArrayList<>();

        public Vertex(int id, String gender) {
            this.id = id;
            this.gender = gender;
        }

        public void addAdjList(Vertex v) {
            adjList.add(v);
        }
    }
}
