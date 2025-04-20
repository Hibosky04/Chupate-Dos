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
        deckOfCard.shuffleDeck();
        collectCard();
        table.addPlayedCard(firstCard());
        iu.showPlayers(players);
        iu.showTable(table);
        do{
            this.players.add(this.players.remove(0));
            iu.showPlayerTurn(this.players.get(0));
            iu.showSelectedCards(this.players.get(0));
            iu.showSelectedCards(legalCards(this.players.get(0));
            
        }while(!this.players.getHand().isEmpty());    
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
        for (Player player : players) {
            while (player.getHand().size() < 7) {
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
}    

