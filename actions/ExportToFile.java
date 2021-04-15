package flashcards.actions;

import flashcards.models.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

class ExportToFile implements Action {

    private final String fileName;

    ApplicationConsole console = new ApplicationConsole(System.out);

    public ExportToFile() {
        console.print(AppMessages.FILE_NAME.getMessage());
        ApplicationScanner scanner = new ApplicationScanner(System.in);
        this.fileName = "./" + scanner.nextLine();
    }

    public ExportToFile(String fileName) {
        this.fileName = "./" + fileName;
    }

    @Override
    public void execute() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName));

            List<Card> cards = new Card().getCards();

            cards.forEach(card -> writer.println(String.format("%s;%s;%s",
                    card.getTerm(), card.getDefinition(), card.getMistakes())));
            writer.close();

            console.printf(AppMessages.SAVED_CARDS_NUMBER.getMessage(), cards.size());
        } catch (IOException e) {
            console.print(AppMessages.IO_EXCEPTION.getMessage());
        }
    }
}