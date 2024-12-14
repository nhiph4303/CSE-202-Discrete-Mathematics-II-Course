package dis2_05;

import java.io.*;
import java.util.*;

public class EIMINDISTA {

    static InputReader sc;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        sc = new InputReader(System.in);
        Vertex[] graph = readGraph();

        PriorityQueue<Vertex> pq = new PriorityQueue<>((e1, e2) -> {
            return Integer.compare(e1.cost, e2.cost);
        });

        pq.add(graph[0]);
        graph[0].cost = 0;
        
        while (!pq.isEmpty()) {
            Vertex polledV = pq.poll();

            if (polledV.visited) {
                continue;
            }

            if (polledV.cost == Integer.MAX_VALUE) {
                break;
            }

            for (Edge e : polledV.adjList) {
                if (e.endpoint.cost > e.weight + polledV.cost) {
                    e.endpoint.cost = e.weight + polledV.cost;
                    pq.add(e.endpoint);
                }
            }
        }
        for (int i = 1; i < graph.length; i++) {
            if (graph[i].cost != Integer.MAX_VALUE) {
                sb.append(graph[i].cost);
            } else {
                sb.append("-1");
            }
            sb.append(" ");
        }
        System.out.println(sb);
    }

    static Vertex[] readGraph() {
        int nVertices = sc.nextInt();
        int nEdges = sc.nextInt();

        Vertex[] vertices = new Vertex[nVertices];

        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < nEdges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            vertices[u].addAdjList(w, vertices[v]);
            vertices[v].addAdjList(w, vertices[u]);
            ;
        }

        return vertices;
    }

    static class Edge {
        int weight;
        Vertex endpoint;

        public Edge(int weight, Vertex endpoint) {
            this.weight = weight;
            this.endpoint = endpoint;
        }
    }

    public static class Vertex {
        int id;
        boolean visited;
        List<Edge> adjList = new ArrayList<>();
        int cost;

        public Vertex(int id) {
            this.id = id;
            this.cost = Integer.MAX_VALUE;
        }

        public void addAdjList(int weight, Vertex endpoint) {
            Edge e = new Edge(weight, endpoint);
            adjList.add(e);
        }
    }

    public static class InputReader {
        private byte[] inbuf = new byte[2 << 23];
        public int lenbuf = 0, ptrbuf = 0;
        public InputStream is;

        public InputReader(InputStream stream) throws IOException {

            inbuf = new byte[2 << 23];
            lenbuf = 0;
            ptrbuf = 0;
            is = System.in;
            lenbuf = is.read(inbuf);
        }

        public InputReader(FileInputStream stream) throws IOException {
            inbuf = new byte[2 << 23];
            lenbuf = 0;
            ptrbuf = 0;
            is = stream;
            lenbuf = is.read(inbuf);
        }

        public boolean hasNext() throws IOException {
            if (skip() >= 0) {
                ptrbuf--;
                return true;
            }
            return false;
        }

        public String nextLine() throws IOException {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!isSpaceChar(b) && b != ' ') { // when nextLine, ()
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public String next() {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b
                                        // != ' ')
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        private int readByte() {
            if (lenbuf == -1)
                throw new InputMismatchException();
            if (ptrbuf >= lenbuf) {
                ptrbuf = 0;
                try {
                    lenbuf = is.read(inbuf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (lenbuf <= 0)
                    return -1;
            }
            return inbuf[ptrbuf++];
        }

        private boolean isSpaceChar(int c) {
            return !(c >= 33 && c <= 126);
        }

        private double nextDouble() {
            return Double.parseDouble(next());
        }

        public Character nextChar() {
            return skip() >= 0 ? (char) skip() : null;
        }

        private int skip() {
            int b;
            while ((b = readByte()) != -1 && isSpaceChar(b))
                ;
            return b;
        }

        public int nextInt() {
            int num = 0, b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
                ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }

            while (true) {
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }

        public long nextLong() {
            long num = 0;
            int b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
                ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }

            while (true) {
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }
    }
}
