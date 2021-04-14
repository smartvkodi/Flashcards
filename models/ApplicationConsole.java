package flashcards.models;

import java.io.OutputStream;
import java.io.PrintStream;

public class ApplicationConsole extends PrintStream {

    public ApplicationConsole(OutputStream out) {
        super(out);
    }

    @Override
    public void print(String line) {
        super.print(line);
        LoggerApp.add(line);
    }

    @Override
    public void println(String line) {
        System.out.println("Do not use \"println()\"");
    }

}
