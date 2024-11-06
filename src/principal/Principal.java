package principal;
import AVL.*;
/**
 *
 * @author David
 */
public class Principal {
    public static void main(String[] args) {
        Node node1 = new Node(20);
        Node node2 = new Node(15);
        Node node3 = new Node(10);
        Node node4 = new Node(12);
        Node node5 = new Node(25);
        Node node6 = new Node(30);
        Node node7 = new Node(28);
        ArbolAVL arbol = new ArbolAVL(node1);
        arbol.add(node2);
        arbol.add(node3);
        arbol.add(node4);
        arbol.add(node5);
        arbol.add(node6);
        arbol.add(node7);
        ArbolAVL.preOrder(arbol.getRoot());
    }
}
