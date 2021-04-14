package flashcards.actions;

import flashcards.models.Action;
import flashcards.models.AppMessages;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class ExportToFile implements Action {

    private FlashcardsApp app = FlashcardsApp.INSTANCE;
    private String fileName;

    public ExportToFile() {
        app.appConsole.print(AppMessages.FILE_NAME.getMessage());
        this.fileName = "./" + app.appScanner.nextLine();
    }

    public ExportToFile(String fileName) {
        this.fileName = "./" + fileName;
    }

    @Override
    public void execute() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName));

            app.cardsList.values()
                    .forEach(card -> writer.println(String.format("%s;%s;%s",
                            card.getTerm(),
                            card.getDefinition(),
                            card.getMistakes())));

            writer.close();

            app.appConsole.printf(AppMessages.SAVED_CARDS_NUMBER.getMessage(), app.cardsList.size());
        } catch (IOException e) {
            app.appConsole.print(AppMessages.IO_EXCEPTION.getMessage());
        }
    }
}