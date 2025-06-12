import java.util.*;

public class EIBUYGIFTS {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Vertex[] graph = readGraph();
        for (Vertex v : graph) {
            sb.append(v.friendsNear + "\n");
        }
        System.out.println(sb);
    }

    static Vertex[] readGraph() {
        int n = sc.nextInt();
        int m = sc.nextInt();

        int currentDate = sc.nextInt();
        int kDays = sc.nextInt();

        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; ++i) {
            vertices[i] = new Vertex(i);
            int birthdayInput = sc.nextInt();
            vertices[i].birthday = birthdayInput;
        }

        for (int i = 0; i < m; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            if (currentDate <= vertices[u].birthday) {
                if (vertices[u].birthday - currentDate <= kDays) {
                    vertices[v].friendsNear++;
                }
            }
            if (currentDate <= vertices[v].birthday) {
                if (vertices[v].birthday - currentDate <= kDays) {
                    vertices[u].friendsNear++;
                }
            }
        }

        return vertices;
    }

    static class Vertex {
        public int id;
        public int friendsNear;
        public int birthday;
        public List<Vertex> adjList = new ArrayList<Vertex>();

        public Vertex(int id) {
            this.id = id;
        }
    }
}
