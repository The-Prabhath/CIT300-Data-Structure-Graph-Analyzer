import java.util.Arrays;

/** Circular-array FIFO queue (O(1) enqueue / dequeue). (Member 2) */
public class CircularQueue {
    private int[] data = new int[8];
    private int front = 0, size = 0;

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    public void enqueue(int value) {
        if (size == data.length) grow();
        data[(front + size) % data.length] = value;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) throw new IllegalStateException("Queue Underflow: cannot dequeue from an empty queue.");
        int v = data[front];
        front = (front + 1) % data.length;
        size--;
        return v;
    }

    public int peek() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty: no front element.");
        return data[front];
    }

    private void grow() {
        int[] bigger = new int[data.length * 2];
        for (int i = 0; i < size; i++) bigger[i] = data[(front + i) % data.length];
        data = bigger;
        front = 0;
    }

    public void display() {
        if (isEmpty()) { System.out.println("Queue is empty."); return; }
        StringBuilder sb = new StringBuilder("Queue (front -> rear): ");
        for (int i = 0; i < size; i++) sb.append(data[(front + i) % data.length]).append(i < size - 1 ? " <- " : "");
        System.out.println(sb);
    }
}
