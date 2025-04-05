package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;
    private Card hiddenCard;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.hiddenCard = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<Card> getHand(){
        return this.hand;


    public Card getHiddenCard() {
        return hiddenCard;
    }

    public void setHiddenCard(Card hiddenCard) {
        this.hiddenCard = hiddenCard;
    }

    public Card playCard(int i){
        while(!(i<=0|| i> hand.size())){
           i=askNumCard();
        }
        return hand.remove(hand.get(i-1));
    }

    /**
     * El jugador recoge la carta que le reparten y la ordena en su mano
     * (robo de la mano inicial)
     * @param collectedCard 
     */
    public void CollectCard(Card collectedCard) {
        if (hand.isEmpty()) {
            int j = 0;
            while (j < hand.size()) {
                hand.add(j, collectedCard);
                j++;
            }
        } else {
            hand.add(collectedCard);
        }
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

