package gal.uvigo.esei.aed1.chupatedos.core;

import gal.uvigo.esei.aed1.chupatedos.iu.IU;
import java.util.List;
import java.util.Stack;
import java.util.LinkedList;

public class Game {

    private final IU iu;
    private DeckOfCards deckOfCard;
    private Table table;
    private List<Player> players;
    private Player playerTurn;

    public Game(IU iu) {
        this.iu = iu;
        this.deckOfCard = new DeckOfCards();
        this.players = new LinkedList<>();
        this.table = new Table();

    }

    /**
     * Metodo principal para jugar
     */
    public void play() {
        createPlayer();
        playerTurn=players.get(0);
        deckOfCard.shuffleDeck();
        collectCard();
        table.addPlayedCard(firstCard());
        checkCard();
        boolean endGame = false;
        do {
            iu.showTable(table);
            iu.displayMessage("\nTurn of: " + playerTurn.getName());
            if (!playerTurn.legalCards(table.upsideCard()).isEmpty()) {
                iu.showPlayer(playerTurn);
                iu.showLegalCards(playerTurn.legalCards(table.upsideCard()));
                this.selectCard(playerTurn, playerTurn.legalCards(table.upsideCard()));
                checkCard();
                endGame = this.endOfGame(playerTurn);
            } else {
               iu.displayMessage("\n" + playerTurn.getName() + " doesn't have legal cards, must collect card");
                playerTurn.collectCard(this.loadCard());
                if (!playerTurn.legalCards(table.upsideCard()).isEmpty()) {
                    iu.showPlayer(playerTurn);
                    iu.showLegalCards(playerTurn.legalCards(table.upsideCard()));
                    this.selectCard(playerTurn, playerTurn.legalCards(table.upsideCard()));
                    checkCard();
                }
                else{
                    iu.displayMessage("\n" + playerTurn.getName() + " don't play a card and loses turn");
                }
            }
            
            playerTurn=nextPlayer(playerTurn);
        }while(!endGame); 
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
        iu.displayMessage(player.getName() + " played: " + cardSelected);
    }
    /*
    * Se carga una carta del mazo, si no hay cartas se rellena el mazo con las cartas jugadas menos la ultima que se jugo, se barajea el mazo
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
  
     public Player nextPlayer(Player player) {
        if (player.equals(players.getLast())) {
            return players.getFirst();
        }
        return players.get(players.indexOf(player) + 1);
    }


    public boolean endOfGame(Player player) {
        if (player.checkHand()) {
            iu.displayMessage("\nThe winner is: " + player.getName());
            return true;
        }
        return false;
    }
    
public void checkCard(){
       if(table.checkSpecial()==2){
           drawTwo();
           iu.displayMessage("Player " + playerTurn.getName()+" draws two and losses turn");

       }
       if(table.checkSpecial()==7){
           reverseTurns();
           iu.displayMessage("Turns have been inverted");
       }
       
    }
    /*
    * se avanza el turno y el jugador roba 2 cartas
    */
    public void drawTwo(){
        playerTurn=nextPlayer(playerTurn);
        for(int i = 0; i<2;i++){
        playerTurn.collectCard(this.loadCard());
        }
    }
           
    /*
    * invierte el orden de los turnos
    */
    public void reverseTurns(){
            Stack<Player> temp = new Stack<>();
            for(int i=0;i<players.size();i++){
                temp.push(players.get(i));
            }
            players.clear();
            while(!temp.isEmpty()){
                players.addLast(temp.pop());
            }
            
    }  
}
