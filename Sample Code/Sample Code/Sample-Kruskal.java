import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;

class Main {
	static InputReader reader;

	public static void main(String[] args) throws IOException {
		reader = new InputReader(System.in);
		int n = reader.nextInt();
		int m = reader.nextInt();

		Vertex[] vertices = new Vertex[n];
		for (int i = 0; i < n; ++i) {
			vertices[i] = new Vertex(i);
		}
		List<Edge> edges = new ArrayList<>();

		for (int i = 0; i < m; ++i) {
			int u = reader.nextInt();
			int v = reader.nextInt();

			int weight = reader.nextInt();

			Edge e = new Edge();
			e.endpoint1 = vertices[u];
			e.endpoint2 = vertices[v];
			e.weight = weight;
			edges.add(e);
		}

		Collections.sort(edges);

		List<Edge> selectedEdges = new ArrayList<>();
		DisjointSet disjointSet = new DisjointSet(n);

		for (int i = 0; i < m; ++i) {
			Edge e = edges.get(i);
			Vertex u = e.endpoint1;
			Vertex v = e.endpoint2;

			boolean isSame = disjointSet.isSameTree(u.id, v.id);
			if (!isSame) {
				selectedEdges.add(e);
				System.out.println(e);
				disjointSet.union(u.id, v.id);
				if (selectedEdges.size() == n - 1) {
					break;
				}
			}
		}

		if (selectedEdges.size() == n - 1) {

		} else {
			// Không tìm được cây khung-> đồ thi khôngg liên thông
			// Số thành phần liên thông: n-selectedEdges.size();
		}

	}

	static class Edge implements Comparable<Edge> {
		Vertex endpoint1;
		Vertex endpoint2;
		int weight;

		@Override
		public int compareTo(Edge o) {
			return weight - o.weight;
		};

		@Override
		public String toString() {
			return endpoint1.id + " " + endpoint2.id + " " + weight;
		}
	}

//    static Vertex[] readGraph(int nVertices, int nEdges) {
//        // Tao ra mảng để lưu các đỉnh và khởi tạo các đỉnh
//        Vertex[] vertices = new Vertex[nVertices + 1];
//        for (int i = 1; i <= nVertices; ++i) {
//            String g = reader.next();
//            vertices[i] = new Vertex(i, g);
//        }
//
//        // Doc lần lượt các cạnh
//        for (int i = 0; i < nEdges; ++i) {
//            int a = reader.nextInt();
//            int b = reader.nextInt();
//
//            // Đồ thị vô hướng nên cạnh a-b nghĩa là: a kề của b,
//            // b kề của a
//            vertices[a].addAdjecentVertex(vertices[b]);
//            vertices[b].addAdjecentVertex(vertices[a]);
//        }
//
//        for (Vertex a : vertices) {
//            a.adjecentVertices.sort((v1, v2) -> v1.id - v2.id);
//        }
//
//        return vertices;
//    }

	static class DisjointSet {
		int[] set;

		public DisjointSet(int n) {
			set = new int[n];
			for (int i = 0; i < n; ++i) {
				set[i] = i;
			}
		}

		int findRoot(int x) {
			if (x != set[x]) {
				set[x] = findRoot(set[x]);
			}

			return set[x];
		}

		void union(int x, int y) {
			int rootX = findRoot(x);
			int rootY = findRoot(y);
			set[rootX] = rootY;
		}

		boolean isSameTree(int x, int y) {
			int rootX = findRoot(x);
			int rootY = findRoot(y);

			return rootX == rootY;
		}
	}

	static class Vertex {
		public int id;
		public String gender;
		public HashMap<Integer, Vertex> adjecentVertices = new HashMap<>();
		// or
		public HashSet<Vertex> neighbors = new HashSet<>();
		// or
		public List<Vertex> adjVertices = new ArrayList<>();

		public Vertex(int id) {
			this.id = id;
		}

		public void addAdjecentVertex(Vertex vertex) {
			// Nếu vertex không có trong adjcent thì add
			if (adjVertices.contains(vertex) == false) {
				adjVertices.add(vertex);
			}
			// or
			adjecentVertices.put(vertex.id, vertex);
			// or
			neighbors.add(vertex);
		}

		public int getDegree() {
			return adjecentVertices.size();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Vertex) {
				return ((Vertex) obj).id == id;
			}
			return false;
		}

		@Override
		public int hashCode() {
			return id;
		}

		@Override
		public String toString() {
			return id + "";
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
