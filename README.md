# Data Structure & Graph Performance Analyzer

**Module:** CIT300 – Data Structures and Algorithms
**Assessment:** Graded Practical Assignment 2 (10% of final grade)
**Institution:** Sri Lanka Technology Campus (SLTC)
**Language / Type:** Java, console-based application

---

## 1. Project Description

This project is a single Java console application that lets a user work with the core data structures from
the course (**Array, Stack, Queue, Linked List, Graph**) and the main algorithms (**Linear Search, Binary
Search, BFS, DFS**).

It also **measures and compares** these approaches by counting the number of steps and the execution time. For
example, it shows that finding the last item among 100,000 takes 100,000 steps with Linear Search but only
about 17 steps with Binary Search. This demonstrates **algorithmic complexity (Big-O)** in practice.

### What the project demonstrates
- Correct implementation of data structures
- Appropriate use of algorithms (searching and graph traversal)
- Graph representation and operations
- Performance / operation comparison
- Input validation and handling of empty structures
- Object-oriented design (one class per component)
- GitHub-based collaboration and individual contribution

---

## 2. Team Members



| | |
|---|---|
| **Student Name:** | Vinod|
| **Student ID:** | `23DA2-0464` |
| **Assigned Responsibility:** | Array and Searching implementation |
| **Individual Contribution:** | - Implemented `DynamicArray` (insert, append, delete, display, sort, random fill)<br>- Implemented `SearchAlgorithms` (Linear Search and Binary Search with steps and time)<br>- Implemented the Array and Searching menus (sorted-array check before binary search)<br>- Tested and validated inputs for these modules |

| | |
|---|---|
| **Student Name:** | Haritha|
| **Student ID:** | `23DA2-0421` |
| **Assigned Responsibility:** | Stack and Queue implementation |
| **Individual Contribution:** | - Implemented `ArrayStack` (push, pop, peek, display, underflow handling)<br>- Implemented `CircularQueue` (enqueue, dequeue, peek, display, underflow handling)<br>- Implemented the Stack and Queue menus<br>- Tested empty-structure and invalid-input cases |

| | |
|---|---|
| **Student Name:** | Hasindu|
| **Student ID:** | `23DA2-0150` |
| **Assigned Responsibility:** | Linked List implementation and Performance Comparison |
| **Individual Contribution:** | - Implemented `SinglyLinkedList` (insert at head/tail/position, delete, search, reverse, display)<br>- Implemented `PerformanceAnalyzer` (search, traversal and array-vs-list comparison table)<br>- Implemented `ResultLog` ("Display All Results")<br>- Wrote the complexity explanations shown by the program |

| | |
|---|---|
| **Student Name:** | Prabhath|
| **Student ID:** | `23DA2-0414` |
| **Assigned Responsibility:** | Graph implementation and traversal; Main menu, integration and testing |
| **Individual Contribution:** | - Implemented `Graph` (adjacency list, add vertex/edge, display)<br>- Implemented BFS, DFS and shortest-path search<br>- Implemented `Main` (main menu and all submenus) and `InputHelper` (input validation)<br>- Integrated all modules and performed end-to-end testing |

---

## 3. Technologies Used

- Java 11 or newer (tested on Java 21)
- Plain console input/output (`Scanner`)
- Git and GitHub for version control and collaboration

---

## 4. Main System Features

| Component | Operations | Complexity |
|---|---|---|
| **Array** | insert, append, delete, search, display, random fill | insert/delete O(n), access O(1) |
| **Stack** | push, pop, peek, display, underflow handling | all O(1) |
| **Queue** (circular) | enqueue, dequeue, peek, display, underflow handling | all O(1) |
| **Linked List** | insert head/tail/position, delete, search, reverse, display | head insert O(1), search O(n) |
| **Searching** | linear, binary (with auto-sort prompt), side-by-side comparison | O(n) vs O(log n) |
| **Graph** | add vertex/edge, display, BFS, DFS, path search, sample graph | traversals O(V + E) |
| **Performance** | steps and time table for search, traversal, array vs list | – |
| **Results log** | records every search, traversal and performance result | – |

All input is validated: non-numeric values, out-of-range numbers, duplicate vertices, missing vertices,
self-loops, and operations on empty structures all give friendly messages instead of crashing.

---

## 5. How the Program Works

### Structure

```
                         Main.java
              (main menu, loops until option 9)
                             |
   +--------+--------+-------+-------+--------+-------------+
   |        |        |       |       |        |             |
 Array    Stack    Queue   Linked  Searching Graph    Performance
 menu     menu     menu    List    menu      menu     Comparison
   |        |        |     menu       |        |             |
DynamicArray ArrayStack CircularQueue SinglyLinkedList  SearchAlgorithms  Graph  PerformanceAnalyzer
                                                                                        |
                              InputHelper (all input)            ResultLog (all results)
```

### Key ideas
1. **`Main` creates one object of each data structure at startup.** The data stays in memory while the user
   moves between menus.
2. **All user input goes through `InputHelper`**, which keeps asking until the input is valid.
3. **Searches, traversals and performance results are saved in `ResultLog`.** Menu option 8 prints them all.
4. **"Steps" is the main measure.** It counts comparisons or visits. Time (in microseconds) changes from run
   to run, but step counts do not, so steps are the reliable way to compare algorithms.
5. **Searching works on the array from the Array menu.** Fill the array first, then use the Searching menu.

### Main menu
```
=============================================
 DATA STRUCTURE & GRAPH ANALYZER
=============================================
1. Array Operations
2. Stack Operations
3. Queue Operations
4. Linked List Operations
5. Searching Operations
6. Graph Operations
7. Performance Comparison
8. Display All Results
9. Exit
```

---

## 6. Project Structure

```
DSGraphAnalyzer/
├── README.md
├── .gitignore
├── docs/
│   ├── GIT_WORKFLOW.md          Git / GitHub steps for the team
│   └── VIDEO_GUIDE.md           What to say and show in the video
└── src/
    ├── Main.java                Prabhath   – main menu, submenus, integration
    ├── InputHelper.java         Prabhath   – validated console input
    ├── Graph.java               Prabhath   – graph, BFS, DFS, path search
    ├── DynamicArray.java        Vinod      – array component
    ├── SearchAlgorithms.java    Vinod      – linear and binary search
    ├── ArrayStack.java          Haritha    – stack
    ├── CircularQueue.java       Haritha    – queue
    ├── SinglyLinkedList.java    Hasindu    – linked list
    ├── PerformanceAnalyzer.java Hasindu    – performance comparison
    └── ResultLog.java           Hasindu    – "Display All Results"
```

---

## 7. Concepts in Simple Words

**Big-O (complexity):** how the number of steps grows as the data grows.
O(1) is constant, O(log n) is very fast, O(n) grows in proportion to the data, and O(n²) gets slow quickly.

| Concept | Idea | Cost |
|---|---|---|
| **Array** | Items in numbered slots. Inserting or deleting in the middle shifts everything after it. | insert/delete O(n) |
| **Stack** | Last In, First Out (a pile of plates). Only the top is touched. | O(1) |
| **Queue** | First In, First Out (a ticket line). Our version is circular: it uses `% length` to wrap around and reuse space. | O(1) |
| **Linked List** | Nodes that each hold a value and a pointer to the next node. No shifting needed. | head insert O(1), search O(n) |
| **Linear Search** | Check items one by one. | O(n) |
| **Binary Search** | Look at the middle item and discard half each time. **The data must be sorted.** | O(log n) |
| **Graph** | Vertices (nodes) joined by edges. We use an *adjacency list*: each vertex stores a list of its neighbours. | space O(V + E) |
| **BFS** | Visits the graph level by level using a **queue**. Finds the shortest path (fewest edges). | O(V + E) |
| **DFS** | Goes as deep as possible first using a **stack**, then backs up. | O(V + E) |

---

## 8. What Each Member Must Understand and Explain

### Vinod – Array and Searching
- Why array insert and delete are O(n): later elements have to shift.
- How binary search works, and why the array must be sorted.
- What `mid = lo + (hi - lo) / 2` does.
- Why linear search needs up to n steps and binary search about log₂ n.

### Haritha – Stack and Queue
- The difference between LIFO (stack) and FIFO (queue).
- How `top` works in the stack (`data[++top] = value`, `data[top--]`).
- Why the queue is circular, and how `(front + size) % data.length` works.
- How underflow (pop/dequeue on empty) is detected and handled.

### Hasindu – Linked List and Performance
- How a node and `head` work, and how insert, delete and reverse change the pointers.
- Why insert at head is O(1) but search is O(n).
- How the performance table is produced, and why results differ:
  - Linear vs Binary Search: O(n) vs O(log n).
  - BFS vs DFS: both O(V + E), so step counts are nearly equal; only the visiting order differs.
  - Array vs Linked List front insertion: O(n²) total vs O(n) total.
  - Why time varies between runs but steps do not.

### Prabhath – Graph, Main and Integration
- Adjacency list vs adjacency matrix, and why the adjacency list suits sparse graphs.
- How BFS (queue) and DFS (stack) work, and why a `visited` set is needed (to avoid infinite loops).
- Why BFS finds the shortest path.
- How `Main` connects all modules, and how `InputHelper` validates input.

---

## 9. How to Run

You need a **JDK** (11 or newer). Check with `java -version` and `javac -version`.

**Windows**
```
cd DSGraphAnalyzer
mkdir out
javac -d out src\*.java
java -cp out Main
```

**Linux / macOS**
```
cd DSGraphAnalyzer
mkdir -p out
javac -d out src/*.java
java -cp out Main
```

You can also open the folder in IntelliJ IDEA, Eclipse or VS Code and run `Main.java`.

---

## 10. Quick Demo Path

1. **Searching:** Array Operations → option 6 (fill 20000 random numbers) → return → Searching → option 3
   (compare). Say `1` to sort when asked. Linear takes thousands of steps, binary takes about 15.
2. **Stack and Queue:** push 1, 2, 3 then pop (you get 3). Enqueue 1, 2, 3 then dequeue (you get 1). Then try
   pop and dequeue on an empty structure.
3. **Linked List:** insert at head, tail and position, display, search, delete, reverse.
4. **Graph:** Graph Operations → option 7 (sample) → 3 (display) → 4 (BFS from `A`) → 5 (DFS from `A`) →
   6 (path from `A` to `G`).
5. **Performance Comparison:** main menu → 7 → enter `100000`.
6. **Display All Results:** main menu → 8.

### Expected sample results
| Test | Expected output |
|---|---|
| BFS from `A` (sample graph) | `A -> B -> C -> D -> E -> F -> G` |
| DFS from `A` (sample graph) | `A -> B -> D -> E -> F -> C -> G` |
| Path `A` to `G` | `A -> C -> F -> G` (3 edges) |
| Performance, n = 100000: linear worst case | 100000 steps |
| Performance, n = 100000: binary worst case | about 17 steps |
| Performance, n = 100000: BFS vs DFS | equal step counts |
| Performance, n = 100000: array front-insert vs list | about 200 million steps vs 20000 |

---

## 11. GitHub Workflow Summary

| Member | Branch | Files |
|---|---|---|
| Vinod | `feature/array-searching` | DynamicArray.java, SearchAlgorithms.java |
| Haritha | `feature/stack-queue` | ArrayStack.java, CircularQueue.java |
| Hasindu | `feature/linkedlist-performance` | SinglyLinkedList.java, PerformanceAnalyzer.java, ResultLog.java |
| Prabhath | `feature/graph-main` | Graph.java, Main.java, InputHelper.java |

1. Clone the repo and create **your own branch**.
2. Commit in **small steps with meaningful messages** (at least 5 each).
3. Push your branch and open a **Pull Request** to `main`. Use `Closes #<issue number>`.
4. A teammate **reviews and merges** it.
5. After the others are merged, Prabhath pulls `main`, tests everything together and merges last.

Full commands are in `docs/GIT_WORKFLOW.md`.

---

## 12. Demonstration Video Rules (summary)

- **One merged video, under 15 minutes.**
- **Every member's face must be clearly visible** during their own section.
- Each member: introduce yourself (name, ID, responsibility) → explain your contribution → show your code →
  run and demonstrate → explain the data structure, algorithm and complexity → show how it fits into the
  complete system.

See `docs/VIDEO_GUIDE.md` for the detailed script.

---

## 13. Final Submission Checklist

- [ ] Project compiles and runs from a fresh clone
- [ ] All required components work (Array, Stack, Queue, Linked List, Searching, Graph, Performance)
- [ ] Real surnames, IDs, responsibilities and contributions are in this README
- [ ] Every member has commits, a branch and a pull request on GitHub
- [ ] One merged video prepared, under 15 minutes, faces visible
- [ ] If using Google Drive: Editor access given to `asanka.r@sltc.ac.lk` and `kaushika.w@sltc.ac.lk`
- [ ] Submitted through the LMS **before the deadline** (no late emails are accepted)
