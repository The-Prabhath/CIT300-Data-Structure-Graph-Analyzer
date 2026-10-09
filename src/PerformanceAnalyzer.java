import java.util.Random;

/** Compares steps and execution time of algorithms / data structures. (Member 3) */
public final class PerformanceAnalyzer {
    private PerformanceAnalyzer() { }

    private static void row(String op, String algo, long steps, long nanos) {
        String line = String.format("%-18s %-24s %12d %14.2f", op, algo, steps, nanos / 1000.0);
        System.out.println(line);
        ResultLog.add("[Performance] " + op + " | " + algo + " | steps=" + steps + " | " + String.format("%.2f", nanos / 1000.0) + " us");
    }

    public static void run() {
        int n = InputHelper.readInt("Enter data size n (10 - 1,000,000): ", 10, 1_000_000);

        // ---- Searching: sorted array of even numbers, worst-case target = last element ----
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i * 2;
        int worst = arr[n - 1];

        SearchAlgorithms.SearchResult lin = SearchAlgorithms.linearSearch(arr, n, worst);
        SearchAlgorithms.SearchResult bin = SearchAlgorithms.binarySearch(arr, n, worst);

        // Average over 100 random existing targets
        Random rnd = new Random(1);
        long linSteps = 0, binSteps = 0, linNanos = 0, binNanos = 0;
        for (int i = 0; i < 100; i++) {
            int target = arr[rnd.nextInt(n)];
            SearchAlgorithms.SearchResult a = SearchAlgorithms.linearSearch(arr, n, target);
            SearchAlgorithms.SearchResult b = SearchAlgorithms.binarySearch(arr, n, target);
            linSteps += a.steps; binSteps += b.steps; linNanos += a.nanos; binNanos += b.nanos;
        }

        // ---- Graph: random connected graph ----
        int v = Math.min(n, 5000);
        Graph g = Graph.random(v, v * 2, 7);
        Graph.TraversalResult bfs = g.bfs("V0");
        Graph.TraversalResult dfs = g.dfs("V0");

        // ---- Insert at front: array (shifts) vs linked list (pointer change) ----
        int k = Math.min(n, 20_000);
        DynamicArray da = new DynamicArray(16);
        long arrSteps = 0, t0 = System.nanoTime();
        for (int i = 0; i < k; i++) { da.insert(0, i); arrSteps += da.getLastSteps() + 1; }
        long arrNanos = System.nanoTime() - t0;

        SinglyLinkedList ll = new SinglyLinkedList();
        long llSteps = 0;
        t0 = System.nanoTime();
        for (int i = 0; i < k; i++) { ll.insertAtHead(i); llSteps++; }
        long llNanos = System.nanoTime() - t0;

        System.out.println("\n=============================================");
        System.out.println(" PERFORMANCE COMPARISON  (n = " + n + ")");
        System.out.println("=============================================");
        System.out.printf("%-18s %-24s %12s %14s%n", "Operation", "Algorithm", "Steps", "Time (us)");
        System.out.println("-------------------------------------------------------------------------");
        row("Search (worst)", "Linear Search", lin.steps, lin.nanos);
        row("Search (worst)", "Binary Search", bin.steps, bin.nanos);
        row("Search (avg x100)", "Linear Search", linSteps / 100, linNanos / 100);
        row("Search (avg x100)", "Binary Search", binSteps / 100, binNanos / 100);
        row("Traversal V=" + v, "BFS", bfs.steps, bfs.nanos);
        row("Traversal V=" + v, "DFS", dfs.steps, dfs.nanos);
        row("Insert front x" + k, "Array (shifting)", arrSteps, arrNanos);
        row("Insert front x" + k, "Linked List (head)", llSteps, llNanos);
        System.out.println("-------------------------------------------------------------------------");

        System.out.println("\nWhy do results differ?");
        System.out.println(" - Linear search is O(n): worst case checks all " + n + " items. Binary search is O(log n):");
        System.out.println("   it halves the range each step, so only about " + bin.steps + " steps (needs sorted data).");
        System.out.println(" - BFS and DFS are both O(V + E): each vertex and edge is handled once, so step counts are");
        System.out.println("   almost identical; they differ only in visiting ORDER (queue vs stack), not in cost.");
        System.out.println(" - Array insert at front is O(n) per insert (shifts every element) => O(n^2) in total,");
        System.out.println("   while linked-list insert at head is O(1) per insert => O(n) in total.");
        System.out.println(" - Times (us) are machine dependent and small runs can be noisy; STEPS are the reliable measure.");
    }
}
