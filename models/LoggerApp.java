package flashcards.models;

import java.util.ArrayList;
import java.util.List;

public enum LoggerApp {
    ;

    public static final List<String> lines = new ArrayList<>();

    public static void add(String line) {
        lines.add(line);
    }
}
