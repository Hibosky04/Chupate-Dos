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

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public Card getHiddenCard() {
        return hiddenCard;
    }

    public void setHiddenCard(Card hiddenCard) {
        this.hiddenCard = hiddenCard;
    }

    /**
     * El jugador recoge la carta que le reparten y la ordena en su mano
     * @param collectedCard 
     */
    public void CollectCard(Card collectedCard) {
        if (!hand.isEmpty()) {
            int j = 0;
            while (j < hand.size() && collectedCard.getNumber() > hand.get(j).getNumber()) {
                j++;
            }
            hand.add(j, collectedCard);
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
