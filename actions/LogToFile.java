package flashcards.actions;

import flashcards.models.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class LogToFile implements Action {

    @Override
    public void execute() {
        ApplicationConsole console = new ApplicationConsole(System.out);
        console.print(AppMessages.FILE_NAME.getMessage());

        ApplicationScanner scanner = new ApplicationScanner(System.in);

        String fileName = "./" + scanner.nextLine();
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName));
            LoggerApp.lines.forEach(writer::print);
            writer.close();

            console.print(AppMessages.LOG_SAVED.getMessage());

        } catch (IOException e) {
            console.print(AppMessages.IO_EXCEPTION.getMessage());
        }
    }
}
