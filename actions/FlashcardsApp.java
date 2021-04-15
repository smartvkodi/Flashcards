package flashcards.actions;

import flashcards.models.AppMessages;
import flashcards.models.ApplicationConsole;
import flashcards.models.ApplicationScanner;

import java.util.HashMap;
import java.util.Map;

public class FlashcardsApp {

    private static String exportFile;

    public FlashcardsApp() {
    }

    public FlashcardsApp(String[] args) {
        Map<String, String> configMap = parseArguments(args);
        String importFile = configMap.get("-import") != null ? configMap.get("-import") : "";
        exportFile = configMap.get("-export") != null ? configMap.get("-export") : "";

        if (!importFile.isBlank()) {
            new ImportFromFile(importFile).execute();
        }
    }

    String getExportFile() {
        return exportFile;
    }

    public void run() {
        ApplicationConsole console = new ApplicationConsole(System.out);

        try (ApplicationScanner scanner = new ApplicationScanner(System.in)) {
            boolean spinApp = true;
            do {
                console.print(AppMessages.INPUT_ACTION.getMessage());

                String option = scanner.nextLine();
                switch (option.toLowerCase()) {
                    case "add":
                        new AddCard().execute();
                        break;
                    case "remove":
                        new RemoveCard().execute();
                        break;
                    case "import":
                        new ImportFromFile().execute();
                        break;
                    case "export":
                        new ExportToFile().execute();
                        break;
                    case "ask":
                        new Quiz().execute();
                        break;
                    case "log":
                        new LogToFile().execute();
                        break;
                    case "hardest card":
                        new HardestCard().execute();
                        break;
                    case "reset stats":
                        new ResetStats().execute();
                        break;
                    case "exit":
                        new Exit().execute();
                        spinApp = false;
                        break;
                    default:
                        break;
                }
            } while (spinApp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> parseArguments(String[] args) {
        int argsNumber = args.length;
        Map<String, String> configMap = new HashMap<>(argsNumber);

        for (int i = 0; i < argsNumber; i++) {
            String token = args[i];
            String value = "";
            if (i + 1 < argsNumber) {
                value = args[i + 1];
            }
            if (token.startsWith("-")) {
                configMap.put(token.toLowerCase(), value);
            }
        }

        return configMap;
    }

}
