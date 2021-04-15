package flashcards.actions;

import flashcards.models.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class ImportFromFile implements Action {

    private final String importFile;

    public ImportFromFile() {
        new ApplicationConsole(System.out).print(AppMessages.FILE_NAME.getMessage());
        importFile = "./" + new ApplicationScanner(System.in).nextLine();
    }

    public ImportFromFile(String fileName) {
        this.importFile = "./" + fileName;
    }

    @Override
    public void execute() {
        ApplicationConsole console = new ApplicationConsole(System.out);

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(this.importFile));

            int countLoadedCards = 0;

            String line;
            while ((line = reader.readLine()) != null) {
                String[] cardArray = line.split(";");
                String term = cardArray[0];
                String definition = cardArray[1];

                int mistakes = 0;
                try {
                    mistakes = Integer.parseInt(cardArray[2]);
                } catch (Exception e) {
                    // do not worry the mistakes is already 0;
                }
                Card card = new Card();
                card.removeTerm(term);
                card.removeDefinition(definition);

                new Card().getCards().add(new Card(term, definition).setMistakes(mistakes));
                countLoadedCards++;
            }
            reader.close();
            console.printf(AppMessages.LOADED_CARDS_NUMBER.getMessage(), countLoadedCards);
        } catch (FileNotFoundException e) {
            console.print(AppMessages.FILE_NOT_FOUND.getMessage());
        } catch (IOException e) {
            console.print(AppMessages.IO_EXCEPTION.getMessage());
        }
    }
}