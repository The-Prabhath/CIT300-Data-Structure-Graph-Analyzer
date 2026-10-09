import java.util.Arrays;

/** Array-based LIFO stack. (Member 2) */
public class ArrayStack {
    private int[] data = new int[8];
    private int top = -1;

    public boolean isEmpty() { return top == -1; }
    public int size() { return top + 1; }

    public void push(int value) {
        if (top == data.length - 1) data = Arrays.copyOf(data, data.length * 2);
        data[++top] = value;
    }

    public int pop() {
        if (isEmpty()) throw new IllegalStateException("Stack Underflow: cannot pop from an empty stack.");
        return data[top--];
    }

    public int peek() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty: nothing to peek.");
        return data[top];
    }

    public void display() {
        if (isEmpty()) { System.out.println("Stack is empty."); return; }
        System.out.println("Stack (top -> bottom):");
        for (int i = top; i >= 0; i--) System.out.println("  | " + data[i] + (i == top ? "   <- TOP" : ""));
        System.out.println("  +---");
    }
}
