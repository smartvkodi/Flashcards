package flashcards.actions;

import flashcards.models.Action;
import flashcards.models.AppMessages;
import flashcards.models.ApplicationConsole;
import flashcards.models.Card;

class ResetStats implements Action {

    @Override
    public void execute() {
        Card card = new Card();
        ApplicationConsole console = new ApplicationConsole(System.out);
        card.getCards().forEach(xCard -> xCard.setMistakes(0));
        console.print(AppMessages.STATISTICS_SAVED.getMessage());
    }
}
