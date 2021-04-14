package flashcards.actions;

import flashcards.models.Action;
import flashcards.models.AppMessages;
import flashcards.models.LoggerApp;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class LogToFile implements Action {

    private final FlashcardsApp app = FlashcardsApp.INSTANCE;

    @Override
    public void execute() {
        app.appConsole.print(AppMessages.FILE_NAME.getMessage());

        String fileName = "./" + app.appScanner.nextLine();
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName));
            LoggerApp.lines.forEach(writer::print);
            writer.close();

            app.appConsole.print(AppMessages.LOG_SAVED.getMessage());

        } catch (IOException e) {
            app.appConsole.print(AppMessages.IO_EXCEPTION.getMessage());
        }
    }
}
