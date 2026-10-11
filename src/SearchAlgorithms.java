/** Linear and binary search that record steps and execution time. (Member 1) */
public final class SearchAlgorithms {
    private SearchAlgorithms() { }

    /** Holds the outcome of one search. */
    public static class SearchResult {
        public final String algorithm;
        public final int index;     // -1 (or position) ; -1 means not found
        public final long steps;    // number of comparisons / nodes visited
        public final long nanos;

        public SearchResult(String algorithm, int index, long steps, long nanos) {
            this.algorithm = algorithm; this.index = index; this.steps = steps; this.nanos = nanos;
        }

        @Override
        public String toString() {
            String found = index >= 0 ? "FOUND at index/position " + index : "NOT FOUND";
            return String.format("%s -> %s | steps: %d | time: %.2f us", algorithm, found, steps, nanos / 1000.0);
        }
    }

    /** O(n): checks elements one by one. */
    public static SearchResult linearSearch(int[] a, int n, int target) {
        long start = System.nanoTime();
        long steps = 0;
        int idx = -1;
        for (int i = 0; i < n; i++) {
            steps++;
            if (a[i] == target) { idx = i; break; }
        }
        return new SearchResult("Linear Search", idx, steps, System.nanoTime() - start);
    }

    /** O(log n): requires a SORTED array; halves the range each step. */
    public static SearchResult binarySearch(int[] a, int n, int target) {
        long start = System.nanoTime();
        long steps = 0;
        int lo = 0, hi = n - 1, idx = -1;
        while (lo <= hi) {
            steps++;
            int mid = lo + (hi - lo) / 2;
            if (a[mid] == target) { idx = mid; break; }
            if (a[mid] < target) lo = mid + 1; else hi = mid - 1;
        }
        return new SearchResult("Binary Search", idx, steps, System.nanoTime() - start);
    }
}
