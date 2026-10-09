public class SinglyLinkedList {
    private static class Node {
        int value; Node next;
        Node(int value) { this.value = value; }
    }

    private Node head;
    private int size;

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    // O(1)
    public void insertAtHead(int value) {
        Node n = new Node(value);
        n.next = head;
        head = n;
        size++;
    }

    // O(n) because there is no tail pointer.
    public void insertAtTail(int value) {
        Node n = new Node(value);
        if (head == null) head = n;
        else {
            Node cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = n;
        }
        size++;
    }

    // Insert at 1-based position (1..size+1).
    public void insertAtPosition(int pos, int value) {
        if (pos < 1 || pos > size + 1)
            throw new IndexOutOfBoundsException("Position must be between 1 and " + (size + 1) + ".");
        if (pos == 1) { insertAtHead(value); return; }
        Node cur = head;
        for (int i = 1; i < pos - 1; i++) cur = cur.next;
        Node n = new Node(value);
        n.next = cur.next;
        cur.next = n;
        size++;
    }

    // Deletes first node holding value. Returns false when not found.
    public boolean deleteByValue(int value) {
        if (isEmpty()) throw new IllegalStateException("List is empty. Nothing to delete.");
        if (head.value == value) { head = head.next; size--; return true; }
        Node cur = head;
        while (cur.next != null && cur.next.value != value) cur = cur.next;
        if (cur.next == null) return false;
        cur.next = cur.next.next;
        size--;
        return true;
    }

    // Sequential search; reports 1-based position (or -1) plus steps and time.
    public SearchAlgorithms.SearchResult search(int value) {
        long start = System.nanoTime();
        long steps = 0;
        int pos = -1, i = 1;
        for (Node cur = head; cur != null; cur = cur.next, i++) {
            steps++;
            if (cur.value == value) { pos = i; break; }
        }
        return new SearchAlgorithms.SearchResult("Linked List Search", pos, steps, System.nanoTime() - start);
    }

    public void reverse() {
        Node prev = null, cur = head;
        while (cur != null) { Node next = cur.next; cur.next = prev; prev = cur; cur = next; }
        head = prev;
    }

    public void display() {
        if (isEmpty()) { System.out.println("Linked list is empty."); return; }
        StringBuilder sb = new StringBuilder("HEAD -> ");
        for (Node cur = head; cur != null; cur = cur.next) sb.append(cur.value).append(" -> ");
        System.out.println(sb.append("NULL").append("   (size ").append(size).append(")"));
    }
}
