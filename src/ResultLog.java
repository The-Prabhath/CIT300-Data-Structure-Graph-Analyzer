import java.util.ArrayList;
import java.util.List;

/** Stores every search / traversal / performance result for "Display All Results". */
public final class ResultLog {
    private static final List<String> ENTRIES = new ArrayList<>();

    private ResultLog() { }

    public static void add(String entry) { ENTRIES.add(entry); }

    public static void displayAll() {
        System.out.println("\n=============================================");
        System.out.println(" ALL RESULTS (this session)");
        System.out.println("=============================================");
        if (ENTRIES.isEmpty()) {
            System.out.println("No results recorded yet. Run a search, traversal or comparison first.");
            return;
        }
        for (int i = 0; i < ENTRIES.size(); i++) {
            System.out.printf("%2d. %s%n", i + 1, ENTRIES.get(i));
        }
    }
}
