package flashcards.actions;

import flashcards.models.*;

import java.util.List;

class Quiz implements Action {

    @Override
    public void execute() {
        Card card = new Card();

        ApplicationConsole console = new ApplicationConsole(System.out);
        ApplicationScanner scanner = new ApplicationScanner(System.in);

        List<Card> cards = card.getCards();
        int listSize = cards.size();
        if (listSize == 0) {
            console.print(AppMessages.QUIZ_EMPTY_CARDS_LIST.getMessage());
        } else {

            console.print(AppMessages.QUIZ_HOW_MANY_TIMES.getMessage());

            try {
                int questionsNumber = Integer.parseInt(scanner.nextLine());
                if (questionsNumber > 0) {
                    List<Card> quizList = card.getRandomQuizList(questionsNumber);

                    quizList.forEach(quizCard -> {
                        console.printf(AppMessages.QUIZ_PRINT_DEFINITION.getMessage(), quizCard.getTerm());

                        Card userAnswer = card.getCardByDefinition(scanner.nextLine());

                        StringBuilder message = new StringBuilder();

                        if (!quizCard.equals(userAnswer)) {
                            quizCard.increaseMistakes();
                            message.append(String.format(AppMessages.QUIZ_WRONG_ANSWER.getMessage(),
                                    quizCard.getDefinition()));
                            if (userAnswer != null) {
                                message.append(String.format(AppMessages.QUIZ_BUT_CORRECT.getMessage(),
                                        userAnswer.getTerm()));
                            } else {
                                message.append(".\n");
                            }
                        } else {
                            message.append(AppMessages.QUIZ_CORRECT_ANSWER.getMessage());
                        }
                        console.print(message.toString());
                    });
                }
            } catch (NumberFormatException e) {
                // no quiz
            }
        }
    }
}