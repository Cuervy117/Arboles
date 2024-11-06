package principal;
import AVL.*;
/**
 *
 * @author David
 */
public class main {
    public static void main(String[] args) {
        Node node1 = new Node(12);
        Node node2 = new Node(10);
        Node node3 = new Node(11);
        Node node4 = new Node(20);
        Node node5 = new Node(24);
        Node node6 = new Node(23);
        Node node7 = new Node(26);
        Node node8 = new Node(22);
        Node node9 = new Node(21);
        ArbolAVL arbol = new ArbolAVL(node1);
        arbol.add(node2);
        arbol.add(node3);
        arbol.add(node4);
        arbol.add(node5);
        arbol.add(node6);
        arbol.add(node7);
        arbol.add(node8);
        arbol.add(node9);
        ArbolAVL.preOrder(arbol.getRoot());
        arbol.simple_right_rotation(node6);
        System.out.println("Rotacion hecha");
        ArbolAVL.preOrder(arbol.getRoot());

    }
}
