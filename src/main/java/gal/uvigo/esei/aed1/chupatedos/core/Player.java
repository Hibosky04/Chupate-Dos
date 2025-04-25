package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<Card> hand;
    private Card card;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.card = null;

    }

    public String getName() {
        return name;
    }

//    public List<Card> getHand() {
//        return this.hand;
//    }

    public Card getCard() {
        return card;
    }
    
    
    public void setCard(Card card) {
        this.card = card;
    }
    /*
    * el jugador escoge un carta de su mano y la juega
    * @param i
     */
    public Card playCard(int i) {
        return hand.remove(i);
    }

    /**
     * El jugador recoge la carta que le reparten (robo
     * de la mano inicial)
     *
     * @param card
     */
    public void collectCard(Card card) {
        this.hand.add(card);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append("\nHand:").append(this.hand);
        sb.append('\n');
        return sb.toString();
    }

}
