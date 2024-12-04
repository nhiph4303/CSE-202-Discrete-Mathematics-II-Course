package dis2_03;

import java.io.*;
import java.util.*;

public class EIFBF {

    static InputReader sc;
    static StringBuilder sb = new StringBuilder();
    static int male;
    static int female;
    static int maxVertex;
    static List<Component> compList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        sc = new InputReader(System.in);
        Vertex[] graph = readGraph();
        
        for (int i = 1; i < graph.length; i++) {
            if (!graph[i].visited) {
                maxVertex = -1;
                male = 0;
                female = 0;
                dfs(graph[i]);
                compList.add(new Component(maxVertex, male, female));
            }
        }

        Collections.sort(compList, (c1, c2) -> c1.maxVertex - c2.maxVertex);
        for (Component c : compList) {
            sb.append(c.maxVertex + " " + c.male + " " + c.female + "\n");
        }
        System.out.println(sb);
    }

    public static class Component {
        int maxVertex;
        int male;
        int female;

        public Component(int maxVertex, int male, int female) {
            this.maxVertex = maxVertex;
            this.male = male;
            this.female = female;
        }
    }

    public static void dfs(Vertex v) {
        v.visited = true;
        maxVertex = Math.max(maxVertex, v.id);
        if (v.gender.equalsIgnoreCase("Nam")) {
            male++;
        } else {
            female++;
        }
        for (Vertex vertex : v.adjList) {
            if (!vertex.visited) {
                vertex.visited = true;
                dfs(vertex);
            }
        }
    }

    public static Vertex[] readGraph() {
        int n = sc.nextInt();
        int m = sc.nextInt();

        Vertex[] vertices = new Vertex[n + 1];
        for (int i = 1; i <= n; i++) {
            vertices[i] = new Vertex(i, sc.next());
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            vertices[u].addAdjList(vertices[v]);
            vertices[v].addAdjList(vertices[u]);
        }

        return vertices;
    }

    public static class Vertex {

        public int id;
        public String gender;
        public boolean visited;
        public List<Vertex> adjList = new ArrayList<>();

        public Vertex(int id, String gender) {
            this.id = id;
            this.gender = gender;
        }

        public void addAdjList(Vertex vertex) {
            adjList.add(vertex);
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
