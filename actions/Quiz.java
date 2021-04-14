package flashcards.actions;

import flashcards.models.Action;
import flashcards.models.AppMessages;
import flashcards.models.Card;

import java.util.Random;
import java.util.Set;

class Quiz implements Action {

    private final FlashcardsApp app = FlashcardsApp.INSTANCE;

    @Override
    public void execute() {

        Set<String> terms = app.cardsList.keySet();
        int listSize = terms.size();
        if (listSize == 0) {
            app.appConsole.print(AppMessages.QUIZ_EMPTY_CARDS_LIST.getMessage());

        } else {
            app.appConsole.print(AppMessages.QUIZ_HOW_MANY_TIMES.getMessage());

            int questionsNumber = 0;
            try {
                questionsNumber = Integer.parseInt(app.appScanner.nextLine());
            } catch (NumberFormatException e) {
                // don't worry! questionsNumber = 0
            }
            if (questionsNumber <= 0 || questionsNumber > listSize) {
                questionsNumber = Math.min(listSize, 50);
            }

            Random generator = new Random();
            while (questionsNumber-- > 0) {

                String randomTerm = terms.stream()
                        .skip(generator.nextInt(listSize > 1 ? listSize - 1 : 1))
                        .findFirst().orElseThrow();

                app.appConsole.printf(AppMessages.QUIZ_PRINT_DEFINITION.getMessage(), randomTerm);

                String answerDefinition = app.appScanner.nextLine();
                Card userAnswer = app.getCardByDefinition(answerDefinition);
                Card correctAnswer = app.cardsList.get(randomTerm);

                StringBuilder message = new StringBuilder();
                if (!correctAnswer.equals(userAnswer)) {
                    correctAnswer.increaseMistakes();
                    message.append(String.format(AppMessages.QUIZ_WRONG_ANSWER.getMessage(),
                                                    correctAnswer.getDefinition()));
                    if (userAnswer == null) {
                        message.append(".\n");
                    } else {
                        message.append(String.format(AppMessages.QUIZ_BUT_CORRECT.getMessage(),
                                userAnswer.getTerm()));
                    }

                } else {
                    message.append(AppMessages.QUIZ_CORRECT_ANSWER.getMessage());
                }

                app.appConsole.print(message.toString());
            }
        }
    }
}