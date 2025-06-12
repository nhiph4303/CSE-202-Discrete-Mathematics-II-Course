
import java.util.*;

public class EITREORD {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static int preIndex = 0;
    static Map<Integer, Integer> inOrderMap = new HashMap<>();

    public static void main(String[] args) {
        int n = sc.nextInt();

        if (n == 0) {
            System.out.println("");
            return;
        }

        int[] preOrder = new int[n];
        int[] inOrder = new int[n];

        for (int i = 0; i < n; i++) {
            preOrder[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            inOrder[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            inOrderMap.put(inOrder[i], i);
        }

        Node root = buildTree(preOrder, 0, n - 1);

        List<Node> list = new ArrayList<>();
        printPostOrder(root, list);

        for (Node v : list) {
            sb.append(v.id).append(" ");
        }
        System.out.println(sb.toString().trim());
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

    public static class Node {

        public int id;
        public Node left;
        public Node right;

        public Node(int id) {
            this.id = id;
        }
    }
}
