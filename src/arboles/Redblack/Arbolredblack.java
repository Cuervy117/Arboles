package arboles.Redblack;

import arboles.binario.Nodo;
import arboles.deBusqueda.ABB;

/**
 * Clase que representa un árbol Red-Black, con métodos para insertar y balancear el árbol.
 * 
 * Extiende de un árbol binario de búsqueda. 
 * @param <T> El tipo de dato que contienen los nodos del árbol, debe implementar la interfaz Comparable.
 */

public class ArbolRedBlack<T extends Comparable<T>> extends ABB<T> {

    public ArbolRedBlack() {
        super();
    }

    @Override
    public void add(Nodo<T> nodo) {
        // Inserta el nodo como un nodo normal en un árbol binario
        super.add(nodo);

        // Después de la inserción, realizamos la reestructuración para balancear el árbol Red-Black
        reestructurar((NodoRB<T>) nodo);
    }

    private void reestructurar(NodoRB<T> nodo) {
        // Aquí realizamos las rotaciones necesarias para balancear el árbol Red-Black
        NodoRB<T> actual = nodo;
        
        while (actual != root && actual.getPadre().getColor() == Color.ROJO) {
            NodoRB<T> padre = (NodoRB<T>) actual.getPadre();
            NodoRB<T> abuelo = (NodoRB<T>) padre.getPadre();

            if (padre == abuelo.getHijoIzquierdo()) {
                NodoRB<T> tio = (NodoRB<T>) abuelo.getHijoDerecho();
                if (tio != null && tio.getColor() == Color.ROJO) {
                    // Caso 1: El tío es rojo, simplemente cambiamos los colores
                    padre.setColor(Color.NEGRO);
                    tio.setColor(Color.NEGRO);
                    abuelo.setColor(Color.ROJO);
                    actual = abuelo;
                } else {
                    // Caso 2: El tío es negro o nulo
                    if (actual == padre.getHijoDerecho()) {
                        // Caso 2a: El hijo es derecho, realizamos una rotación a la izquierda
                        actual = padre;
                        rotacionIzquierda(actual);
                    }
                    // Caso 2b: El hijo es izquierdo, realizamos rotación a la derecha
                    padre.setColor(Color.NEGRO);
                    abuelo.setColor(Color.ROJO);
                    rotacionDerecha(abuelo);
                }
            } else {
                // Caso simétrico, padre es el hijo derecho del abuelo
                NodoRB<T> tio = (NodoRB<T>) abuelo.getHijoIzquierdo();
                if (tio != null && tio.getColor() == Color.ROJO) {
                    // Caso 1: El tío es rojo, simplemente cambiamos los colores
                    padre.setColor(Color.NEGRO);
                    tio.setColor(Color.NEGRO);
                    abuelo.setColor(Color.ROJO);
                    actual = abuelo;
                } else {
                    // Caso 2: El tío es negro o nulo
                    if (actual == padre.getHijoIzquierdo()) {
                        // Caso 2a: El hijo es izquierdo, realizamos una rotación a la derecha
                        actual = padre;
                        rotacionDerecha(actual);
                    }
                    // Caso 2b: El hijo es derecho, realizamos rotación a la izquierda
                    padre.setColor(Color.NEGRO);
                    abuelo.setColor(Color.ROJO);
                    rotacionIzquierda(abuelo);
                }
            }
        }
        root.setColor(Color.NEGRO);
    }

    private void rotacionIzquierda(NodoRB<T> nodo) {
        NodoRB<T> hijoDerecho = (NodoRB<T>) nodo.getHijoDerecho();
        nodo.setHijoDerecho(hijoDerecho.getHijoIzquierdo());
        if (hijoDerecho.getHijoIzquierdo() != null) {
            hijoDerecho.getHijoIzquierdo().setPadre(nodo);
        }
        hijoDerecho.setPadre(nodo.getPadre());
        if (nodo.getPadre() == null) {
            root = hijoDerecho;
        } else if (nodo == nodo.getPadre().getHijoIzquierdo()) {
            nodo.getPadre().setHijoIzquierdo(hijoDerecho);
        } else {
            nodo.getPadre().setHijoDerecho(hijoDerecho);
        }
        hijoDerecho.setHijoIzquierdo(nodo);
        nodo.setPadre(hijoDerecho);
    }

    private void rotacionDerecha(NodoRB<T> nodo) {
        NodoRB<T> hijoIzquierdo = (NodoRB<T>) nodo.getHijoIzquierdo();
        nodo.setHijoIzquierdo(hijoIzquierdo.getHijoDerecho());
        if (hijoIzquierdo.getHijoDerecho() != null) {
            hijoIzquierdo.getHijoDerecho().setPadre(nodo);
        }
        hijoIzquierdo.setPadre(nodo.getPadre());
        if (nodo.getPadre() == null) {
            root = hijoIzquierdo;
        } else if (nodo == nodo.getPadre().getHijoDerecho()) {
            nodo.getPadre().setHijoDerecho(hijoIzquierdo);
        } else {
            nodo.getPadre().setHijoIzquierdo(hijoIzquierdo);
        }
        hijoIzquierdo.setHijoDerecho(nodo);
        nodo.setPadre(hijoIzquierdo);
    }
}

