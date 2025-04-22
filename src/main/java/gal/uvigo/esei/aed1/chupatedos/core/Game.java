package gal.uvigo.esei.aed1.chupatedos.core;

import gal.uvigo.esei.aed1.chupatedos.iu.IU;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Game {

    private final IU iu;
    private DeckOfCards deckOfCard;
    private Table table;
    private List<Player> players;


    public Game(IU iu) {
        this.iu = iu;
        this.deckOfCard = new DeckOfCards();
        this.players = new ArrayList<>();
        this.table = new Table();

    }

    /**
     * Metodo principal para jugar
     */
    public void play() {
        createPlayer();
         do {
            deckOfCard.shuffleDeck();
            collectCard();
            table.addPlayedCard(firstCard());
            iu.showPlayers(players);
            iu.showTable(table);
            for (int i = 0; i < 7; i++) {
                colocarCartas();
                iu.showTable(table);
                iu.showPlayers(players);
        } while (!endOfGame());
    }

    /**
     * Crea los jugadores
     */
    public void createPlayer() {
        for (String s : iu.askPlayersName()) {
            this.players.add(new Player(s));
        }
        
    }

    /**
     * Reparte diez cartas a cada jugador al inicio de cada ronda
     */
    public void collectCard() {
        for (int i=0; i<7; i++){
            for (Player player : players) {
                player.collectCard(deckOfCard.removeCard());
            }
        }
    }

    /** 
     * @return la carta de la mesa
     */
    public Card firstCard() {
        return this.deckOfCard.removeCard();
    }
    public List<Card> legalCards(Player player){
        List<Card> legal = new LinkedList<>();
        for(Card card : player.getHand()){
            if(card.getNumber().isEqual(table.top().getNumber())||card.getSuit().isEqual(table.top().getSuit()){
                legal.add(card);
            }
        }
        return legal;
    }
     /**
    * Selecciona la carta que se va jugar en cada jugador
    */
    public List<Card> selectedCards(){
        List<Card> listSelectedCards = new ArrayList<>();
        for(Player player : players){
            player.setCard(player.playCard(iu.askNumCard(player)));
            listSelectedCards.add(player.getCard());
        }
        iu.showSelectedCard((Player) listSelectedCards);
        return listSelectedCards;
    }
    /*
    * Se añade a la mesa la carta que se va a jugar después de seleccionarla
    */
    public void playCard (){
        Table.push(this.player.get(0).remove(selectCards));
    }

    /*
    * Se carga una carta del mazo, si no hay cartas se rellena el mazo con las cartas jugadas menos la ultima que se jugo, se barajea el mazo
    */
    public Card loadCard(){
        if(deckOfCard.getNumOfCards()==0){
            Card lastCardPlayed = table.getPlayedCards().pop();
            while(!table.getPlayedCards().isEmpty()){
                deckOfCard.addCard(table.getPlayedCards().pop());
            }
            table.getPlayedCards().push(lastCardPlayed);
            deckOfCard.shuffleDeck();
        }
    return deckOfCard.removeCard();
    }

     public boolean endOfGame() {
        boolean finPartida = false;
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                finPartida = true;
            }
        }
        return finPartida;
    }
}    

