package dis2_05;

import java.io.*;
import java.util.*;

public class EIUWBT {

    static InputReader sc;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        sc = new InputReader(System.in);
        

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
            double sale = sc.nextDouble();
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < n-1; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].addAdjList(vertices[v]);
            // vertices[v].addAdjList(vertices[u]);
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

// Cây cân bằng yếu
// Cách 1: Bruteforce

// Khai báo biến.....(chênh lệch min)

// Thử từng đỉnh 1 -> n
// 	Nếu đỉnh đó có khác 2 nhánh thì bỏ qua
// 	Nếu đỉnh có đúng 2 nhánh:
// 	- Tính chênh lệch giữa 2 nhánh
// 		-> Tính tổng trọng số từng nhánh
// 			-> Đánh số đỉnh i là visited
// 			-> lấy đỉnh kề tại 0 và dfs
// 			-> lấy đỉnh kề tại 1 và dfs
// 	- So sánh chệnh lệch với chênh lệch minDiff

// int bfs(Vertex){
// 	int total
// }

// Cách 2:
// Bước 1: tính tổng trọng số các đỉnh of đồ thị
// Bước 2:
//     dfs từ 1 đỉnh bất kì
// Bước 3:
//     nếu đỉnh đang được duyệt có 2 con 
//     thì dfs 1 nhánh để tìm được tổng trọng số 1 nhánh T1,
//     sau đó tính trọng số nhánh còn lại bằng cách lấy T - T1 - trọng số của đỉnh đang duyệt.

