
import java.util.*;

public class EIFBF {

    static StringBuilder sb = new StringBuilder();
    static Scanner sc = new Scanner(System.in);
    static int male;
    static int female;
    static int maxVertex;
    static List<Component> compList = new ArrayList<>();

    public static void main(String[] args) {
        Vertex[] graph = readGraph();

        for (int i = 1; i < graph.length; i++) {
            if (!graph[i].visited) {
                maxVertex = -1;
                male = 0;
                female = 0;
                dfs(graph[i]);
                compList.add(new Component(maxVertex, male, female));
            }
        }

        compList.sort((c1, c2) -> c1.maxVertex - c2.maxVertex);
        for (Component c : compList) {
            sb.append(c.maxVertex + " " + c.male + " " + c.female + "\n");
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

    static class Component {

        int maxVertex;
        int male;
        int female;

        public Component(int maxVertex, int male, int female) {
            this.female = female;
            this.male = male;
            this.maxVertex = maxVertex;
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
        public String gender;
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
