package flashcards.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class Card {

    private static final List<Card> cards = new ArrayList<>();

    String term;
    String definition;
    int mistakes;

    public Card() {
    }

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

    public Card getCardByDefinition(String definition) {
        for (Card card : cards) {
            if (definition.equals(card.getDefinition())) {
                return card;
            }
        }
        return null;
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

    public List<Card> getCards() {
        return cards;
    }

    public void addNewCard(Card card) {
        cards.add(card);
    }

    public boolean existsCardWithTerm(String term) {
        return cards.stream().anyMatch(card -> card.getTerm().equals(term));
    }

    public boolean existsCardWithDefinition(String definition) {
        return cards.stream().anyMatch(card -> card.getDefinition().equals(definition));
    }

    public boolean removeTerm(String term) {
        List<Card> cardsForDelete = cards.stream().filter(card -> card.getTerm().equals(term))
                .collect(Collectors.toList());
        if (cardsForDelete.size() != 0) {
            cards.removeAll(cardsForDelete);
            return true;
        }
        return false;
    }

    public void removeDefinition(String definition) {
        List<Card> cardsForDelete = cards.stream().filter(card -> card.getDefinition().equals(definition))
                .collect(Collectors.toList());
        if (cardsForDelete.size() != 0) {
            cards.removeAll(cardsForDelete);
        }
    }

    public List<Card> getRandomQuizList(int questionsNumber) {
        int cardsListSize = cards.size();

        if (questionsNumber <= 0 || questionsNumber > cardsListSize) {
            questionsNumber = Math.min(cardsListSize, 50);
        }

        Random generator = new Random();

        List<Card> quizList = new ArrayList<>(questionsNumber);
        while (quizList.size() < questionsNumber) {
            Card card = cards.get(generator.nextInt(cardsListSize));
            if (!quizList.contains(card)) {
                quizList.add(card);
            }
        }

        return quizList;
    }
}
