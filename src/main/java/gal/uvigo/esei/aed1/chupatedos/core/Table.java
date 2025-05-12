package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Table {
    
    private Stack<Card> playedCards;
    
    public Table() {
        this.playedCards = new Stack<>();
    }
    
    /**
    * Agrega una carta a la mesa
    * 
    */
    public void addPlayedCard(Card card){
        this.playedCards.push(card);
    }
    
    /**
    * Cantidad de cartas jugadas
    * @return devuelve cuantas cartas ya han sido jugadas
    */
    public int size(){
        return this.playedCards.size();
    }
    
    /**
    *  Carta en juego
    * @return muestra la primera carta de la pila
    */
    public Card upsideCard(){
        return playedCards.peek();
    }
    
    /**
     * Elimina una carta de la pila de las ya jugadas
     */
    public Card removeCard(){
        return playedCards.pop();
    }

    /**
     * Devuelve una lista de las cartas jugadas menos la que esta en juego
     * @return 
     */
    public List<Card> playedCardMinusTopPlayed(){
        List<Card> list = new ArrayList<>();
        Card upsideCard = playedCards.firstElement();
        while(!playedCards.isEmpty()){
            list.addLast(removeCard());
        }
        playedCards.add(upsideCard);
        return list;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nUpside Card: ").append(upsideCard());
        sb.append("\nTotal cards played: ").append(size());
        return sb.toString();
    }

    
 
    
}
