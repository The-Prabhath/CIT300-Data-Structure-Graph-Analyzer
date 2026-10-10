import java.util.List;

/** Console entry point: menus and integration of all modules. (Member 4) */
public class Main {
    // Shared data structures used across menus
    private static final DynamicArray array = new DynamicArray(16);
    private static final ArrayStack stack = new ArrayStack();
    private static final CircularQueue queue = new CircularQueue();
    private static final SinglyLinkedList list = new SinglyLinkedList();
    private static final Graph graph = new Graph();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=============================================");
            System.out.println(" DATA STRUCTURE & GRAPH ANALYZER");
            System.out.println("=============================================");
            System.out.println("1. Array Operations");
            System.out.println("2. Stack Operations");
            System.out.println("3. Queue Operations");
            System.out.println("4. Linked List Operations");
            System.out.println("5. Searching Operations");
            System.out.println("6. Graph Operations");
            System.out.println("7. Performance Comparison");
            System.out.println("8. Display All Results");
            System.out.println("9. Exit");
            switch (InputHelper.readInt("Enter your choice: ", 1, 9)) {
                case 1: arrayMenu(); break;
                case 2: stackMenu(); break;
                case 3: queueMenu(); break;
                case 4: listMenu(); break;
                case 5: searchMenu(); break;
                case 6: graphMenu(); break;
                case 7: PerformanceAnalyzer.run(); break;
                case 8: ResultLog.displayAll(); break;
                default: System.out.println("Goodbye!"); return;
            }
        }
    }

    // ---------------------------------------------------------------- Array
    private static void arrayMenu() {
        while (true) {
            System.out.println("\n--------------- ARRAY OPERATIONS ---------------");
            System.out.println("1. Insert at Index\n2. Append\n3. Delete at Index\n4. Search (Linear)\n5. Display\n6. Fill with Random Numbers\n7. Return to Main Menu");
            int c = InputHelper.readInt("Enter your choice: ", 1, 7);
            try {
                switch (c) {
                    case 1: {
                        int idx = InputHelper.readInt("Index (0 - " + array.size() + "): ");
                        array.insert(idx, InputHelper.readInt("Value: "));
                        System.out.println("Inserted. Elements shifted: " + array.getLastSteps());
                        break;
                    }
                    case 2: array.append(InputHelper.readInt("Value: ")); System.out.println("Appended."); break;
                    case 3: {
                        if (array.isEmpty()) { System.out.println("Array is empty. Nothing to delete."); break; }
                        int removed = array.deleteAt(InputHelper.readInt("Index to delete: "));
                        System.out.println("Deleted " + removed + ". Elements shifted: " + array.getLastSteps());
                        break;
                    }
                    case 4: {
                        if (array.isEmpty()) { System.out.println("Array is empty. Insert some values first."); break; }
                        runLinear(InputHelper.readInt("Value to search: "));
                        break;
                    }
                    case 5: array.display(); break;
                    case 6: array.fillRandom(InputHelper.readInt("How many numbers (1-100000): ", 1, 100000), 1000);
                            System.out.println("Added random numbers (0-999)."); break;
                    default: return;
                }
            } catch (IndexOutOfBoundsException | IllegalStateException e) {
                System.out.println("  [!] " + e.getMessage());
            }
        }
    }

    // ---------------------------------------------------------------- Stack
    private static void stackMenu() {
        while (true) {
            System.out.println("\n--------------- STACK OPERATIONS ---------------");
            System.out.println("1. Push\n2. Pop\n3. Peek\n4. Display\n5. Return to Main Menu");
            int c = InputHelper.readInt("Enter your choice: ", 1, 5);
            try {
                switch (c) {
                    case 1: stack.push(InputHelper.readInt("Value to push: ")); System.out.println("Pushed."); break;
                    case 2: System.out.println("Popped: " + stack.pop()); break;
                    case 3: System.out.println("Top element: " + stack.peek()); break;
                    case 4: stack.display(); break;
                    default: return;
                }
            } catch (IllegalStateException e) {
                System.out.println("  [!] " + e.getMessage());
            }
        }
    }

    // ---------------------------------------------------------------- Queue
    private static void queueMenu() {
        while (true) {
            System.out.println("\n--------------- QUEUE OPERATIONS ---------------");
            System.out.println("1. Enqueue\n2. Dequeue\n3. Peek (Front)\n4. Display\n5. Return to Main Menu");
            int c = InputHelper.readInt("Enter your choice: ", 1, 5);
            try {
                switch (c) {
                    case 1: queue.enqueue(InputHelper.readInt("Value to enqueue: ")); System.out.println("Enqueued."); break;
                    case 2: System.out.println("Dequeued: " + queue.dequeue()); break;
                    case 3: System.out.println("Front element: " + queue.peek()); break;
                    case 4: queue.display(); break;
                    default: return;
                }
            } catch (IllegalStateException e) {
                System.out.println("  [!] " + e.getMessage());
            }
        }
    }

    // ---------------------------------------------------------- Linked list
    private static void listMenu() {
        while (true) {
            System.out.println("\n----------- LINKED LIST OPERATIONS -------------");
            System.out.println("1. Insert at Head\n2. Insert at Tail\n3. Insert at Position\n4. Delete by Value\n5. Search\n6. Reverse List\n7. Display\n8. Return to Main Menu");
            int c = InputHelper.readInt("Enter your choice: ", 1, 8);
            try {
                switch (c) {
                    case 1: list.insertAtHead(InputHelper.readInt("Value: ")); System.out.println("Inserted at head."); break;
                    case 2: list.insertAtTail(InputHelper.readInt("Value: ")); System.out.println("Inserted at tail."); break;
                    case 3: {
                        int pos = InputHelper.readInt("Position (1 - " + (list.size() + 1) + "): ");
                        list.insertAtPosition(pos, InputHelper.readInt("Value: "));
                        System.out.println("Inserted.");
                        break;
                    }
                    case 4: System.out.println(list.deleteByValue(InputHelper.readInt("Value to delete: ")) ? "Deleted." : "Value not found."); break;
                    case 5: {
                        if (list.isEmpty()) { System.out.println("List is empty."); break; }
                        SearchAlgorithms.SearchResult r = list.search(InputHelper.readInt("Value to search: "));
                        System.out.println(r);
                        ResultLog.add("[Search] " + r);
                        break;
                    }
                    case 6: list.reverse(); System.out.println("List reversed."); break;
                    case 7: list.display(); break;
                    default: return;
                }
            } catch (IndexOutOfBoundsException | IllegalStateException e) {
                System.out.println("  [!] " + e.getMessage());
            }
        }
    }

    // ------------------------------------------------------------ Searching
    private static void runLinear(int target) {
        SearchAlgorithms.SearchResult r = SearchAlgorithms.linearSearch(array.toArray(), array.size(), target);
        System.out.println(r);
        ResultLog.add("[Search] " + r);
    }

    /** Binary search needs sorted data; offers to sort first. Returns null if user declines. */
    private static SearchAlgorithms.SearchResult runBinary(int target) {
        if (!array.isSorted()) {
            System.out.println("  [!] Binary search requires a sorted array, but the array is not sorted.");
            if (InputHelper.readInt("Sort the array now? (1 = Yes, 2 = No): ", 1, 2) == 2) return null;
            array.sort();
            System.out.println("Array sorted.");
        }
        SearchAlgorithms.SearchResult r = SearchAlgorithms.binarySearch(array.toArray(), array.size(), target);
        ResultLog.add("[Search] " + r);
        return r;
    }

    private static void searchMenu() {
        while (true) {
            System.out.println("\n-------------- SEARCHING OPERATIONS ------------");
            System.out.println("Searching the array (currently " + array.size() + " elements).");
            System.out.println("1. Linear Search\n2. Binary Search\n3. Compare Linear vs Binary\n4. Display Array\n5. Return to Main Menu");
            int c = InputHelper.readInt("Enter your choice: ", 1, 5);
            if (c == 5) return;
            if (c == 4) { array.display(); continue; }
            if (array.isEmpty()) {
                System.out.println("  [!] Array is empty. Use Array Operations to add data (e.g. 'Fill with Random Numbers').");
                continue;
            }
            int target = InputHelper.readInt("Value to search: ");
            if (c == 1) runLinear(target);
            else if (c == 2) {
                SearchAlgorithms.SearchResult r = runBinary(target);
                if (r != null) System.out.println(r);
            } else {
                runLinear(target);
                SearchAlgorithms.SearchResult r = runBinary(target);
                if (r != null) {
                    System.out.println(r);
                    System.out.println("Linear search is O(n), binary search is O(log n): compare the 'steps' above.");
                }
            }
        }
    }

    // ---------------------------------------------------------------- Graph
    private static void graphMenu() {
        while (true) {
            System.out.println("\n--------------- GRAPH OPERATIONS ---------------");
            System.out.println("1. Add Vertex\n2. Add Edge\n3. Display Graph\n4. BFS Traversal\n5. DFS Traversal\n6. Search Path Between Vertices\n7. Load Sample Graph\n8. Return to Main Menu");
            int c = InputHelper.readInt("Enter your choice: ", 1, 8);
            switch (c) {
                case 1: {
                    String v = InputHelper.readWord("Vertex name: ");
                    System.out.println(graph.addVertex(v) ? "Vertex added." : "  [!] Vertex already exists.");
                    break;
                }
                case 2: {
                    if (graph.vertexCount() < 2) { System.out.println("  [!] Add at least two vertices first."); break; }
                    String err = graph.addEdge(InputHelper.readWord("From vertex: "), InputHelper.readWord("To vertex: "));
                    System.out.println(err == null ? "Edge added." : "  [!] " + err);
                    break;
                }
                case 3: graph.display(); break;
                case 4: case 5: traverse(c == 4); break;
                case 6: {
                    if (graph.isEmpty()) { System.out.println("  [!] Graph is empty."); break; }
                    String a = InputHelper.readWord("Start vertex: "), b = InputHelper.readWord("Target vertex: ");
                    if (!graph.hasVertex(a) || !graph.hasVertex(b)) { System.out.println("  [!] Both vertices must exist."); break; }
                    List<String> path = graph.findPath(a, b);
                    String msg = path == null ? "No path between " + a + " and " + b + "."
                            : "Shortest path (" + (path.size() - 1) + " edges): " + String.join(" -> ", path);
                    System.out.println(msg);
                    ResultLog.add("[Graph Search] " + msg);
                    break;
                }
                case 7: graph.loadSample(); System.out.println("Sample graph loaded (A-G)."); break;
                default: return;
            }
        }
    }

    private static void traverse(boolean isBfs) {
        if (graph.isEmpty()) { System.out.println("  [!] Graph is empty. Add vertices/edges or load the sample graph."); return; }
        String start = InputHelper.readWord("Start vertex: ");
        if (!graph.hasVertex(start)) { System.out.println("  [!] Vertex '" + start + "' does not exist."); return; }
        Graph.TraversalResult r = isBfs ? graph.bfs(start) : graph.dfs(start);
        String out = r.algorithm + " from " + start + ": " + String.join(" -> ", r.order)
                + " | steps: " + r.steps + " | time: " + String.format("%.2f", r.nanos / 1000.0) + " us";
        System.out.println(out);
        ResultLog.add("[Traversal] " + out);
    }
}
