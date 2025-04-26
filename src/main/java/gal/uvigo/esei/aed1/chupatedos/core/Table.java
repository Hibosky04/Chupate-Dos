package gal.uvigo.esei.aed1.chupatedos.core;

import es.uvigo.esei.aed1.tads.stack.LinkedStack;
import es.uvigo.esei.aed1.tads.stack.Stack;
import java.util.ArrayList;
import java.util.List;



public class Table {
    
    private Stack<Card> playedCards;
    
    public Table() {
        this.playedCards = new LinkedStack<>();
    }

    public Stack<Card> getPlayedCards() {
        return playedCards;
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
    public Card UpsideCard(){
        return playedCards.top();
    }
    
    /**
     * Elimina una carta de la pila de las ya jugadas
     */
    public void removeCard(){
        playedCards.pop();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.playedCards.top());
        sb.append("Total card played ").append(size());
        return sb.toString();
    }

    
    
}
