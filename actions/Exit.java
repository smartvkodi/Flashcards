package flashcards.actions;

import flashcards.models.Action;
import flashcards.models.AppMessages;

class Exit implements Action {

    private final FlashcardsApp app = FlashcardsApp.INSTANCE;

    @Override
    public void execute() {
        if (app.exportFile != null && !app.exportFile.isBlank()) {
            new ExportToFile(app.exportFile).execute();
        }

        app.appConsole.print(AppMessages.EXIT.getMessage());
    }
}
