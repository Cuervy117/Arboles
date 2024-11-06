package principal;
import AVL.*;
/**
 *
 * @author David
 */
public class main {
    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL(new Node(20));
        arbol.add(new Node(10));
        arbol.add(new Node(15));
        ArbolAVL.preOrder(arbol.getRoot());
        arbol.simple_left_rotation(arbol.getRoot().getLeft_child());
        arbol.simple_right_rotation(arbol.getRoot());
        System.out.println("Rotacion hecha");
        ArbolAVL.preOrder(arbol.getRoot());
    }
}
