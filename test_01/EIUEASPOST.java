package test_01;
import java.util.*;
public class EIUEASPOST {

        static Scanner sc = new Scanner (System.in);
        static StringBuilder sb = new StringBuilder();
    
        public static void main(String[] args){
            Vertex[] graph = readGraph();

            List<Vertex> list = new ArrayList<Vertex>();
            printPostOrder(graph[1], list);
            for( Vertex v: list){
                sb.append(v.id+" ");
            }
            System.out.println(sb);
        }

        static void printPostOrder(Vertex v, List<Vertex> list){
            if(v.left != null){
                printPostOrder(v.left, list);
            }
            if(v.right != null){
                printPostOrder(v.right, list);
            }
            list.add(v);
        }

        static Vertex[] readGraph(){
            int n = sc.nextInt();
           
            
            Vertex[] vertices = new Vertex[n+1];
            for(int i = 1; i<=n; ++i){
                vertices[i] = new Vertex(i);
            }

            for(int i = 1 ; i<=n; ++i){
                int a = sc.nextInt();
                vertices[i].left = a > 0 ? vertices[a] : null;
                int b = sc.nextInt();
                vertices[i].right = b > 0 ? vertices[b] : null;
            }

            return vertices;
        }

        static class Vertex {
            public int id;
            public Vertex left;
            public Vertex right;

            public Vertex(int id) {
                this.id = id;    }

        }
    }
