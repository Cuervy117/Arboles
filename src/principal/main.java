package principal;
import AVL.*;
/**
 *
 * @author David
 */
public class main {
    public static void main(String[] args) {
        Node nodo1 = new Node.NodeBuilder().set_key(12).build();
        Node nodo2 = new Node.NodeBuilder().set_key(11).build();
        Node nodo3 = new Node.NodeBuilder().set_key(23).build();
        Node nodo4 = new Node.NodeBuilder().set_key(54).build();
        Node nodo5 = new Node.NodeBuilder().set_key(4354).build();
        Node nodo6 = new Node.NodeBuilder().set_key(167).build();
        Node nodo7 = new Node.NodeBuilder().set_key(2).build();
        
        ArbolAVL arbol = new ArbolAVL(nodo1);
        arbol.insert(nodo2);
        arbol.insert(nodo3);
        arbol.insert(nodo4);
        arbol.insert(nodo5);
        arbol.insert(nodo6);
        arbol.insert(nodo7);
        
        ArbolAVL.preOrder(arbol.getRoot());
    }
}
