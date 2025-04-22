package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;
    

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
      
    }

    public String getName() {
        return name;
    }
    
    public List<Card> getHand(){
        return this.hand;
    }

    /*
    * el jugador escoge un carta de su mano y la juega
    * @param i
    */
    public Card playCard(int i){
        return hand.remove(i);
    }

    /**
     * El jugador recoge la carta que le reparten y la ordena en su mano
     * (robo de la mano inicial)
     * @param collectedCard 
     */
    public void collectCard(Card collectedCard) {
            hand.add(collectedCard);
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append("\nHand:").append(hand);
        sb.append('\n');
        return sb.toString();
    }

    
}
