package flashcards.actions;

import flashcards.models.Action;
import flashcards.models.AppMessages;
import flashcards.models.Card;

import java.util.ArrayList;
import java.util.List;

class HardestCard implements Action {

    private final FlashcardsApp app = FlashcardsApp.INSTANCE;

    @Override
    public void execute() {
        int maxMistakes = 0;
        int mistakes;
        for (Card card : app.cardsList.values()) {
            mistakes = card.getMistakes();
            maxMistakes = Math.max(maxMistakes, mistakes);
        }

        if (maxMistakes == 0) {
            app.appConsole.print(AppMessages.NO_CARDS_WITH_ERRORS.getMessage());
        } else {
            List<Card> hardestCards = new ArrayList<>();

            int finalMaxMistakes = maxMistakes;
            app.cardsList.values().stream()
                    .filter(c -> c.getMistakes() == finalMaxMistakes)
                    .forEach(hardestCards::add);

            int hcSize = hardestCards.size();

            if (hcSize == 1) {
                app.appConsole.printf(AppMessages.ONE_HARDEST_CARD.getMessage(),
                        hardestCards.get(0).getTerm(), maxMistakes);
            } else {
                StringBuilder message = new StringBuilder();
                message.append(String
                        .format(AppMessages.MANY_HARDEST_CARDS.getMessage(), hardestCards.get(0).getTerm()));

                for (int i = 1; i < hcSize; i++) {
                    message.append(String
                            .format(", \"%s\"", hardestCards.get(i).getTerm()));
                }

                message.append(String
                        .format(AppMessages.THERE_ARE_N_ERRORS.getMessage(), maxMistakes));

                app.appConsole.print(message.toString());
            }
        }
    }
}
