package dis2_05;

import java.io.*;
import java.util.*;

class EiuWBTsum {
    static InputReader reader;
    static StringBuilder sb = new StringBuilder();
    static Vertex mainNode = new Vertex(Integer.MAX_VALUE);
    static long totalWeight;

    public static void main(String[] args) throws IOException {
        reader = new InputReader(System.in);
        mainNode.leftWeight = Long.MAX_VALUE;
        mainNode.rightWeight = 0;
        Vertex[] graph = readGraph();
        DFS(graph[2]);
        if (mainNode.id == Integer.MAX_VALUE) {
            sb.append("-1");
        } else {
            sb.append(mainNode.id + " " + Math.min(mainNode.leftWeight, mainNode.rightWeight) + " "
                    + Math.max(mainNode.rightWeight, mainNode.leftWeight));
        }
        System.out.println(sb);

    }

    static long DFS(Vertex v) {
        long temp = v.weight;
        v.visited = true;
        if (v.adjacentVertices.size() == 2) {
            for (Vertex u : v.adjacentVertices) {
                if (!u.visited) {
                    DFS(u);
                    v.leftWeight += u.weight;
                    v.weight += u.weight;
                }

                v.rightWeight = totalWeight - v.leftWeight - temp;
                if (v.getDif() < mainNode.getDif()) {
                    mainNode = v;
                } else if (v.getDif() == mainNode.getDif() && v.id < mainNode.id) {
                    mainNode = v;
                }

            }
        } else {
            for (Vertex u : v.adjacentVertices) {
                if (!u.visited) {
                    DFS(u);
                    v.weight += u.weight;
                }
            }
        }

        return v.weight;
    }

    static Vertex[] readGraph() {
        int nVertices = reader.nextInt();
        int nEdges = nVertices - 1;

        Vertex[] vertices = new Vertex[nVertices + 1];
        for (int i = 1; i <= nVertices; ++i) {
            vertices[i] = new Vertex(i);
            int weightInput = reader.nextInt();
            vertices[i].weight = weightInput;
            totalWeight += vertices[i].weight;
        }

        for (int i = 0; i < nEdges; ++i) {
            int a = reader.nextInt();
            int b = reader.nextInt();

            vertices[a].addAdjacentVertices(vertices[b]);
            vertices[b].addAdjacentVertices(vertices[a]);
        }
        return vertices;
    }

    static class Vertex {
        int id;
        boolean visited;
        List<Vertex> adjacentVertices;
        long leftWeight;
        long rightWeight;
        long weight;

        public Vertex(int id) {
            this.id = id;
            adjacentVertices = new ArrayList<>();
        }

        public void addAdjacentVertices(Vertex v) {
            adjacentVertices.add(v);
        }

        public long getDif() {
            return Math.abs(leftWeight - rightWeight);
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
