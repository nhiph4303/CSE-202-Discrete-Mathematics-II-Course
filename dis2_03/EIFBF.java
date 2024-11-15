package dis2_03;

import java.io.*;
import java.util.*;

public class EIFBF {

    static InputReader sc;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        sc = new InputReader(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        Vertex[] vertices = readGraph(n, m);

        List<int[]> comps = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (!vertices[i].visited) {
                List<Vertex> comp = new ArrayList<>();
                dfs(vertices[i], comp);

                int males = 0, females = 0, max = -1;
                for (Vertex v : comp) {
                    if ("Nu".equals(v.gender)) {
                        females++;
                    } else {
                        males++;
                    }
                    max = Math.max(max, v.id);
                }
                comps.add(new int[]{max, males, females});
            }
        }

        comps.sort((a, b) -> Integer.compare(a[0], b[0]));

        for (int[] comp : comps) {
            int max = comp[0];
            int males = comp[1];
            int females = comp[2];

            sb.append(max).append(" ")
                    .append(males).append(" ")
                    .append(females).append("\n");
        }
        
        System.out.println(sb.toString().trim());
    }

    static void dfs(Vertex v, List<Vertex> componentVertices) {
        v.visited = true;
        componentVertices.add(v);

        for (Vertex neighbor : v.adjacentVertices) {
            if (!neighbor.visited) {
                dfs(neighbor, componentVertices);
            }
        }
    }

    static Vertex[] readGraph(int n, int m) {
        Vertex[] vertices = new Vertex[n + 1];
        for (int i = 1; i <= n; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 1; i <= n; i++) {
            vertices[i].gender = sc.next();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            vertices[a].addAdjacentVertex(vertices[b]);
            vertices[b].addAdjacentVertex(vertices[a]);
        }

        return vertices;
    }

    static class Vertex {

        int id;
        String gender;
        boolean visited = false;
        List<Vertex> adjacentVertices = new ArrayList<>();

        Vertex(int id) {
            this.id = id;
        }

        void addAdjacentVertex(Vertex vertex) {
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
            if (lenbuf == -1) {
                throw new InputMismatchException();
            }
            if (ptrbuf >= lenbuf) {
                ptrbuf = 0;
                try {
                    lenbuf = is.read(inbuf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (lenbuf <= 0) {
                    return -1;
                }
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
            while ((b = readByte()) != -1 && isSpaceChar(b));
            return b;
        }

        public int nextInt() {
            int num = 0, b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
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
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
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
