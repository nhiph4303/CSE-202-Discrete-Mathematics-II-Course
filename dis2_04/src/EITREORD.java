import java.io.*;
import java.util.*;

public class EITREORD {
    static InputReader sc;
    static StringBuilder sb = new StringBuilder();
    static int preIndex = 0;
    static Map<Integer, Integer> inOrderMap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        sc = new InputReader(System.in);
        int nNode = sc.nextInt();

        if (nNode == 0) {
            System.out.println("");
            return;
        }

        int[] preOrder = new int[nNode];
        int[] inOrder = new int[nNode];

        for (int i = 0; i < nNode; i++) {
            preOrder[i] = sc.nextInt();
        }
        for (int i = 0; i < nNode; i++) {
            inOrder[i] = sc.nextInt();
        }

        for (int i = 0; i < nNode; i++) {
            inOrderMap.put(inOrder[i], i);
        }

        Node root = buildTree(preOrder, 0, nNode - 1);

        List<Node> list = new ArrayList<>();
        printPostOrder(root, list);

        for (Node v : list) {
            sb.append(v.id).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    public static Node buildTree(int[] preOrder, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }

        int rootVal = preOrder[preIndex++];
        Node root = new Node(rootVal);

        int inIndex = inOrderMap.get(rootVal);

        root.left = buildTree(preOrder, inStart, inIndex - 1);
        root.right = buildTree(preOrder, inIndex + 1, inEnd);

        return root;
    }

    public static void printPostOrder(Node v, List<Node> list) {
        if (v == null) {
            return;
        }
        if (v.left != null) {
            printPostOrder(v.left, list);
        }
        if (v.right != null) {
            printPostOrder(v.right, list);
        }
        list.add(v);
    }

    public static class Node {
        public int id;
        public Node left;
        public Node right;

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
