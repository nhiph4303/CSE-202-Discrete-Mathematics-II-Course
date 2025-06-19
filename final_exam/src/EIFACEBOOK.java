
import java.util.*;

public class EIFACEBOOK {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        Vertex[] graph = readGraph(n, m);

        for (int i = 1; i < graph.length; i++) {
            int count = 0;

            for (Vertex v : graph[i].adjList) {
                if (!v.gender.equalsIgnoreCase(graph[i].gender)) {
                    count++;
                }
            }

            sb.append(count + " ");
        }

        System.out.println(sb);
    }

    static Vertex[] readGraph(int n, int m) {
        Vertex[] vertices = new Vertex[n + 1];
        for (int i = 1; i <= n; i++) {
            vertices[i] = new Vertex(sc.next());
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[v].addAdjList(vertices[u]);
            vertices[u].addAdjList(vertices[v]);
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
