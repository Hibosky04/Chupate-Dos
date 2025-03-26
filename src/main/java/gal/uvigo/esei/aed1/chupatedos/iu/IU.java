package gal.uvigo.esei.aed1.chupatedos.iu;

import gal.uvigo.esei.aed1.chupatedos.core.Card;
import gal.uvigo.esei.aed1.chupatedos.core.DeckOfCards;
import gal.uvigo.esei.aed1.chupatedos.core.Player;
import gal.uvigo.esei.aed1.chupatedos.core.Suit;
import gal.uvigo.esei.aed1.chupatedos.core.Table;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class IU {

    private final Scanner keyboard;

    public IU() {
        keyboard = new Scanner(System.in);
    }

    /**
     * Lee un num. de teclado
     *
     * @param msg El mensaje a visualizar.
     * @return El num., como entero
     */
    public int readNumber(String msg) {
        boolean repeat;
        int toret = 0;

        do {
            repeat = false;
            System.out.print(msg);
            try {
                toret = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException exc) {
                repeat = true;
            }
        } while (repeat);

        return toret;
    }

    /**
     * Lee un string de teclado
     *
     * @param msg mensaje a mostrar antes de la lectura
     * @return el string leido
     */
    public String readString(String msg) {
        String toret;
        System.out.print(msg);
        toret = keyboard.nextLine();
        return toret;
    }

    /**
     * muestra un mensaje por pantalla
     *
     * @param msg el mensaje a mostrar
     */
    public void displayMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Solicita los nombres de los jugadores por teclado y los almacena en una
     * estructura de datos
     *
     * @return Los datos de los jugadores almacenados en la estructura de datos
     * correspondiente
     */
    private int numberPlayers() {
        int numPlayers = 0;
        do {
            numPlayers = readNumber("Insert number of players: ");
        } while (numPlayers < 2 || numPlayers > 5);
        return numPlayers;
    }

    public List<String> askPlayersName() {
        List<String> name = new ArrayList();
        int numPlayers = numberPlayers();
        for (int i = 0; i < numPlayers; i++) {
            String Name = readString("Insert name of the player: ");
            name.add(Name);
        }
        return name;
    }

     /**
     * Muestra por pantalla los datos de un jugador
     *
     * @param player Jugador para el cual se mostrarán los datos por pantalla
     */
    public void showPlayer(Player player) {
        System.out.println(player.toString());
    }
    
    /**
     * Muestra por pantalla los datos de una coleccion de jugadores
     *
     * @param players Jugadores cuyos datos se mostrarán por pantalla
     */
    public void showPlayers(List<Player> players) {
        for (Player temp : players) {
            showPlayer(temp);
        }
    }
    
    /**
     * Pide el número de carta que el jugador desea echar
     * @param player
     * @return numero de posicion de la carta que se desea jugar
     */
    public int pedirNumCarta(Player player) {
        int num = 0;
        do {
            showPlayer(player);
            num = readNumber("Insert " + player.getName() + " the number of the card selected: ");
        } while (num < 0 || num > player.getHand().size());
        return num;
    }
    
    /**
     * Muestra las cartas seleccionadas por los jugadores
     * @param list 
     */
    public void showSelectedCards(List<Card> list) {
        System.out.println(list);

    }
    /**
     * Muestra las cartas que hay en la mesa
     * @param table 
     */
    public void mostrarMesa(Table table) {
        System.out.println(table);
    }

    /**
     * muestra la carta seleccionada por un jugador
     * @param player 
     */
    public void showSelectedCard(Player player) {
        System.out.println(player.getName() + " Your card selected is: " + player.getHiddenCard());
    }
    
    /**
     * Muestra de que jugador es el turno
     * @param name 
     */

    public void showPlayerTurn(String name) {
        System.out.println("The turn is: " + name);
    }

    


    

    
}
