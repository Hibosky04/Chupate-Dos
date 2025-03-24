package gal.uvigo.esei.aed1.chupatedos.core;
import java.util.Stack;

public class Table {

    private Stack<Card>[] tableCard;

    public Table(Stack<Card>[] tableCard) {
        this.tableCard = tableCard;
    }
    
    /**
     * Añade una carta a cada fila de la mesa
     *
     * @param c
     */
    public void FirstCard(Card[] c) {
    }
    
    /**
     * Coloca la carta en la fila asignada
     *
     * @param c
     */
    public void PutCard(Card c) {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cartas en la Mesa: ");
        sb.append(tableCard);
        sb.append("\n");
        return sb.toString();
    }

}
