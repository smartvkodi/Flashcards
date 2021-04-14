package flashcards.actions;

import flashcards.models.Action;
import flashcards.models.AppMessages;

class ResetStats implements Action {

    private final FlashcardsApp app = FlashcardsApp.INSTANCE;

    @Override
    public void execute() {
        app.cardsList.values().forEach(card -> card.setMistakes(0));
        app.appConsole.print(AppMessages.STATISTICS_SAVED.getMessage());
    }
}
