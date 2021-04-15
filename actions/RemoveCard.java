package flashcards.actions;

import flashcards.models.*;

class RemoveCard implements Action {

    @Override
    public void execute() {
        ApplicationConsole console = new ApplicationConsole(System.out);
        console.print(AppMessages.REMOVE_WHICH_CARD.getMessage());

        String term = new ApplicationScanner(System.in).nextLine();
        if (new Card().removeTerm(term)) {
            console.print(AppMessages.CARD_REMOVED.getMessage());
        } else {
            console.printf(AppMessages.CARD_NOT_REMOVED.getMessage(), term);
        }
    }
}