
import java.util.*;

public class EIFBF2 {

    static StringBuilder sb = new StringBuilder();
    static Scanner sc = new Scanner(System.in);

    static int male;
    static int female;
    static List<Vertex> compList;

    public static void main(String[] args) {
        Vertex[] graph = readGraph();

        for (int i = 1; i < graph.length; i++) {
            if (!graph[i].visited) {
                compList = new ArrayList<>();
                male = 0;
                female = 0;

                dfs(graph[i]);

                for (Vertex v : compList) {
                    v.totalMales = male;
                    v.totalFemales = female;
                }
            }
        }

        for (int i = 1; i < graph.length; i++) {
            sb.append(i + " " + graph[i].totalMales + " " + graph[i].totalFemales).append("\n");
        }

        System.out.print(sb);
    }

    public static void dfs(Vertex v) {
        v.visited = true;
        compList.add(v);

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
        public int id;
        public String gender;
        public boolean visited;
        public int totalMales, totalFemales;
        public List<Vertex> adjList = new ArrayList<>();

        public Vertex(int id, String gender) {
            this.id = id;
            this.gender = gender;
        }

        public void addAdjList(Vertex v) {
            adjList.add(v);
        }
    }

}
