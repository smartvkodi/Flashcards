package flashcards.models;

public enum AppMessages {

    INPUT_ACTION("\nInput the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):\n"),
    // Add card
    THE_CARD_TERM("The card:\n"),
    TERM_EMPTY("The card can not be empty.\n"),
    TERM_ALREADY_EXIST("The card \"%s\" already exists.\n"),
    ADD_DEFINITION("The definition of the card:\n"),
    DEFINITION_ALREADY_EXIST("The definition \"%s\" already exists.\n"),
    EXIT("Bye bye!\n"),
    DEFINITION_EMPTY("The definition can not be empty.\n"),
    PAIR_ADDED("The pair (\"%s\":\"%s\") has been added.\n"),
    // Remove card
    REMOVE_WHICH_CARD("Which card?\n"),
    CARD_NOT_REMOVED("Can't remove \"%s\": there is no such card.\n"),
    CARD_REMOVED("The card has been removed.\n"),
    // Import / Export
    FILE_NAME("File name:\n"),
    FILE_NOT_FOUND("File not found.\n"),
    IO_EXCEPTION("Failed to read file.\n"),
    LOADED_CARDS_NUMBER("%d cards have been loaded.\n"),
    SAVED_CARDS_NUMBER("%d cards have been saved.\n"),
    // Hardest card
    NO_CARDS_WITH_ERRORS("There are no cards with errors.\n"),
    ONE_HARDEST_CARD("The hardest card is \"%s\". You have %d errors answering it.\n"),
    MANY_HARDEST_CARDS("The hardest cards are \"%s\""),
    THERE_ARE_N_ERRORS(". You have %d errors answering them.\n"),
    // Save the log messages
    LOG_SAVED("The log has been saved.\n"),
    // Quiz messages
    QUIZ_HOW_MANY_TIMES("How many times to ask?\n"),
    QUIZ_PRINT_DEFINITION("\nPrint the definition of \"%s\":\n"),
    QUIZ_CORRECT_ANSWER("Correct!\n"),
    QUIZ_WRONG_ANSWER("Wrong. The right answer is \"%s\""),
    QUIZ_BUT_CORRECT(", but your definition is correct for \"%s\" card.\n"),
    QUIZ_EMPTY_CARDS_LIST("Flashcards list is empty\n"),
    // Statistics messages
    STATISTICS_SAVED("Card statistics have been reset.\n");
    //

    private final String message;

    AppMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}