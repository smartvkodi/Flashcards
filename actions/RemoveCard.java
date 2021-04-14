package flashcards.actions;

import flashcards.models.Action;
import flashcards.models.AppMessages;

class RemoveCard implements Action {

    private final FlashcardsApp app = FlashcardsApp.INSTANCE;

    @Override
    public void execute() {
        app.appConsole.print(AppMessages.REMOVE_WHICH_CARD.getMessage());

        String term = app.appScanner.nextLine();

        if (!app.cardsList.containsKey(term)) {
            app.appConsole.printf(AppMessages.CARD_NOT_REMOVED.getMessage(), term);
        } else {
            app.cardsList.remove(term);
            app.appConsole.print(AppMessages.CARD_REMOVED.getMessage());
        }
    }
}