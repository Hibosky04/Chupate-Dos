package gal.uvigo.esei.aed1.chupatedos.core;

import es.uvigo.esei.aed1.tads.stack.LinkedStack;
import es.uvigo.esei.aed1.tads.stack.Stack;
import gal.uvigo.esei.aed1.chupatedos.iu.IU;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

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
        Player playerTurn = players.get(0);
        boolean endGame = false;
        do {
            iu.showTable(table);
            iu.showPlayerTurn(playerTurn);
            if (!playerTurn.legalCards(table.UpsideCard()).isEmpty()) {
                iu.showPlayer(playerTurn);
                iu.showLegalCards(playerTurn.legalCards(table.UpsideCard()));
                this.selectCard(playerTurn, playerTurn.legalCards(table.UpsideCard()));
                if (table.UpsideCard().getNumber() == 7){
                    this.reverseTurns(players);
                }
                endGame = this.endOfGame(playerTurn);
                playerTurn = this.nextPlayer(playerTurn);
            } else {
                iu.showAlertNoLegalCard(playerTurn);
                playerTurn.collectCard(this.loadCard());
                if (!playerTurn.legalCards(table.UpsideCard()).isEmpty()) {
                    iu.showPlayer(playerTurn);
                    iu.showLegalCards(playerTurn.legalCards(table.UpsideCard()));
                    this.selectCard(playerTurn, playerTurn.legalCards(table.UpsideCard()));
                    if (table.UpsideCard().getNumber() == 7){
                    this.reverseTurns(players);
                }
                    endGame = this.endOfGame(playerTurn);
                    playerTurn = this.nextPlayer(playerTurn);
                } else {
                    iu.showAlertLostTurn(playerTurn);
                    playerTurn = this.nextPlayer(playerTurn);
                }
            }
        } while (!endGame);
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
    * Reparte siete cartas a cada jugador al inicio de cada ronda
    */
    public void collectCard() {
        for (int i = 0; i < 7; i++) {
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

    /**
     * Selecciona la carta que se va jugar en cada jugador
     *
     * @param player
     * @param listOfCards
     * @return
     */
    public void selectCard(Player player, List<Card> listOfCards) {
        int numCard = iu.askNumCard(listOfCards);
        Card cardSelected = listOfCards.get(numCard);
        table.addPlayedCard(player.playCard(cardSelected));
        iu.showSelectedCard(player, cardSelected);
    }

    /**
     * Se carga una carta del mazo, si no hay cartas se rellena el mazo con las
     * cartas jugadas menos la ultima que se jugo, se barajea el mazo
     * 
     * @return
     */
    public Card loadCard() {
        if (deckOfCard.getSize() == 0) {
            Card lastCardPlayed = table.UpsideCard();
            while (!table.getPlayedCards().isEmpty()) {
                deckOfCard.addCard(table.removeCard());
            }
            table.addPlayedCard(lastCardPlayed);
            deckOfCard.shuffleDeck();
        }
        return deckOfCard.removeCard();
    }

    /**
     *
     * @param player
     * @return
     */
    public Player nextPlayer(Player player) {
        if (player.equals(players.getLast())) {
            return players.getFirst();
        }
        return players.get(players.indexOf(player) + 1);
    }
    /*
    * invierte el orden de los turnos
    */
    public List<Player> reverseTurns(List<Player> players){
         Stack<Player> temp = new LinkedStack<>();
            for(int i=0;i<players.size();i++){
                temp.push(players.get(i));
            }
            players.clear();
            while(!temp.isEmpty()){
                players.addLast(temp.pop());
            }
            return players ;
    }
    
    /**
     *
     * @param player
     * @return verdadero si el jugador tiene la mano vacia y se acaba el juego y
     * si fuera falso que siga el juego
     */
    public boolean endOfGame(Player player) {
        if (player.checkHand()) {
            iu.showWinner(player);
            return true;
        }
        return false;
    }
}

