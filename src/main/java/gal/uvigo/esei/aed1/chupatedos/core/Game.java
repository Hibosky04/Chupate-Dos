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
        crearJugadores();
        deckOfCard.barajar();
        repartirCartas();
        iu.showPlayers(players);

    }

    /**
     * Crea los jugadores
     */
    public void crearJugadores() {
        for (String s : iu.askPlayersName()) {
            this.players.add(new Player(s));
        }
    }

    /**
     * Reparte diez cartas a cada jugador al inicio de cada ronda
     */
    public void repartirCartas() {
        for (int i = 0; i < 7; i++) {
            for (Player player : players) {
                player.CollectCard(deckOfCard.quitarCarta());
            }
        }
    }
}

 

}
