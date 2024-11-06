package principal;
import AVL.*;
/**
 *
 * @author David
 */
public class main {
    public static void main(String[] args) {
        Node node1 = new Node(20);
        ArbolAVL arbol = new ArbolAVL(new Node(20));

        System.out.println("Rotacion hecha");
        ArbolAVL.preOrder(arbol.getRoot());
    }
}
