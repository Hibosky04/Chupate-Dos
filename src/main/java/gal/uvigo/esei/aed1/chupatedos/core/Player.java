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
    /*
    * El jugador quita de su mano y juega una carta 
    * @param selectedCard
    * 
     */
    public void playCard(Card selectedCard) {
        hand.remove(selectedCard);
    }

    /*
    * Se verifica si la mano del jugador esta vacia
    * @return si la mano esta vacia o no
     */
    public boolean checkHand() {
        return hand.isEmpty();
    }
    /*
    * Se crea una lista de cartas legales que se puedan jugar
    * @param cardOnTable
    * @return lista de cartas jugables
     */
    public List<Card> legalCards(Card cardOnTable) {
        List<Card> legal = new ArrayList<>();
        for (Card c : hand) {
            if (cardOnTable.getNumber() == c.getNumber() || cardOnTable.getSuit() == c.getSuit()) {
                legal.addLast(c);
            }
        }
        return legal;
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
        sb.append("\nHand:").append(this.hand);
        sb.append('\n');
        return sb.toString();
    }

}
