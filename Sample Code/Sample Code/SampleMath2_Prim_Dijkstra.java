import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class SampleMath2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Vertex[] vertices = readGraph(sc);

		printPrim(vertices);
	}


	private static void findShortPathUsingDijkstra(Vertex[] vertices, Vertex target){
		//Lặp lại n lần
		for(int i = 0; i<vertices.length; ++i){
			//Tìm đỉnh unvisited và distance nhỏ nhất
			Vertex minVertex = findUnvistedMinDistanceVertex(vertices);
			
			if(minVertex==target|| minVertex.cost==Integer.MAX_VALUE){
				break;
			}

			for(Edge e: minVertex.adjacentVertices){
				Vertex v = e.endpoint;
				int w = e.weight;
				if(v.visited ==false&& v.cost<minVertex.cost+ w){
					v.cost = minVertex.cost+ w;
					v.previous = minVertex;
				}
			}
		}
	}

	private static void findShortPathUsingDijkstra2(Vertex[] vertices, Vertex target){
		//Lặp lại n lần
		PriorityQueue<Vertex> queue = new PriorityQueue<>();
		queue.add(vertices[0]);

		while(!queue.isEmpty()){
			Vertex minVertex = queue.remove();
			if(minVertex.visited) continue;
			minVertex.visited  = true;

			if(minVertex==target|| minVertex.cost==Integer.MAX_VALUE){
				break;
			}
			Vertex orginVertex = vertices[minVertex.id];	
			for(Edge e: orginVertex.adjacentVertices){
				Vertex v = e.endpoint;
				int w = e.weight;
				if(v.visited ==false&& v.cost<orginVertex.cost+ w){
					v.cost = orginVertex.cost+ w;
					v.previous = orginVertex;
					
					Vertex clone = new Vertex(v.id);
					clone.cost = v.cost;
				
					queue.add(clone);
				}
			}
		}
		
	}

	static Vertex findUnvistedMinDistanceVertex(Vertex[] vertices){
		Vertex result = null;
		for(int i = 0; i<vertices.length; ++i){
			if(vertices[i].visited==false){
				if(result==null||vertices[i].cost< result.cost){
					result = vertices[i];
				}
			}
		}
		return result;
	}




	private static void printPrim(Vertex[] vertices) {
		PriorityQueue<Vertex> q = new PriorityQueue<SampleMath2.Vertex>();
		vertices[0].cost = 0;
		for (Vertex v : vertices) {
			q.add(v);
		}
		int count = 0;
		int total = 0;
		while (count < vertices.length - 1 && q.size() > 0) {
			Vertex min = q.remove();
			if (min.visited)
				continue;
			count++;
			total += min.cost;
			min.visited = true;
			
			if (min.previous != null) {
				System.out.println(min.id + " " + min.previous.id + " " + min.cost);
			}

		
			for (Edge e : min.adjacentVertices) {
				if (e.endpoint.visited)
					continue;
				if (e.endpoint.previous == null || e.endpoint.cost > e.weight) {
					e.endpoint.cost = e.weight;
					e.endpoint.previous = min;
					
					q.add(e.endpoint);
				}
			}
		}

	}

//	static int dfs(Vertex vertex) {
//		int total = 0;
//		
//	}

	static Vertex[] readGraph(Scanner sc) {
		int n = sc.nextInt();
		Vertex[] vertices = new Vertex[n];
		for (int i = 0; i < n; i++) {
			vertices[i] = new Vertex(i);
		}

		int m = sc.nextInt();
		for (int i = 0; i < m; i++) {
			Vertex u = vertices[sc.nextInt()];
			Vertex v = vertices[sc.nextInt()];
			int w = sc.nextInt();
			u.addVertex(v, w);
			v.addVertex(u, w);
		}

		return vertices;
	}

	static class Vertex implements Comparable<Vertex> {
		public int id;
		public boolean visited = false;
		public int cost = Integer.MAX_VALUE;
		public Vertex previous = null;
		public List<Edge> adjacentVertices = new ArrayList<Edge>();
		// public HashMap<Integer, Vertex> adjacentVertices = new HashMap<Integer,
		// Vertex>();

		public Vertex(int id) {
			this.id = id;
		}

		public void addVertex(Vertex vertex, int w) {
			adjacentVertices.add(new Edge(vertex, w));
		}

		@Override
		public int compareTo(SampleMath2.Vertex o) {
			return this.cost - o.cost;
		}
	}

	static class Edge {
		public int weight;
		public Vertex endpoint;

		public Edge(Vertex endpoint, int w) {
			this.endpoint = endpoint;
			this.weight = w;
		}
	}
}