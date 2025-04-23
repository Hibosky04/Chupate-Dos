package gal.uvigo.esei.aed1.chupatedos.core;

import es.uvigo.esei.aed1.tads.stack.LinkedStack;
import es.uvigo.esei.aed1.tads.stack.Stack;
import java.util.ArrayList;
import java.util.List;



public class Table {
    
    private Stack<Card> playedCards;
//constructor
    public Table() {
        this.playedCards = new LinkedStack<>();
    }
//añadir carta a la mesa
    public void addPlayedCard(Card card){
        this.playedCards.push(card);
    }
//num total de cartas en la mesa 
    public int size(){
        return this.playedCards.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.playedCards.top());
        sb.append("Total card played").append(size());
        return sb.toString();
    }
}
