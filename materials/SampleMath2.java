
import java.util.*;

class SampleMath2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Vertex[] vertices = readGraph(sc);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Edge2> edges = new ArrayList<>();
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; ++i) {
            vertices[i] = new Vertex(i);
        }
        //Read list of edges
        for (int i = 0; i < m; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            Edge2 e = new Edge2(vertices[u], vertices[v], w);
            edges.add(e);
        }
        edges.sort((e1, e2) -> e1.weight - e2.weight);

        //tao disjoin set
        int[] root = new int[vertices.length];
        int[] height = new int[vertices.length];
        for (int i = 0; i < root.length; ++i) {
            root[i] = i;
        }

        List<Edge2> selectedEdges = new ArrayList<>();
        for (Edge2 e : edges) {
            int u = e.endpoint1.id;
            int v = e.enpoint2.id;

            int uRoot = findRoot(u, root);
            int vRoot = findRoot(v, root);
            if (uRoot != vRoot) {
                selectedEdges.add(e);
                union(v, v, root, height);
                if (selectedEdges.size() == n - 1) {
                    break;
                }
            }

        }
        if (selectedEdges.size() == n - 1) {
            System.out.println("Cay khung gom cac dinh trong selectedEdges");
        } else {
            System.out.println("Do thi khong lien thong");
        }
    }

    class Edge2 {

        Vertex endpoint1;
        Vertex enpoint2;
        int weight;

        public Edge2(Vertex u, Vertex v, int w) {
            this.weight = w;
            endpoint1 = u;
            enpoint2 = 2;
        }
    }

    static void union(int vertex1, int vertex2, int[] root, int[] height) {
        int root1 = findRoot(vertex1, root);
        int root2 = findRoot(vertex2, root);

        if (height[root1] < height[root2]) {
            root[root1] = root2;
        } else {
            root[root2] = root1;
            if (height[root1] == height[root2]) {
                height[root1]++;
            }
        }
    }

    static int findRoot(int vertexId, int[] root) {
        if (root[vertexId] == vertexId) {
            return vertexId;
        }

        int foundRoot = findRoot(root[vertexId], root);
        root[vertexId] = foundRoot;
        return foundRoot;
    }

    private int findSpanningTreeUsingPrim(Vertex[] graph) {
        for (Vertex vertex : graph) {
            vertex.cost = Integer.MAX_VALUE;
            vertex.visited = false;
        }

        //Chon 1 dinh bat ki de xuat phat
        graph[0].cost = 0;
        graph[0].previous = graph[0];

        int countUnvisted = graph.length;
        while (countUnvisted > 0) {
            Vertex min = findUnvistedVertexWithMinCost(graph);
            if (min == null) {
                return -1;
            }
            count--;
            for (Edge e : min.adjacentVertices) {
                if (e.endpoint.cost > e.weight) {
                    e.endpoint.cost = e.weight;
                    e.endpoint
                    ..previous = min;
                }
            }
        }
    }

    private Vertex findUnvistedVertexWithMinCost(Vertex[] graph) {
        int min = Integer.MAX_VALUE;
        int id = -1;
        for (Vertex vertex : graph) {
            if (!vertex.visited) {
                if (vertex.cost < min) {
                    id = vertex.id;
                    min = vertex.cost;
                }

            }

        }
        return id == -1 ? null : graph[id];
    }

    private static void printPrim(Vertex[] vertices) {
        PriorityQueue<Vertex> q = new PriorityQueue<SampleMath2.Vertex>();
        vertices[0].cost = 0;
        for (Vertex v : vertices) {
            q.add(v);
        }
        int count = 0;
        int total = 0;
        while (count < vertices.length - 1 && q.size() > 0) {
            Vertex min = q.remove();
            if (min.visited) {
                continue;
            }
            count++;
            total += min.cost;
            min.visited = true;

            if (min.previous != null) {
                System.out.println(min.id + " " + min.previous.id + " " + min.cost);
            }

            for (Edge e : min.adjacentVertices) {
                if (e.endpoint.visited) {
                    continue;
                }
                if (e.endpoint.previous == null || e.endpoint.cost > e.weight) {
                    e.endpoint.cost = e.weight;
                    e.endpoint.previous = min;

                    q.add(e.endpoint);
                }
            }
        }

    }

//	static int dfs(Vertex vertex) {
//		int total = 0;
//		
//	}
    static Vertex[] readGraph(Scanner sc) {
        int n = sc.nextInt();
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }

        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            Vertex u = vertices[sc.nextInt()];
            Vertex v = vertices[sc.nextInt()];
            int w = sc.nextInt();
            u.addVertex(v, w);
            v.addVertex(u, w);
        }

        return vertices;
    }

    static class Vertex implements Comparable<Vertex> {

        public int id;
        public boolean visited = false;
        public int cost = Integer.MAX_VALUE;
        public Vertex previous = null;
        public List<Edge> adjacentVertices = new ArrayList<Edge>();
        // public HashMap<Integer, Vertex> adjacentVertices = new HashMap<Integer,
        // Vertex>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addVertex(Vertex vertex, int w) {
            adjacentVertices.add(new Edge(vertex, w));
        }

        @Override
        public int compareTo(SampleMath2.Vertex o) {
            return this.cost - o.cost;
        }
    }

    static class Edge {

        public int weight;
        public Vertex endpoint;

        public Edge(Vertex endpoint, int w) {
            this.endpoint = endpoint;
            this.weight = w;
        }
    }
}
