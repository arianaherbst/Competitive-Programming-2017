import java.io.*;
import java.util.*;
/**
 * Max Flow
 * "Delivery Bears"
 * http://codeforces.com/contest/653/problem/D
 * @author Ariana Herbst
 * 3/29/17
 */
/*
 * Learned:  Max Flow does not work with doubles.
 * To change a double to a long, multiply by a big number,
 * run max flow, then divide by the big number later.
 */

public class DeliveryBears {
	static int N, M, X;
	static List<E>[] graph;
	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		N = sc.nextInt();
		M = sc.nextInt();
		X = sc.nextInt();
		graph = new ArrayList[N];
		for (int n = 0; n < N; n++)	{
			graph[n] = new ArrayList<E>();
		}
		for (int m = 0; m < M; m++)	{
			graph[sc.nextInt()-1].add(new E(sc.nextInt()-1, sc.nextDouble()));
		}
		double max = findMax();
		double ans = binarySearch(max);
		System.out.println(ans);
	}
	public static boolean works(double weight)	{
		double target = weight * X;
		MaxFlowSolver mf = new Dinic();
		Node source = mf.addNode();
		Node sink = mf.addNode();
		Node[] nodes = new Node[N];
		for (int n = 0; n < N; n++)	{
			nodes[n] = mf.addNode();
		}
		mf.link(source, nodes[0], Long.MAX_VALUE);
		mf.link(nodes[N-1], sink, Long.MAX_VALUE);
		for (int n = 0; n < N; n++)	{
			for (E e : graph[n])	{
				mf.link(nodes[n], nodes[e.to], ((long)(e.weight / weight)));
			}
		}
		return mf.getMaxFlow(source, sink)*weight>= target;
		
		
	}
	public static double binarySearch(double max)	{
		double min = 0;
		double mid = -1;
		for (int i = 0; i < 60; i++)	{
			mid = (max + min )/ 2.0;
			if (works(mid))	{
				min = mid;
			} else {
				max = mid;
			}
		}
		return mid*X;
	}
	public static double findMax()	{
		MaxFlowSolver mf = new Dinic();
		Node source = mf.addNode();
		Node sink = mf.addNode();
		Node[] nodes = new Node[N];
		for (int n = 0; n < N; n++)	{
			nodes[n] = mf.addNode();
		}
		mf.link(source, nodes[0], Long.MAX_VALUE);
		mf.link(nodes[N-1], sink, Long.MAX_VALUE);
		for (int n = 0; n < N; n++)	{
			for (E e : graph[n])	{
				mf.link(nodes[n], nodes[e.to], ((long)(e.weight*1e12)));
			}
		}
		return mf.getMaxFlow(source, sink) / 1e12;
	}
	
	
	
	
	public static class E {
		int to;
		double weight;
		public E(int to, double weight)	{
			this.to= to;
			this.weight = weight;
		}
	}

	
	
	public static class Node {
	    // thou shall not create nodes except through addNode()
	    private Node() { }

	    List<Edge> edges = new ArrayList<Edge>();
	    int index;          // index in nodes array

	    int dist;
	    boolean active;
	    boolean blocked;
	}

	public static class Edge
	{
	    boolean forward; // true: edge is in original graph
	    // false: edge is a backward edge
	    Node from, to;   // nodes connected
	    long flow;        // current flow
	    final long capacity;
	    Edge dual;      // reference to this edge's dual
	    long cost;      // only used for MinCost.

	    // thou shall not create edges except through link()
	    protected Edge(Node s, Node d, long c, boolean f)
	    {
	        forward = f;
	        from = s;
	        to = d;
	        capacity = c;
	    }

	    // remaining capacity()
	    long remaining() { return capacity - flow; }

	    // increase flow and adjust dual
	    void addFlow(long amount) {
	        flow += amount;
	        dual.flow -= amount;
	    }
	}

	/* A Max Flow solver base class. */
	public static abstract class MaxFlowSolver {
	    /* List of nodes, indexed. */
	    List<Node> nodes = new ArrayList<Node>();

	    /* Create an edge between nodes n1 and n2 */
	    public void link(Node n1, Node n2, long capacity)
	    {
	    /*
	     * Only the EdmondsKarp solver takes cost into account
	     * during getMaxFlow().  Setting it to 1 for problems
	     * that do not involve cost means it uses a shortest path
	     * search when finding augmenting paths.  In practice,
	     * this does not seem to make a difference.
	     */
	        link(n1, n2, capacity, 1);
	    }

	    /* Create an edge between nodes n1 and n2 and assign cost */
	    public void link(Node n1, Node n2, long capacity, long cost)
	    {
	        Edge e12 = new Edge(n1, n2, capacity, true);
	        Edge e21 = new Edge(n2, n1, 0, false);
	        e12.dual = e21;
	        e21.dual = e12;
	        n1.edges.add(e12);
	        n2.edges.add(e21);
	        e12.cost = cost;
	        e21.cost = -cost;
	    }

	    /* Create an edge between nodes n1 and n2 */
	    void link(int n1, int n2, long capacity)
	    {
	        link(nodes.get(n1), nodes.get(n2), capacity);
	    }

	    /* Create a graph with n nodes. */
	    protected MaxFlowSolver(int n) {
	        for (int i = 0; i < n; i++)
	            addNode();
	    }

	    protected MaxFlowSolver() { this(0); }

	    public abstract long getMaxFlow(Node src, Node snk);

	    /* Add a new node. */
	    public Node addNode() {
	        Node n = new Node();
	        n.index = nodes.size();
	        nodes.add(n);
	        return n;
	    }

	    /* OPTIONAL: Returns the edges associated with the Min Cut.
	     * Must be run immediately after a getMaxFlow() call.  */
	    List<Edge> getMinCut(Node src) {
	        Queue<Node> bfs = new ArrayDeque<Node>();
	        Set<Node> visited = new HashSet<Node>();
	        bfs.offer(src);
	        visited.add(src);
	        while (!bfs.isEmpty()) {
	            Node u = bfs.poll();
	            for (Edge e : u.edges) {
	                if (e.remaining() > 0 && !visited.contains(e.to)) {
	                    visited.add(e.to);
	                    bfs.offer(e.to);
	                }
	            }
	        }
	        List<Edge> minCut = new ArrayList<Edge>();
	        for (Node s : visited) {
	            for (Edge e : s.edges)
	                if (e.forward && !visited.contains(e.to))
	                    minCut.add(e);
	        }
	        return minCut;
	    }
	}

	/**
	 * Dinic's algorithm, Shimon Even variant.
	 */
	public static class Dinic extends MaxFlowSolver
	{
	    long BlockingFlow(Node src, Node snk) {
	        int N = nodes.size();
	        for (Node u : nodes) {
	            u.dist = -1;
	            u.blocked = false;
	        }
	        Node [] Q = new Node[N];

	        /* Step 1.  BFS from source to compute levels */
	        src.dist = 0;
	        int head = 0, tail = 0;
	        Q[tail++] = src;
	        while (head < tail) {
	            Node x = Q[head++];
	            List<Edge> succ = x.edges;
	            for (Edge e : succ) {
	                if (e.to.dist == -1 && e.remaining() > 0) {
	                    e.to.dist = e.from.dist + 1;
	                    Q[tail++] = e.to;
	                }
	            }
	        }

	        if (snk.dist == -1)     // no flow if sink is not reachable
	            return 0;

	        long flow;
			/* Step 2. Push flow down until we have a blocking flow */
	        long totflow = 0;
	        do {
	            flow = dfs(src, snk, Long.MAX_VALUE);
	            totflow += flow;
	        } while (flow > 0);
	        return totflow;
	    }

	    /*
	     * Run DFS on the BFS level graph.
	     */
	    long dfs(Node v, Node snk, long mincap) {
	        // reached sink
	        if (v == snk)
	            return mincap;

	        for (Edge e : v.edges) {
	            // standard DFS, but consider an edge only if
	            if (!e.to.blocked    // the path to the sink is not already blocked
	                    && e.from.dist + 1 == e.to.dist // it's in the BFS level graph
	                    && e.remaining() > 0) {  // the edge has remaining capacity
	                long flow = dfs(e.to, snk, Math.min(mincap, e.remaining()));
	                if (flow > 0) {
	                    e.addFlow(flow);
	                    return flow;
	                }
	            }
	        }
	        // if we couldn't add any flow then there is no point in ever going
	        // past this node again.  Mark it blocked.
	        v.blocked = true;
	        return 0;
	    }

	    @Override
	    public long getMaxFlow(Node src, Node snk) {
	        long flow, totflow = 0;
	        while ((flow = BlockingFlow(src, snk)) != 0)
	            totflow += flow;
	        return totflow;
	    }

	    public Dinic () { this(0); }
	    public Dinic (int n) { super(n); }
	}

	/**
	 * Implements Edmonds/Karp min-cost max-flow.
	 *
	 * This algorithm uses costs to find an mincost augmenting path.
	 *
	 * See Theoretical Improvements in Algorithmic Efficiency for
	 * Network Flow Problems by Edmonds and Karp,
	 * Journal of the Association for Computing Machinery,
	 * Vol. 19, No. 2, Apri; 1972. pp. 248-264.
	 */
	
	
	public static class FastScanner {
		BufferedReader br;
		StringTokenizer st;

		public FastScanner(Reader in) {
			br = new BufferedReader(in);
		}

		public FastScanner() {
			this(new InputStreamReader(System.in));
		}

		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String readNextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}

		int[] readIntArray(int n) {
			int[] a = new int[n];
			for (int idx = 0; idx < n; idx++) {
				a[idx] = nextInt();
			}
			return a;
		}

		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int idx = 0; idx < n; idx++) {
				a[idx] = nextLong();
			}
			return a;
		}
	}
}
