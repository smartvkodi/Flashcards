package flashcards.actions;

import flashcards.models.Action;
import flashcards.models.AppMessages;
import flashcards.models.Card;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class ImportFromFile implements Action {

    private FlashcardsApp app = FlashcardsApp.INSTANCE;
    private String fileName;

    public ImportFromFile() {
        app.appConsole.print(AppMessages.FILE_NAME.getMessage());
        this.fileName = "./" + app.appScanner.nextLine();
    }

    public ImportFromFile(String fileName) {
        this.fileName = "./" + fileName;
    }

    @Override
    public void execute() {

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(this.fileName));
            String line;
            int countLoadedCards = 0;

            while ((line = reader.readLine()) != null) {
                String[] card = line.split(";");
                String term = card[0];
                String definition = card[1];
                int mistakes = 0;
                try {
                    mistakes = Integer.parseInt(card[2]);
                } catch (Exception e) {
                    // do not worry the mistakes is already 0;
                }
                // remove existing cards with term or definition
                if (!term.isBlank()) {
                    app.cardsList.remove(term);
                }
                if(!definition.isBlank()) {
                    Card xCard = app.getCardByDefinition(definition);
                    if (xCard != null) {
                        app.cardsList.remove(xCard.getTerm());
                    }
                }
                app.cardsList.put(term, new Card(term, definition).setMistakes(mistakes));
                countLoadedCards++;
            }
            reader.close();
            app.appConsole.printf(AppMessages.LOADED_CARDS_NUMBER.getMessage(), countLoadedCards);
        } catch (FileNotFoundException e) {
            app.appConsole.print(AppMessages.FILE_NOT_FOUND.getMessage());
        } catch (IOException e) {
            app.appConsole.print(AppMessages.IO_EXCEPTION.getMessage());
        }
    }
}