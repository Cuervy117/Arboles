package arboles.Redblack;

import arboles.binario.Nodo;

/**
 * Clase que representa un nodo de un árbol Red-Black.
 * Extiende la clase Nodo para agregar la funcionalidad de color (Rojo o Negro).
 * @param <T> el tipo de dato que utiliza el nodo
 */
public class NodoRB<T extends Comparable<T>> extends Nodo<T> {

    private static final boolean ROJO = false;
    private static final boolean NEGRO = true;

    private boolean color;  // El color del nodo: ROJO o NEGRO

    /**
     * Constructor para crear un nodo Red-Black con la clave proporcionada.
     * Inicialmente, el nodo es rojo.
     * @param clave la clave del nodo
     */
    public NodoRB(T clave) {
        super(clave);
        this.color = ROJO;  // Los nodos se insertan inicialmente como rojos
        this.padre = null;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }

    /**
     * Verifica si el nodo es rojo.
     * @return true si el nodo es rojo, false si es negro
     */
    public boolean esRojo() {
        return this.color == ROJO;
    }

    /**
     * Verifica si el nodo es negro.
     * @return true si el nodo es negro, false si es rojo
     */
    public boolean esNegro() {
        return this.color == NEGRO;
    }

    /**
     * Establece el color del nodo a rojo.
     */
    public void setColorRojo() {
        this.color = ROJO;
    }

    /**
     * Establece el color del nodo a negro.
     */
    public void setColorNegro() {
        this.color = NEGRO;
    }

    /**
     * Obtiene el color del nodo.
     * @return el color del nodo (Rojo o Negro)
     */
    public boolean getColor() {
        return this.color;
    }

    /**
     * Establece el color del nodo.
     * @param color el color a establecer (Rojo o Negro)
     */
    public void setColor(boolean color) {
        this.color = color;
    }

}



