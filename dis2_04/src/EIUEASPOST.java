
import java.io.*;
import java.util.*;

public class EIUEASPOST {

    static InputReader sc;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        sc = new InputReader(System.in);
        int nNode = sc.nextInt();

        if (nNode == 0) {
            System.out.println("");
            return;
        }

        Node[] nodes = readTree(nNode);
        List<Node> list = new ArrayList<>();
        printPostOrder(nodes[0], list);

        for (Node v : list) {
            sb.append(v.id).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    public static void printPostOrder(Node v, List<Node> list) {
        if (v.left != null) {
            printPostOrder(v.left, list);
        }
        if (v.right != null) {
            printPostOrder(v.right, list);
        }
        list.add(v);
    }

    public static Node[] readTree(int nNode) {
        Node[] nodes = new Node[nNode];
        for (int i = 0; i < nNode; i++) {
            nodes[i] = new Node(i + 1);
        }

        for (int i = 0; i < nNode; i++) {
            int leftIndex = sc.nextInt();
            if (leftIndex > 0 && leftIndex <= nNode) {
                nodes[i].left = nodes[leftIndex - 1];
            }
            int rightIndex = sc.nextInt();
            if (rightIndex > 0 && rightIndex <= nNode) {
                nodes[i].right = nodes[rightIndex - 1];
            }
        }

        return nodes;
    }

    public static class Node {
        int id;
        Node left;
        Node right;

        public Node(int id) {
            this.id = id;
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
