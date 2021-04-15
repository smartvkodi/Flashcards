package flashcards.actions;

import flashcards.models.Action;
import flashcards.models.AppMessages;
import flashcards.models.ApplicationConsole;
import flashcards.models.Card;

import java.util.List;
import java.util.stream.Collectors;

class HardestCard implements Action {

    @Override
    public void execute() {
        List<Card> cards = new Card().getCards();
        ApplicationConsole console = new ApplicationConsole(System.out);

        if (cards.size() == 0) {
            console.print(AppMessages.NO_CARDS_WITH_ERRORS.getMessage());
        } else {
            int max = cards.stream().mapToInt(Card::getMistakes).max().orElseThrow();
            List<Card> hardest = cards.stream().filter(c -> c.getMistakes() == max).collect(Collectors.toList());

            Card firstCard = hardest.get(0);

            int maxMistakes = firstCard.getMistakes();
            int countHardest = hardest.size();

            if (maxMistakes == 0) {
                console.print(AppMessages.NO_CARDS_WITH_ERRORS.getMessage());
            } else if (countHardest == 1) {
                console.printf(AppMessages.ONE_HARDEST_CARD.getMessage(),
                        firstCard.getTerm(), maxMistakes);
            } else {
                StringBuilder message = new StringBuilder(String
                        .format(AppMessages.MANY_HARDEST_CARDS.getMessage(), firstCard.getTerm()));

                for (int i = 1; i < hardest.size(); i++) {
                    message.append(String.format(", \"%s\"", hardest.get(i).getTerm()));
                }

                message.append(String
                        .format(AppMessages.THERE_ARE_N_ERRORS.getMessage(), maxMistakes));

                console.print(message.toString());
            }
        }
    }
}
