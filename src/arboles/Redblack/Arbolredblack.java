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

    // Constructor del árbol
    public ArbolRedBlack() {
        super();
    }

    // Método para agregar un nodo
    @Override
    public void add(Nodo<T> nodo) {
        if (nodo == null) return;

        // Si el árbol está vacío, el nodo será la raíz
        if (getRoot() == null) {
            setRoot(nodo);
            ((NodoRB<T>) nodo).setColorNegro();  // La raíz debe ser negra
            return;
        }

        // Realizamos la inserción de un nodo como en un árbol binario de búsqueda normal
        Nodo<T> current = getRoot();
        while (true) {
            int comparacion = nodo.getClave().compareTo(current.getClave());
            if (comparacion == 0) return; // El valor ya está en el árbol, no se añade
            else if (comparacion < 0) {
                if (current.getHijoIzquierdo() == null) {
                    current.setHijoIzquierdo(nodo);
                    nodo.setPadre(current);
                    break;
                } else {
                    current = current.getHijoIzquierdo();
                }
            } else {
                if (current.getHijoDerecho() == null) {
                    current.setHijoDerecho(nodo);
                    nodo.setPadre(current);
                    break;
                } else {
                    current = current.getHijoDerecho();
                }
            }
        }

        // Inicialmente, el nuevo nodo es rojo
        ((NodoRB<T>) nodo).setColorRojo();

        // Ahora debemos reparar el árbol para mantener las propiedades Red-Black
        repararArbol(nodo);
    }

    // Método para reparar el árbol después de una inserción
    private void repararArbol(Nodo<T> nodo) {
        Nodo<T> actual = nodo;
        
        while (actual != getRoot() && ((NodoRB<T>) actual.getPadre()).esRojo()) {
            // Caso 1: El padre es rojo
            if (actual.getPadre() == actual.getPadre().getPadre().getHijoIzquierdo()) {
                Nodo<T> tio = actual.getPadre().getPadre().getHijoDerecho();
                
                // Caso 2a: El tio es rojo (se realiza recoloración)
                if (tio != null && ((NodoRB<T>) tio).esRojo()) {
                    ((NodoRB<T>) actual.getPadre()).setColorNegro();
                    ((NodoRB<T>) tio).setColorNegro();
                    ((NodoRB<T>) actual.getPadre().getPadre()).setColorRojo();
                    actual = actual.getPadre().getPadre();  // Subimos al abuelo
                } else {
                    // Caso 2b: El tio es negro y el nodo está en el lado contrario
                    if (actual == actual.getPadre().getHijoDerecho()) {
                        actual = actual.getPadre();
                        rotarIzquierda(actual);  // Rotación a la izquierda
                    }

                    // Caso 2c: El tio es negro y el nodo está en el lado correcto
                    ((NodoRB<T>) actual.getPadre()).setColorNegro();
                    ((NodoRB<T>) actual.getPadre().getPadre()).setColorRojo();
                    rotarDerecha(actual.getPadre().getPadre());  // Rotación a la derecha
                }
            } else {
                // Simétrico al caso anterior, pero con el padre en el lado derecho
                Nodo<T> tio = actual.getPadre().getPadre().getHijoIzquierdo();
                
                if (tio != null && ((NodoRB<T>) tio).esRojo()) {
                    ((NodoRB<T>) actual.getPadre()).setColorNegro();
                    ((NodoRB<T>) tio).setColorNegro();
                    ((NodoRB<T>) actual.getPadre().getPadre()).setColorRojo();
                    actual = actual.getPadre().getPadre();
                } else {
                    if (actual == actual.getPadre().getHijoIzquierdo()) {
                        actual = actual.getPadre();
                        rotarDerecha(actual);
                    }

                    ((NodoRB<T>) actual.getPadre()).setColorNegro();
                    ((NodoRB<T>) actual.getPadre().getPadre()).setColorRojo();
                    rotarIzquierda(actual.getPadre().getPadre());
                }
            }
        }

        // Aseguramos que la raíz sea siempre negra
        ((NodoRB<T>) getRoot()).setColorNegro();
    }

    // Rotación a la izquierda
    private void rotarIzquierda(Nodo<T> nodo) {
        Nodo<T> derecha = nodo.getHijoDerecho();
        nodo.setHijoDerecho(derecha.getHijoIzquierdo());

        if (derecha.getHijoIzquierdo() != null) {
            derecha.getHijoIzquierdo().setPadre(nodo);
        }

        derecha.setPadre(nodo.getPadre());
        if (nodo.getPadre() == null) {
            setRoot(derecha);
        } else if (nodo == nodo.getPadre().getHijoIzquierdo()) {
            nodo.getPadre().setHijoIzquierdo(derecha);
        } else {
            nodo.getPadre().setHijoDerecho(derecha);
        }

        derecha.setHijoIzquierdo(nodo);
        nodo.setPadre(derecha);
    }

    // Rotación a la derecha
    private void rotarDerecha(Nodo<T> nodo) {
        Nodo<T> izquierda = nodo.getHijoIzquierdo();
        nodo.setHijoIzquierdo(izquierda.getHijoDerecho());

        if (izquierda.getHijoDerecho() != null) {
            izquierda.getHijoDerecho().setPadre(nodo);
        }

        izquierda.setPadre(nodo.getPadre());
        if (nodo.getPadre() == null) {
            setRoot(izquierda);
        } else if (nodo == nodo.getPadre().getHijoDerecho()) {
            nodo.getPadre().setHijoDerecho(izquierda);
        } else {
            nodo.getPadre().setHijoIzquierdo(izquierda);
        }

        izquierda.setHijoDerecho(nodo);
        nodo.setPadre(izquierda);
    }
    public void imprimirPreOrden() {
        recorridoPreOrden(getRoot());
    }

    // Método para imprimir el recorrido InOrden
    public void imprimirInOrden() {
        recorridoInOrden(getRoot());
    }

    // Método para imprimir el recorrido PostOrden
    public void imprimirPostOrden() {
        recorridoPostOrden(getRoot());
    }

    // Recorrido PreOrden (interno)
    private void recorridoPreOrden(Nodo<T> nodo) {
        if (nodo != null) {
            // Imprimir la clave y el color del nodo
            String color = ((NodoRB<T>) nodo).esRojo() ? "Rojo" : "Negro";
            System.out.println("Clave: " + nodo.getClave() + " - Color: " + color);

            // Recorrer el subárbol izquierdo
            recorridoPreOrden(nodo.getHijoIzquierdo());

            // Recorrer el subárbol derecho
            recorridoPreOrden(nodo.getHijoDerecho());
        }
    }

    // Recorrido InOrden (interno)
    private void recorridoInOrden(Nodo<T> nodo) {
        if (nodo != null) {
            // Recorrer el subárbol izquierdo
            recorridoInOrden(nodo.getHijoIzquierdo());

            // Imprimir la clave y el color del nodo
            String color = ((NodoRB<T>) nodo).esRojo() ? "Rojo" : "Negro";
            System.out.println("Clave: " + nodo.getClave() + " - Color: " + color);

            // Recorrer el subárbol derecho
            recorridoInOrden(nodo.getHijoDerecho());
        }
    }

    // Recorrido PostOrden (interno)
    private void recorridoPostOrden(Nodo<T> nodo) {
        if (nodo != null) {
            // Recorrer el subárbol izquierdo
            recorridoPostOrden(nodo.getHijoIzquierdo());

            // Recorrer el subárbol derecho
            recorridoPostOrden(nodo.getHijoDerecho());

            // Imprimir la clave y el color del nodo
            String color = ((NodoRB<T>) nodo).esRojo() ? "Rojo" : "Negro";
            System.out.println("Clave: " + nodo.getClave() + " - Color: " + color);
        }
    }
}



