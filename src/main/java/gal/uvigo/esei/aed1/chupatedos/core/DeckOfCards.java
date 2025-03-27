package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.Collections;
import java.util.Stack;

public class DeckOfCards {

    private Stack<Card> DeckOfCards;
    private static final int NumOfCards = 40;

   // no estoy segura
    public DeckOfCards() {
        this.DeckOfCards = new Stack<>();
        for (int i = 1; i <= 10; i++) {
            DeckOfCards.add(i,Card.AS_BASTOS );
            DeckOfCards.add(i,Card.AS_COPAS );
            DeckOfCards.add(i,Card.AS_ESPADAS );
            DeckOfCards.add(i,Card.AS_OROS );
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
