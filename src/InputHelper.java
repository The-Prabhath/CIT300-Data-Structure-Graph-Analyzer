import java.util.Scanner;

/** Central place for validated console input (used by every module). */
public final class InputHelper {
    private static final Scanner SC = new Scanner(System.in);

    private InputHelper() { }

    private static String nextLine() {
        if (!SC.hasNextLine()) {
            System.out.println("\nInput closed. Exiting.");
            System.exit(0);
        }
        return SC.nextLine().trim();
    }

    /** Reads any whole number, re-prompting until valid. */
    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(nextLine());
            } catch (NumberFormatException e) {
                System.out.println("  [!] Invalid input. Please enter a whole number.");
            }
        }
    }

    /** Reads a whole number within [min, max]. */
    public static int readInt(String prompt, int min, int max) {
        while (true) {
            int v = readInt(prompt);
            if (v >= min && v <= max) return v;
            System.out.println("  [!] Value must be between " + min + " and " + max + ".");
        }
    }

    /** Reads a non-empty single word (e.g. a vertex name). */
    public static String readWord(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = nextLine();
            if (!s.isEmpty() && !s.contains(" ")) return s;
            System.out.println("  [!] Please enter a non-empty name without spaces.");
        }
    }
}
