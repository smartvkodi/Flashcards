package flashcards.actions;

import flashcards.models.*;

import java.util.*;

public enum FlashcardsApp {
    INSTANCE;

    Map<String, Card> cardsList = new LinkedHashMap<>();
    ApplicationConsole appConsole = new ApplicationConsole(System.out);
    ApplicationScanner appScanner;

    String importFile;
    String exportFile;


    public void run(String[] args) {
        Map<String, String> configMap = parseArguments(args);

        this.importFile = configMap.get("-import") != null ? configMap.get("-import") : "";
        this.exportFile = configMap.get("-export") != null ? configMap.get("-export") : "";

        if (!importFile.isBlank()) {
            new ImportFromFile(importFile).execute();
        }

        run();
    }

    private void run() {
        try (ApplicationScanner scanner = new ApplicationScanner(System.in)) {
            appScanner = scanner;
            boolean spinApp = true;
            do {
                appConsole.print(AppMessages.INPUT_ACTION.getMessage());

                String option = appScanner.nextLine();
                switch (option.toLowerCase()) {
                    case "add": new AddCard().execute();
                        break;
                    case "remove": new RemoveCard().execute();
                        break;
                    case "import": new ImportFromFile().execute();
                        break;
                    case "export": new ExportToFile().execute();
                        break;
                    case "ask": new Quiz().execute();
                        break;
                    case "log": new LogToFile().execute();
                        break;
                    case "hardest card": new HardestCard().execute();
                        break;
                    case "reset stats": new ResetStats().execute();
                        break;
                    case "exit": new Exit().execute();
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

    Card getCardByDefinition(String definition) {
        for (Card card : cardsList.values()) {
            if (definition.equals(card.getDefinition())) {
                return card;
            }
        }
        return null;
    }
}
