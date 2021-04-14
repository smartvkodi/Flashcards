package flashcards.actions;

import flashcards.models.Action;
import flashcards.models.AppMessages;
import flashcards.models.Card;

class AddCard implements Action {

    FlashcardsApp app = FlashcardsApp.INSTANCE;

    @Override
    public void execute() {
        app.appConsole.print(AppMessages.THE_CARD_TERM.getMessage());

        String term = app.appScanner.nextLine();

        if (app.cardsList.containsKey(term)) {
            app.appConsole.printf(AppMessages.TERM_ALREADY_EXIST.getMessage(), term);

        } else if (term != null && !term.isBlank()) {
            app.appConsole.print(AppMessages.ADD_DEFINITION.getMessage());

            String definition = app.appScanner.nextLine();
            if (definition != null && !definition.isBlank()) {
                Card card = new Card(term, definition);

                if (app.cardsList.containsValue(card)) {
                    app.appConsole
                            .printf(AppMessages.DEFINITION_ALREADY_EXIST.getMessage(), definition);
                } else {
                    app.cardsList.put(term, card);
                    app.appConsole
                            .printf(AppMessages.PAIR_ADDED.getMessage(), term, definition);
                }
            } else {
                app.appConsole.print(AppMessages.DEFINITION_EMPTY.getMessage());
            }
        } else {
            app.appConsole.print(AppMessages.TERM_EMPTY.getMessage());
        }
    }
}