package gal.uvigo.esei.aed1.chupatedos.core;

import gal.uvigo.esei.aed1.chupatedos.iu.IU;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Game {

    private final IU iu;
    private DeckOfCards deckOfCard;
    private Table table;
    private List<Player> players;
    private Player playerTurn;

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
        playerTurn = players.get(0);
        deckOfCard.shuffleDeck();
        collectCard();
        table.addPlayedCard(firstCard());
        checkFirstSpecialCard();
        boolean endGame = false;
        do {
            Card topCard = table.upsideCard();
            System.out.println(deckOfCard.toString());

            iu.showTable(table);
            iu.displayMessage("\nTurn of: " + playerTurn.getName());
            if (playerTurn.legalCards(topCard).isEmpty()) {
                iu.displayMessage("\n" + playerTurn.getName() + " doesn't have legal cards, must collect card");
                playerTurn.collectCard(this.loadCard());
            }
            if (!playerTurn.legalCards(topCard).isEmpty()) {
                iu.showPlayer(playerTurn);
                iu.showLegalCards(playerTurn.legalCards(topCard));
                this.selectCard(playerTurn, playerTurn.legalCards(topCard));
                checkSpecialCard();
                endGame = this.endOfGame(playerTurn);
            } else {
                iu.displayMessage("\n" + playerTurn.getName() + " don't play a card and loses turn");
            }

            playerTurn = nextPlayer(playerTurn);
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
     */
    public void selectCard(Player player, List<Card> listOfCards) {
        int numCard = iu.askNumCard(listOfCards);
        Card cardSelected = listOfCards.get(numCard);
        player.playCard(cardSelected);
        table.addPlayedCard(cardSelected);
        iu.displayMessage(player.getName() + " played: " + cardSelected);
    }

    /**
     * Se carga una carta del mazo, si no hay cartas se rellena el mazo con las
     * cartas jugadas menos la ultima que se jugo, se barajea el mazo
     *
     * @return las cartas al mazo
     */
    public Card loadCard() {
        if (deckOfCard.getSize() == 0) {
            Stack<Card> stack = new Stack<>();
            stack.addAll(table.playedCardMinusTopPlayed());
            while (!stack.isEmpty()) {
                deckOfCard.addCard(stack.pop());
            }
            deckOfCard.shuffleDeck();
        }
        return deckOfCard.removeCard();
    }

    /**
     *
     * @param player
     * @return el player sigiente
     */
    public Player nextPlayer(Player player) {
        if (player.equals(players.getLast())) {
            return players.getFirst();
        }
        return players.get(players.indexOf(player) + 1);
    }

    /**
     * 
     */
    public void checkFirstSpecialCard() {
        if (table.upsideCard().getNumber() == 7) {
            this.reverseTurns();
            playerTurn = nextPlayer(playerTurn);
            iu.displayMessage("The turns have been inverted");
        }
        if (table.upsideCard().getNumber() == 2) {
            this.drawTwoFirstCard();
            
        }
    }

    /**
     * se mira que la carta en la mesa es igual a siete o dos si la carta es
     * igual a 7 llama al método reverseTurns y si la carta es 2 llama al método
     * drawTwo
     */
    public void checkSpecialCard() {
        if (table.upsideCard().getNumber() == 7) {
            this.reverseTurns();
            iu.displayMessage("The turns have been inverted");
        }
        if (table.upsideCard().getNumber() == 2) {
            this.drawTwo();
            
        }
    }

    /**
     * El jugador siguiente roba 2 cartas y pasamos el turno al siguiente
     * 
     */
    public void drawTwo() {
        playerTurn = nextPlayer(playerTurn);
        for (int i = 0; i < 2; i++) {
            playerTurn.collectCard(loadCard());
        }
        iu.displayMessage(playerTurn.getName() + " has to draw 2 cards and looses their turn!");
        
    }
    
    /**
     * El jugador siguiente roba 2 cartas y pasamos el turno al siguiente
     */
    public void drawTwoFirstCard(){
        for (int i = 0; i < 2; i++) {
            playerTurn.collectCard(loadCard());
        }
        iu.displayMessage(playerTurn.getName() + " has to draw 2 cards and looses their turn!");
         playerTurn = nextPlayer(playerTurn);
    }

    /**
     * invierte el orden de los turnos usando una pila para reordenar la lista
     * de jugadores, lo que cambia la dirección del juego cuando se juega una
     * carta especial (7)
     */
    public void reverseTurns() {
        Stack<Player> temp = new Stack<>();
        for (int i = 0; i < players.size(); i++) {
            temp.push(players.get(i));
        }
        players.clear();
        while (!temp.isEmpty()) {
            players.addLast(temp.pop());
        }
    }

    /**
     * @param player
     * @return verdadero si el jugador tiene la mano vacia y se acaba el juego y
     * si fuera falso que siga el juego
     */
    public boolean endOfGame(Player player) {
        if (player.checkHand()) {
            iu.displayMessage("\nThe winner is: " + player.getName());
            return true;
        }
        return false;
    }
}
