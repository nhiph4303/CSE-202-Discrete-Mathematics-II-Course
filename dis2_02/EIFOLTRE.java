
import java.io.*;
import java.util.*;

public class EIFOLTRE {

    static InputReader sc;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        sc = new InputReader(System.in);
        HashMap<String, Vertex> vertices = readGraph();

        dfs(vertices.get(sc.next()), 1);
        System.out.println(sb);
    }

    static void dfs(Vertex v, int nDashes) {
        v.visited = true;
        for (int i = 0; i < nDashes; ++i) {
            sb.append("-");
        }
        sb.append(v.id).append("\n");
        for (int i = 0; i < v.adjecentVertices.size(); i++) {
            if (v.adjecentVertices.get(i).visited == false) {
                dfs(v.adjecentVertices.get(i), nDashes + 3);
            }
        }
    }

    static HashMap<String, Vertex> readGraph() {
        int nVertices = sc.nextInt();
        HashMap<String, Vertex> vertices = new HashMap<>();

        for (int i = 1; i < nVertices; ++i) {
            String u = sc.next();
            String v = sc.next();

            Vertex vertexU = vertices.get(u);
            if (vertexU == null) {
                vertexU = new Vertex(u);
                vertices.put(u, vertexU);
            }

            Vertex vertexV = vertices.get(v);
            if (vertexV == null) {
                vertexV = new Vertex(v);
                vertices.put(v, vertexV);
            }

            vertexU.addAdjacentVertices(vertexV);
            vertexV.addAdjacentVertices(vertexU);
        }

        for (Vertex v : vertices.values()) {
            Collections.sort(v.adjecentVertices, (v1, v2) -> v1.id.compareToIgnoreCase(v2.id));
        }

        return vertices;
    }

    static class Vertex {

        String id;
        boolean visited;
        List<Vertex> adjecentVertices = new ArrayList<>();

        public Vertex(String id) {
            this.id = id;
        }

        public void addAdjacentVertices(Vertex v) {
            adjecentVertices.add(v);
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
