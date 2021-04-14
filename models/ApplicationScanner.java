package flashcards.models;

import java.io.InputStream;
import java.util.Scanner;

public class ApplicationScanner implements AutoCloseable {

    private final Scanner scanner;

    public ApplicationScanner(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public String nextLine() {
        String line = scanner.nextLine();
        LoggerApp.add(line + "\n");
        return line;
    }

    @Override
    public void close() {
        this.scanner.close();
    }

}

