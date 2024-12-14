package dis2_05;

import java.io.*;
import java.util.*;

public class EIUSEFI2 {

    static InputReader sc;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        sc = new InputReader(System.in);

        HashMap<String, Vertex> map = readGraph();
        for (Map.Entry<String, Vertex> entry : map.entrySet()) {
            entry.getValue().adjList.sort((v1, v2) -> {
                return v1.name.compareToIgnoreCase(v2.name);
            });
        }

        String source = sc.next();
        String searchedWords = sc.next();
        dfs(map.get(source), searchedWords);

        System.out.println(sb);
    }

    public static void dfs(Vertex v, String searchedWords) {
        v.visited = true;
        boolean check = false;

        for (Vertex u : v.adjList) {
            if (u.visited == false) {
                check = true;
                dfs(u, searchedWords);
                v.paths += u.paths;
            }
        }
        if (!check && v.name.contains(searchedWords)) {
            v.paths++;
        }
        if (check && v.paths != 0) {
            sb.append(v.name + " " + v.paths + "\n");
        }

    }

    public static HashMap<String, Vertex> readGraph() {
        int n = sc.nextInt();
        int m = n - 1;

        HashMap<String, Vertex> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            String u = sc.next();
            String v = sc.next();

            if (map.get(u) == null) {
                Vertex vA = new Vertex(u);
                map.put(u, vA);
            }

            if (map.get(v) == null) {
                Vertex vB = new Vertex(v);
                map.put(v, vB);
            }
            map.get(u).addAdjList(map.get(v));
            map.get(v).addAdjList(map.get(u));
        }

        return map;
    }

    public static class Vertex {
        public String name;
        public boolean visited;
        public List<Vertex> adjList = new ArrayList<Vertex>();
        public int paths;

        public Vertex(String name) {
            this.name = name;
        }

        public void addAdjList(Vertex v) {
            adjList.add(v);
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
