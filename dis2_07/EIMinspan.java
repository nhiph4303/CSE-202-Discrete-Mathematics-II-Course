package dis2_07;

import java.io.*;
import java.util.*;

public class EIMinspan {

    static InputReader reader;
    static StringBuilder sb = new StringBuilder();
    static int countVertices;
    static int totalDis = 0;

    public static void main(String[] args) throws IOException {
        reader = new InputReader(System.in);
        Vertex[] graph = readGraph();
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1,e2)->{
            return Integer.compare(e1.weight, e2.weight);
        });
        Edge startEdge = new Edge(0, graph[0]);
        pq.add(startEdge);

        while(!pq.isEmpty()){
            Edge polledE = pq.poll();
            if (polledE.endpoint.visited) {
                continue;
            }
            polledE.endpoint.visited = true;
            totalDis += polledE.weight;
            countVertices--;

            for(Edge u: polledE.endpoint.adjacentEdges){
                if(u.endpoint.visited == false){
                    pq.add(u);
                }
            }
        }
        if(countVertices==0){
            System.out.println(totalDis);
        }
        else{
            System.out.println("-1");
        }
    }


    static Vertex[] readGraph(){
        int nVertices = reader.nextInt();
        countVertices = nVertices;
        int nEdges = reader.nextInt();

        Vertex[] vertices = new Vertex[nVertices];

        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < nEdges; i++) {
            int a = reader.nextInt();
            int b = reader.nextInt();
            int w = reader.nextInt();
            
            vertices[a].addAdjacentEdges(w, vertices[b]);
            vertices[b].addAdjacentEdges(w, vertices[a]);;
        }

        return vertices;
    }

    static class Edge{
        int weight;
        Vertex endpoint;

        public Edge(int weight, Vertex endpoint) {
            this.weight = weight;
            this.endpoint = endpoint;
        }    
    }

    static class Vertex{
        int id;
        boolean visited;
        List<Edge> adjacentEdges = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjacentEdges(int weight, Vertex endpoint){
            Edge e = new Edge(weight, endpoint);
            adjacentEdges.add(e);
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
