
import java.util.*;

public class EIUEASPOST {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
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

        public int id;
        public Node left;
        public Node right;

        public Node(int id) {
            this.id = id;
        }
    }
}
