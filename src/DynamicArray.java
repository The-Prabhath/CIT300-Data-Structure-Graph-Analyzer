import java.util.Arrays;
import java.util.Random;

/** Resizable int array with insert, delete, search support and display.*/
public class DynamicArray {
    private int[] data;
    private int size;
    private int lastSteps; // element shifts done by the last insert/delete

    public DynamicArray(int initialCapacity) {
        data = new int[Math.max(initialCapacity, 4)];
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public int getLastSteps() { return lastSteps; }
    public int[] toArray() { return Arrays.copyOf(data, size); }

    /** Insert value at index (0..size). Shifts later elements right: O(n). */
    public void insert(int index, int value) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index must be between 0 and " + size + ".");
        if (size == data.length) data = Arrays.copyOf(data, data.length * 2);
        int steps = 0;
        for (int i = size; i > index; i--) { data[i] = data[i - 1]; steps++; }
        data[index] = value;
        size++;
        lastSteps = steps;
    }

    public void append(int value) { insert(size, value); }

    /** Delete element at index (0..size-1). Shifts later elements left: O(n). */
    public int deleteAt(int index) {
        if (isEmpty()) throw new IllegalStateException("Array is empty. Nothing to delete.");
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index must be between 0 and " + (size - 1) + ".");
        int removed = data[index];
        int steps = 0;
        for (int i = index; i < size - 1; i++) { data[i] = data[i + 1]; steps++; }
        size--;
        lastSteps = steps;
        return removed;
    }

    public void sort() { Arrays.sort(data, 0, size); }

    public boolean isSorted() {
        for (int i = 1; i < size; i++) if (data[i - 1] > data[i]) return false;
        return true;
    }

    /** Appends 'count' random values in [0, bound). */
    public void fillRandom(int count, int bound) {
        Random r = new Random();
        for (int i = 0; i < count; i++) append(r.nextInt(bound));
    }

    public void display() {
        if (isEmpty()) { System.out.println("Array is empty."); return; }
        StringBuilder sb = new StringBuilder("Array (size " + size + "): [");
        int limit = Math.min(size, 50);
        for (int i = 0; i < limit; i++) sb.append(data[i]).append(i < limit - 1 ? ", " : "");
        if (size > limit) sb.append(", ... (").append(size - limit).append(" more)");
        System.out.println(sb.append("]"));
    }
}
