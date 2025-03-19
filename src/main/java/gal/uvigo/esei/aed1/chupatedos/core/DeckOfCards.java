package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.Collections;
import java.util.Stack;

public class DeckOfCards {

    private Stack<Card> DeckOfCards;
    private static final int NumOfCards = 40;

    public DeckOfCards() {
        this.DeckOfCards = new Stack<>();
        for (int i = 0; i < NumOfCards; i++) {
            DeckOfCards.add(new Card(i + 1));
        }
    }

    /**
     * baraja con la instrucción shuffle()
     */
    public void barajar() {
        Collections.shuffle(this.DeckOfCards);
    }

    /**
     * Quitamos una carta de la baraja
     *
     * @return una carta de la baraja
     */
    public Card quitarCarta() {
        Card toret = DeckOfCards.pop();
        return toret;
    }

    /**
     * Introducimos una carat en la baraja
     *
     * @param c
     */
    public void insertarCarta(Card c) {
        DeckOfCards.add(c);
    }

}
