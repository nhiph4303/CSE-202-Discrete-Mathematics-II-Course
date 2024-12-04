package dis2_05;

import java.io.*;
import java.util.*;

public class EIUWBT {

    static InputReader reader;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        reader = new InputReader(System.in);
        Vertex[] graph = readGraph();
        long minDif = Long.MAX_VALUE;
        long leftWeight = -1;
        long rightWeight = -1;
        Vertex minNode = null;
        for (int i = 1; i < graph.length; i++) {
            Vertex v = graph[i];
            if (v.adjacentVertices.size() == 2) {
                v.visited = true;
                Vertex leftV = v.adjacentVertices.get(0);
                dfs(leftV);
                Vertex rightV = v.adjacentVertices.get(1);
                dfs(rightV);
                v.dif = Math.abs(leftV.weight - rightV.weight);
                if (v.dif < minDif) {
                    minDif = v.dif;
                    leftWeight = leftV.weight;
                    rightWeight = rightV.weight;
                    minNode = v;
                }
                for (int j = 1; j < graph.length; j++) {
                    graph[j].visited = false;
                    graph[j].weight = graph[j].originalWeight;
                }
            }
        }
        if (minDif == Long.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(
                    minNode.id + " " + Math.min(leftWeight, rightWeight) + " " + Math.max(leftWeight, rightWeight));
        }
    }

    static void dfs(Vertex v) {
        v.visited = true;

        for (Vertex u : v.adjacentVertices) {
            if (u.visited == false) {
                dfs(u);
                v.weight += u.weight;
            }
        }
    }

    static Vertex[] readGraph() {
        int nVertices = reader.nextInt();
        int nEdges = nVertices - 1;

        Vertex[] vertices = new Vertex[nVertices + 1];
        for (int i = 1; i <= nVertices; ++i) {
            vertices[i] = new Vertex(i);
            int weightInput = reader.nextInt();
            vertices[i].originalWeight = weightInput;
            vertices[i].weight = vertices[i].originalWeight;
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
        public int id;
        public boolean visited;
        public List<Vertex> adjacentVertices = new ArrayList<Vertex>();
        long weight;
        long originalWeight;
        long leftWeight;
        long rightWeight;
        long dif = -1;

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
