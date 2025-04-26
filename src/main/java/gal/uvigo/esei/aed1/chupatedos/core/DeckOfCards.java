package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.Collections;
import java.util.Stack;

public class DeckOfCards {

    private Stack<Card> deck;


    public DeckOfCards() {
        this.deck = new Stack<>();
         for(Card cards : Card.values())
        {
            deck.push(cards);        
        }
    }

     /**
     * Devuelve cuantas cartas hay en el mazo
     * @return el numero de cartas restantes
     */
    
    public int getSize(){
        return deck.size();
    }
    
    
    /**
     * baraja con la instrucción shuffle()
     */
    public void shuffleDeck() {
        Collections.shuffle(this.deck);
    }

    /**
     * Quitamos una carta de la baraja
     *
     * @return una carta de la baraja
     */
    public Card removeCard() {
        return this.deck.pop();
    }

    /**
     * Introducimos una carat en la baraja
     *
     * @param c
     */
    public void addCard(Card c) {
        deck.add(c);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Deck of card: ").append(deck);
        return sb.toString();
    }
    
    

}
