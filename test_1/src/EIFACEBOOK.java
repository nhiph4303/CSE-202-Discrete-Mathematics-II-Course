
import java.util.*;

public class EIFACEBOOK {

    static StringBuilder sb = new StringBuilder();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Vertex[] graph = readGraph();
        for (int i = 1; i < graph.length; i++) {
            int count = 0;
            Vertex v = graph[i];
            for (Vertex y : v.adjList) {
                if (!v.gender.equalsIgnoreCase(y.gender)) {
                    count++;
                }
            }
            sb.append(count + " ");
        }

        System.out.println(sb);
    }

    static Vertex[] readGraph() {
        int n = sc.nextInt();
        int m = sc.nextInt();

        Vertex[] vertices = new Vertex[n + 1];
        for (int i = 1; i <= n; i++) {
            String gender = sc.next();
            vertices[i] = new Vertex(gender);
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

        public String gender;
        public List<Vertex> adjList = new ArrayList<>();

        public Vertex(String gender) {
            this.gender = gender;
        }

        public void addAdjList(Vertex v) {
            if (!adjList.contains(v)) {
                adjList.add(v);
            }
        }

    }

}
