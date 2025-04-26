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
        do {
//            for (int i = 0; i < players.size(); i++) {
//                iu.showPlayerTurn(players.get(i).getName());
//                iu.showPlayer(players.get(i));
//                if (!legalCards(players.get(i)).isEmpty()) {
//                    iu.showLegalCards(legalCards(players.get(i)));
//                    selectCard(players.get(i));
//                    iu.showTable(table);
//                } 
//                else {
//                    players.get(i).collectCard(loadCard());
//                }
//            }
        } while (!endOfGame((Player) this.players));
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


    /*
    * Se carga una carta del mazo, si no hay cartas se rellena el mazo con las cartas jugadas menos la ultima que se jugo, se barajea el mazo
     */
    public Card loadCard() {
        if (deckOfCard.getSize() == 0) {
            Card lastCardPlayed = table.getPlayedCards().pop();
            while (!table.getPlayedCards().isEmpty()) {
                deckOfCard.addCard(table.getPlayedCards().pop());
            }
            table.getPlayedCards().push(lastCardPlayed);
            deckOfCard.shuffleDeck();
        }
        return deckOfCard.removeCard();
    }
/**
 * @param player
 * @return verdadero si el jugador tiene la mano vacia y  se acaba el juego y si fuera falso que siga el juego
 */
    public boolean endOfGame(Player player) {
            if (player.checkHand()) {
                iu.showWinner(player);
                return true;
            }
        return false;
    }
}
