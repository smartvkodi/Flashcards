package flashcards.models;

import java.util.Objects;

public class Card {

    String term;
    String definition;
    int mistakes;

    public Card(String term, String definition) {
        this.term = term;
        this.definition = definition;
        this.mistakes = 0;
    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }

    public int getMistakes() {
        return mistakes;
    }

    public Card setMistakes(int mistakes) {
        this.mistakes = mistakes;
        return this;
    }

    public void increaseMistakes() {
        this.mistakes += 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return term.equals(card.term) || definition.equals(card.definition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(term, definition);
    }
}
