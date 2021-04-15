package flashcards.actions;

import flashcards.models.Action;
import flashcards.models.AppMessages;
import flashcards.models.ApplicationConsole;

class Exit implements Action {

    @Override
    public void execute() {
        ApplicationConsole console = new ApplicationConsole(System.out);
        String exportFile = new FlashcardsApp().getExportFile();

        if (exportFile != null && !exportFile.isBlank()) {
            new ExportToFile(exportFile).execute();
        }

        console.print(AppMessages.EXIT.getMessage());
    }
}
