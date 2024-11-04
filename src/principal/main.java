package principal;
import AVL.*;
/**
 *
 * @author David
 */
public class main {
    public static void main(String[] args) {
        Node nodo1 = new Node.NodeBuilder().set_key(12).build();
        System.out.println(nodo1.getClave());
    }
}
