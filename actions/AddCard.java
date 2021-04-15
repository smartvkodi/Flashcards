package flashcards.actions;

import flashcards.models.*;

class AddCard implements Action {

    @Override
    public void execute() {
        Card card = new Card();

        ApplicationConsole console = new ApplicationConsole(System.out);
        console.print(AppMessages.THE_CARD_TERM.getMessage());

        String term = new ApplicationScanner(System.in).nextLine();

        if (term != null && !term.isBlank()) {

            if (card.existsCardWithTerm(term)) {
                console.printf(AppMessages.TERM_ALREADY_EXIST.getMessage(), term);

            } else {
                console.print(AppMessages.ADD_DEFINITION.getMessage());
                String definition = new ApplicationScanner(System.in).nextLine();

                if (definition != null && !definition.isBlank()) {

                    if (card.existsCardWithDefinition(definition)) {
                        console.printf(AppMessages.DEFINITION_ALREADY_EXIST.getMessage(), definition);

                    } else {
                        card.addNewCard(new Card(term, definition));
                        console.printf(AppMessages.PAIR_ADDED.getMessage(), term, definition);
                    }

                } else {
                    console.print(AppMessages.DEFINITION_EMPTY);
                }
            }
        } else {
            console.print(AppMessages.TERM_EMPTY);
        }

    }
}