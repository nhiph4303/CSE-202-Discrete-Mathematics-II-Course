
import java.util.*;

public class EIUEASPOST {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();

        if (n == 0) {
            System.out.println("");
            return;
        }

        Node[] nodes = readTree(n);
        List<Node> list = new ArrayList<>();
        printPostOrder(nodes[0], list);

        for (Node v : list) {
            sb.append(v.id).append(" ");
        }
        System.out.println(sb.toString());
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

    public static Node[] readTree(int n) {
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i + 1);
        }

        for (int i = 0; i < n; i++) {
            int leftIndex = sc.nextInt();
            if (leftIndex > 0 && leftIndex <= n) {
                nodes[i].left = nodes[leftIndex - 1];
            }
            int rightIndex = sc.nextInt();
            if (rightIndex > 0 && rightIndex <= n) {
                nodes[i].right = nodes[rightIndex - 1];
            }

        }

        return nodes;
    }

    public static class Node {
        public int id;
        public Node left;
        public Node right;

        public Node(int id) {
            this.id = id;
        }
    }
}
