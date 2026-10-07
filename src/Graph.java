import java.util.*;

/** Undirected, unweighted graph stored as an adjacency list. (Member 4) */
public class Graph {
    private final Map<String, List<String>> adj = new LinkedHashMap<>();
    private int edges;

    /** Result of a BFS / DFS run. */
    public static class TraversalResult {
        public final String algorithm;
        public final List<String> order;
        public final long steps;   // vertices visited + edges examined
        public final long nanos;

        TraversalResult(String algorithm, List<String> order, long steps, long nanos) {
            this.algorithm = algorithm; this.order = order; this.steps = steps; this.nanos = nanos;
        }
    }

    public boolean isEmpty() { return adj.isEmpty(); }
    public int vertexCount() { return adj.size(); }
    public boolean hasVertex(String v) { return adj.containsKey(v); }
    public String firstVertex() { return adj.keySet().iterator().next(); }

    public int edgeCount() { return edges; }

    public boolean addVertex(String v) {
        if (adj.containsKey(v)) return false;
        adj.put(v, new ArrayList<>());
        return true;
    }

    /** Returns null on success, otherwise an error message. */
    public String addEdge(String a, String b) {
        if (!hasVertex(a) || !hasVertex(b)) return "Both vertices must exist first.";
        if (a.equals(b)) return "Self-loops are not allowed.";
        if (adj.get(a).contains(b)) return "Edge " + a + " - " + b + " already exists.";
        adj.get(a).add(b);
        adj.get(b).add(a);
        edges++;
        return null;
    }

    public void display() {
        if (isEmpty()) { System.out.println("Graph is empty."); return; }
        System.out.println("Adjacency List (" + vertexCount() + " vertices, " + edgeCount() + " edges):");
        for (Map.Entry<String, List<String>> e : adj.entrySet())
            System.out.println("  " + e.getKey() + " -> " + (e.getValue().isEmpty() ? "(none)" : String.join(", ", e.getValue())));
    }

    /** Breadth-first traversal using a queue. O(V + E). */
    public TraversalResult bfs(String start) {
        long t0 = System.nanoTime(), steps = 0;
        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        visited.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            String v = queue.poll();
            steps++;                       // vertex processed
            order.add(v);
            for (String n : adj.get(v)) {
                steps++;                   // edge examined
                if (visited.add(n)) queue.add(n);
            }
        }
        return new TraversalResult("BFS", order, steps, System.nanoTime() - t0);
    }

    /** Depth-first traversal using an explicit stack (no recursion limit). O(V + E). */
    public TraversalResult dfs(String start) {
        long t0 = System.nanoTime(), steps = 0;
        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Deque<String> stack = new ArrayDeque<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            String v = stack.pop();
            if (!visited.add(v)) continue;
            steps++;                       // vertex processed
            order.add(v);
            List<String> nb = adj.get(v);
            for (int i = nb.size() - 1; i >= 0; i--) {   // reverse so first neighbour is visited first
                steps++;                   // edge examined
                if (!visited.contains(nb.get(i))) stack.push(nb.get(i));
            }
        }
        return new TraversalResult("DFS", order, steps, System.nanoTime() - t0);
    }

    /** Graph search: shortest path (fewest edges) from start to target via BFS, or null if unreachable. */
    public List<String> findPath(String start, String target) {
        Map<String, String> parent = new HashMap<>();
        Queue<String> queue = new ArrayDeque<>();
        parent.put(start, null);
        queue.add(start);
        while (!queue.isEmpty()) {
            String v = queue.poll();
            if (v.equals(target)) {
                LinkedList<String> path = new LinkedList<>();
                for (String c = target; c != null; c = parent.get(c)) path.addFirst(c);
                return path;
            }
            for (String n : adj.get(v))
                if (!parent.containsKey(n)) { parent.put(n, v); queue.add(n); }
        }
        return null;
    }

    /** Adds a small demo graph (A..G); existing vertices/edges are kept. */
    public void loadSample() {
        for (String v : new String[]{"A", "B", "C", "D", "E", "F", "G"}) addVertex(v);
        String[][] edges = {{"A","B"},{"A","C"},{"B","D"},{"B","E"},{"C","F"},{"E","F"},{"F","G"}};
        for (String[] e : edges) addEdge(e[0], e[1]);
    }

    /** Random connected graph with 'v' vertices and about 'e' edges (used for performance tests). */
    public static Graph random(int v, int e, long seed) {
        Graph g = new Graph();
        Random r = new Random(seed);
        for (int i = 0; i < v; i++) g.addVertex("V" + i);
        for (int i = 1; i < v; i++) g.addEdge("V" + i, "V" + r.nextInt(i));   // spanning tree => connected
        long max = (long) v * (v - 1) / 2;
        long target = Math.min(e, max);
        int guard = 0;
        while (g.edgeCount() < target && guard++ < target * 20) {
            g.addEdge("V" + r.nextInt(v), "V" + r.nextInt(v));
        }
        return g;
    }
}
