package arboles.Redblack;

import arboles.binario.Nodo;

/**
 * Clase que representa un nodo de un árbol Red-Black.
 * Esta clase extiende de {@link Nodo} para agregar la funcionalidad de color (Rojo o Negro) 
 * y otros métodos específicos para el árbol Red-Black.
 * 
 * <p>Un nodo de tipo {@code NodoRB} tiene una clave y un color, que puede ser rojo o negro.
 * El color rojo es el valor predeterminado para los nuevos nodos.</p>
 * 
 * @param <T> el tipo de dato que utiliza el nodo. Debe ser comparable para poder ordenar los elementos en el árbol.
 */
public class NodoRB<T extends Comparable<T>> extends Nodo<T> {
    
    /**
     * Atributo que define si el nodo es rojo o negro. 
     * {@code true} significa que el nodo es rojo, y {@code false} significa que el nodo es negro.
     */
    private boolean esRojo; // true = Rojo, false = Negro

    /**
     * Constructor del nodo que inicializa la clave del nodo y establece el color del nodo a rojo por defecto.
     * 
     * @param clave la clave que contiene el nodo. Se utiliza para la comparación en el árbol.
     */
    public NodoRB(T clave) {
        super(clave);
        this.esRojo = true; // Los nuevos nodos son rojos por defecto
    }

    /**
     * Método que obtiene el estado del color del nodo.
     * 
     * @return {@code true} si el nodo es rojo, {@code false} si el nodo es negro.
     */
    public boolean esRojo() {
        return esRojo;
    }

    /**
     * Establece el color del nodo como rojo.
     */
    public void setColorRojo() {
        this.esRojo = true;
    }

    /**
     * Establece el color del nodo como negro.
     */
    public void setColorNegro() {
        this.esRojo = false;
    }

    /**
     * Método que determina si el nodo es negro.
     * 
     * @return {@code true} si el nodo es negro, {@code false} si el nodo es rojo.
     */
    public boolean esNegro() {
        return !esRojo;
    }

    /**
     * Método que obtiene el sucesor en el árbol Red-Black del nodo actual.
     * Si el nodo tiene un hijo derecho, el sucesor será el nodo más a la izquierda en su subárbol derecho.
     * Si no tiene hijo derecho, el sucesor será el primer ancestro cuya clave sea mayor que la del nodo.
     * 
     * @return el nodo sucesor, o {@code null} si no existe un sucesor.
     */
    public Nodo<T> getNodoSucesor() {
        // Si tiene un hijo derecho, el sucesor es el nodo más a la izquierda en el subárbol derecho
        if (this.getHijoDerecho() != null) {
            Nodo<T> nodo = this.getHijoDerecho();
            while (nodo.getHijoIzquierdo() != null) {
                nodo = nodo.getHijoIzquierdo();
            }
            return nodo;
        }
        // Si no tiene hijo derecho, el sucesor es el primer ancestro cuya clave es mayor que la del nodo
        Nodo<T> nodo = this;
        while (nodo.getPadre() != null && nodo == nodo.getPadre().getHijoDerecho()) {
            nodo = nodo.getPadre();
        }
        return nodo.getPadre();
    }

    /**
     * Este método {@link setColor} no está soportado en la clase {@code NodoRB}. 
     * Si se intenta usar este método, se lanzará una excepción {@link UnsupportedOperationException}.
     * 
     * @param esRojo El valor que se intenta asignar al color del nodo.
     * @throws UnsupportedOperationException siempre, ya que este método no está soportado.
     */
    void setColor(boolean esRojo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}




