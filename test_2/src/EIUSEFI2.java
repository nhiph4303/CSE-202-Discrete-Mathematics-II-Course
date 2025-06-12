
import java.io.*;
import java.util.*;

public class EIUSEFI2 {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

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
}
