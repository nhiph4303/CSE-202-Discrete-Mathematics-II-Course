import java.io.*;
import java.util.*;

public class EIMAXHTR {

    static InputReader sc;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        sc = new InputReader(System.in);
        
        Vertex[] graph = readGraph();

        int minVertex = -1;
        int maxHeight = -1;

        for (Vertex root : graph) {
            int currentHeight = getHeight(graph, root); 

            if (currentHeight > maxHeight || (currentHeight == maxHeight && root.id < minVertex)) {
                maxHeight = currentHeight;
                minVertex = root.id;
            }

        //reset lại status và level của đỉnh
        for (Vertex v : graph) {
                v.visited = false;
                v.level = 0;
            }
        }

        System.out.println(minVertex + " " + maxHeight);
    }

    public static int getHeight(Vertex[] graph, Vertex root) {
        dfs(root);

        int maxLevel = 0;

        for (Vertex v : graph) {
            if (v.level > maxLevel) {
                maxLevel = v.level;
                v.visited = false;
            }
        }
        return maxLevel;
    }

    public static void dfs(Vertex v) {
        v.visited = true;

        for (Vertex w : v.adjList) {
            if (!w.visited) {
                w.level = v.level + 1;
                dfs(w); 
            }
        }
    }

    public static Vertex[] readGraph() {
        int n = sc.nextInt();

        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; ++i) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < n-1; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].addAdjList(vertices[v]);
            vertices[v].addAdjList(vertices[u]);
        }

        return vertices;
    }

    public static class Vertex {
        public int id;
        public boolean visited;
        public int level = 0;
        public List<Vertex> adjList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjList(Vertex v) {
            adjList.add(v);
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
