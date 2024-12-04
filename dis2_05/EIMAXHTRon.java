package dis2_05;

import java.io.*;
import java.util.*;

public class EIMAXHTRon {

    static InputReader reader;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        reader = new InputReader(System.in);
        Vertex[] graph = readGraph();

        Vertex vA = findFurthest(graph[0], graph);
        Vertex vB = findFurthest(vA, graph);

        System.out.println(Math.min(vA.id, vB.id)+" "+vB.level);
    }
    static Vertex findFurthest(Vertex v, Vertex[] graph){
        Vertex maxVertex = null;
        int maxHeight = -1;
        for(Vertex o: graph){
            o.level = 0;
        }
        dfs(v);

            for (Vertex u : graph) {
                if (u.level > maxHeight) {
                    maxHeight = u.level;
                    maxVertex = u;
                }
                u.visited = false;
            }
        return maxVertex;
    }

    static void dfs(Vertex v) {
        v.visited = true;

        for (Vertex w : v.adjacentVertices) {
            if (w.visited == false) {
                w.level = v.level + 1;
                dfs(w);
            }
        }
    }

    static Vertex[] readGraph() {
        int nVertices = reader.nextInt();
        int nEdges = nVertices - 1;

        Vertex[] vertices = new Vertex[nVertices];
        for (int i = 0; i < nVertices; ++i) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < nEdges; ++i) {
            int a = reader.nextInt();
            int b = reader.nextInt();

            vertices[a].addAdjacentVertices(vertices[b]);
            vertices[b].addAdjacentVertices(vertices[a]);
        }

        for (int i = 0; i < nVertices; i++) {
            vertices[i].adjacentVertices.sort((v1, v2) -> {
                int compare = Integer.compare(v1.id, v2.id);
                return compare;
            });
        }
        return vertices;
    }

    static class Vertex {
        public int id;
        public boolean visited;
        public List<Vertex> adjacentVertices = new ArrayList<Vertex>();
        int level = 0;

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjacentVertices(Vertex vertex) {
            adjacentVertices.add(vertex);
        }
    }

    static class InputReader {
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
