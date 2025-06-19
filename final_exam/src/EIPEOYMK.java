
import java.util.*;

public class EIPEOYMK {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Vertex[] graph = readGraph();
        int root = sc.nextInt();

        bfs(graph[root]);

        Map<Integer, ArrayList<Integer>> levelMap = new HashMap<>();
        for (Vertex v : graph) {
            if (!levelMap.containsKey(v.level)) {
                levelMap.put(v.level, new ArrayList<>());
            }
            levelMap.get(v.level).add(v.id);
        }

        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            int u = sc.nextInt();

            List<Integer> friendList = levelMap.get(u);

            if (friendList == null) {
                sb.append("-1\n");
            } else {
                for (int id : friendList) {
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
            Vertex u = q.poll();

            for (Vertex y : u.adjList) {
                if (!y.visited) {
                    y.visited = true;
                    y.level = u.level + 1;
                    q.add(y);
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
            v.adjList.sort((v1, v2) -> v1.id - v2.id);
        }
        return vertices;
    }

    public static class Vertex {

        int id;
        int level;
        boolean visited;
        List<Vertex> adjList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjList(Vertex v) {
            adjList.add(v);
        }
    }
}
