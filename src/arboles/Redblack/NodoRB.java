package arboles.Redblack;

import arboles.binario.Nodo;

/**
 * Clase que representa un nodo de un árbol Red-Black.
 * Extiende la clase Nodo para agregar la funcionalidad de color (Rojo o Negro).
 * @param <T> el tipo de dato que utiliza el nodo
 */
public class NodoRB<T extends Comparable<T>> extends Nodo<T> {
    private boolean esRojo; // true = Rojo, false = Negro

    // Constructor del nodo
    public NodoRB(T clave) {
        super(clave);
        this.esRojo = true; // Los nuevos nodos son rojos por defecto
    }

    // Métodos de color
    public boolean esRojo() {
        return esRojo;
    }

    public void setColorRojo() {
        this.esRojo = true;
    }

    public void setColorNegro() {
        this.esRojo = false;
    }

    public boolean esNegro() {
        return !esRojo;
    }
}




