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

       /**
     * Constructor del árbol Red-Black.
     * Inicializa el árbol llamando al constructor de la clase base {@link ABB}.
     */
    public ArbolRedBlack() {
        super();
    }

    /**
     * Agrega un nodo al árbol Red-Black. Si el árbol está vacío, el nuevo nodo será la raíz y será de color negro.
     * Si el árbol no está vacío, se realiza una inserción binaria y el nodo es insertado como rojo.
     * Luego, se llama al método {@link #repararArbol(Nodo)} para asegurarse de que el árbol mantenga sus propiedades Red-Black.
     *
     * @param nodo El nodo a agregar al árbol.
     */
    @Override
    public void add(Nodo<T> nodo) {
        if (nodo == null) return;

        if (getRoot() == null) {
            setRoot(nodo);
            ((NodoRB<T>) nodo).setColorNegro();  // La raíz debe ser negra
            return;
        }

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

    /**
     * Repara el árbol después de la inserción de un nodo para mantener las propiedades Red-Black.
     * Este método realiza rotaciones y recoloraciones según los casos definidos por las reglas Red-Black.
     *
     * @param nodo El nodo que se acaba de insertar y que necesita la reparación.
     */
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

    /**
     * Imprime el recorrido PreOrden del árbol Red-Black.
     * En el recorrido PreOrden, el nodo actual se imprime antes de recorrer sus hijos.
     */
    public void imprimirPreOrden() {
        recorridoPreOrden(getRoot());
    }

    /**
     * Imprime el recorrido InOrden del árbol Red-Black.
     * En el recorrido InOrden, se recorren primero los subárboles izquierdos, luego el nodo actual y después los subárboles derechos.
     */
    public void imprimirInOrden() {
        recorridoInOrden(getRoot());
    }

    /**
     * Imprime el recorrido PostOrden del árbol Red-Black.
     * En el recorrido PostOrden, se recorren primero los subárboles izquierdo y derecho, y finalmente el nodo actual.
     */
    public void imprimirPostOrden() {
        recorridoPostOrden(getRoot());
    }

    /**
     * Realiza el recorrido PreOrden de manera recursiva.
     * Se imprime la clave y el color de cada nodo visitado.
     *
     * @param nodo El nodo actual del recorrido.
     */
    private void recorridoPreOrden(Nodo<T> nodo) {
        if (nodo != null) {
            String color = ((NodoRB<T>) nodo).esRojo() ? "Rojo" : "Negro";
            System.out.println("Clave: " + nodo.getClave() + " - Color: " + color);

            recorridoPreOrden(nodo.getHijoIzquierdo());
            recorridoPreOrden(nodo.getHijoDerecho());
        }
    }

    /**
     * Realiza el recorrido InOrden de manera recursiva.
     * Se visita primero el subárbol izquierdo, luego el nodo actual y finalmente el subárbol derecho.
     *
     * @param nodo El nodo actual del recorrido.
     */
    private void recorridoInOrden(Nodo<T> nodo) {
        if (nodo != null) {
            recorridoInOrden(nodo.getHijoIzquierdo());

            String color = ((NodoRB<T>) nodo).esRojo() ? "Rojo" : "Negro";
            System.out.println("Clave: " + nodo.getClave() + " - Color: " + color);

            recorridoInOrden(nodo.getHijoDerecho());
        }
    }

    /**
     * Realiza el recorrido PostOrden de manera recursiva.
     * Se visitan primero los subárboles izquierdo y derecho, y finalmente el nodo actual.
     *
     * @param nodo El nodo actual del recorrido.
     */
    private void recorridoPostOrden(Nodo<T> nodo) {
        if (nodo != null) {
            recorridoPostOrden(nodo.getHijoIzquierdo());
            recorridoPostOrden(nodo.getHijoDerecho());

            String color = ((NodoRB<T>) nodo).esRojo() ? "Rojo" : "Negro";
            System.out.println("Clave: " + nodo.getClave() + " - Color: " + color);
        }
    }

    /**
     * Elimina un nodo con el valor especificado del árbol Red-Black.
     * Si el nodo no existe, muestra un mensaje de error.
     * Si el nodo es rojo, se elimina sin realizar reequilibración.
     * Si el nodo es negro, se realiza una reequilibración.
     *
     * @param valor El valor del nodo a eliminar.
     */
    public void eliminar(T valor) {
        NodoRB<T> nodoEliminar = buscarNodo((NodoRB<T>) root, valor);
        if (nodoEliminar == null) {
            System.out.println("Error: El nodo con dicho valor no existe.");
            return;
        }
        eliminarNodo(nodoEliminar);
    }

    /**
     * Busca el nodo con el valor especificado.
     *
     * @param nodo El nodo actual de búsqueda.
     * @param valor El valor a buscar.
     * @return El nodo encontrado o null si no existe.
     */
    private NodoRB<T> buscarNodo(NodoRB<T> nodo, T valor) {
        if (nodo == null) {
            return null;
        }
        if (valor.compareTo(nodo.getClave()) < 0) {
            return buscarNodo((NodoRB<T>) nodo.getHijoIzquierdo(), valor);
        } else if (valor.compareTo(nodo.getClave()) > 0) {
            return buscarNodo((NodoRB<T>) nodo.getHijoDerecho(), valor);
        }
        return nodo;
    }
/**
 * Elimina un nodo del árbol Red-Black.
 * 
 * @param nodo El nodo a eliminar.
 */
private void eliminarNodo(NodoRB<T> nodo) {
    NodoRB<T> nodoSustituto;
    if (nodo.getHijoIzquierdo() == null || nodo.getHijoDerecho() == null) {
        nodoSustituto = nodo;
    } else {
        nodoSustituto = obtenerMinimo((NodoRB<T>) nodo.getHijoDerecho());
    }

    NodoRB<T> hijo = (nodoSustituto.getHijoIzquierdo() != null) ? (NodoRB<T>) nodoSustituto.getHijoIzquierdo() : (NodoRB<T>) nodoSustituto.getHijoDerecho();
    if (hijo != null) {
        hijo.setPadre(nodoSustituto.getPadre());
    }

    if (nodoSustituto.getPadre() == null) {
        root = hijo;
    } else if (nodoSustituto == nodoSustituto.getPadre().getHijoIzquierdo()) {
        nodoSustituto.getPadre().setHijoIzquierdo(hijo);
    } else {
        nodoSustituto.getPadre().setHijoDerecho(hijo);
    }

    if (nodoSustituto != nodo) {
        nodo.setClave(nodoSustituto.getClave()); // Usamos setClave() para cambiar el valor
    }

    if (!nodoSustituto.esRojo()) {
        rebalancearEliminacion(hijo);
    }
}

/**
 * Obtiene el nodo con el valor mínimo a partir del nodo dado.
 * 
 * @param nodo El nodo desde el cual comenzar la búsqueda del mínimo.
 * @return El nodo con el valor mínimo encontrado.
 */
private NodoRB<T> obtenerMinimo(NodoRB<T> nodo) {
    while (nodo.getHijoIzquierdo() != null) {
        nodo = (NodoRB<T>) nodo.getHijoIzquierdo();
    }
    return nodo;
}

/**
 * Rebalancea el árbol después de la eliminación de un nodo.
 * Este proceso asegura que las propiedades del árbol Red-Black se mantengan.
 * 
 * @param nodo El nodo desde el cual comenzar el rebalanceo.
 */
private void rebalancearEliminacion(NodoRB<T> nodo) {
    while (nodo != root && (nodo == null || !nodo.esRojo())) {
        if (nodo == nodo.getPadre().getHijoIzquierdo()) {
            NodoRB<T> hermano = (NodoRB<T>) nodo.getPadre().getHijoDerecho();
            if (hermano.esRojo()) {
                hermano.setColorNegro();
                ((NodoRB<T>) nodo.getPadre()).setColorRojo();
                rotarIzquierda(nodo.getPadre());
                hermano = (NodoRB<T>) nodo.getPadre().getHijoDerecho();
            }
            if (hermano.getHijoIzquierdo() == null || !hermano.getHijoIzquierdo().esRojo()) {
                if (hermano.getHijoDerecho() == null || !hermano.getHijoDerecho().esRojo()) {
                    hermano.setColorRojo();
                    nodo = (NodoRB<T>) nodo.getPadre();
                } else {
                    ((NodoRB<T>) hermano.getHijoDerecho()).setColorNegro();
                    hermano.setColorRojo();
                    rotarIzquierda(hermano);
                    hermano = (NodoRB<T>) nodo.getPadre().getHijoDerecho();
                }
            }
        } else {
            // Caso espejo para el hermano izquierdo
            NodoRB<T> hermano = (NodoRB<T>) nodo.getPadre().getHijoIzquierdo();
            if (hermano.esRojo()) {
                hermano.setColorNegro();
                ((NodoRB<T>) nodo.getPadre()).setColorRojo();
                rotarDerecha(nodo.getPadre());
                hermano = (NodoRB<T>) nodo.getPadre().getHijoIzquierdo();
            }
            if (hermano.getHijoDerecho() == null || !((NodoRB<T>) hermano.getHijoDerecho()).esRojo()) {
                if (hermano.getHijoIzquierdo() == null || !((NodoRB<T>) hermano.getHijoIzquierdo()).esRojo()) {
                    hermano.setColorRojo();
                    nodo = (NodoRB<T>) nodo.getPadre();
                } else {
                    ((NodoRB<T>) hermano.getHijoIzquierdo()).setColorNegro();
                    hermano.setColorRojo();
                    rotarDerecha(hermano);
                    hermano = (NodoRB<T>) nodo.getPadre().getHijoIzquierdo();
                }
            }
        }
    }
    nodo.setColorNegro();
}

/**
 * Verifica que las propiedades del árbol Red-Black se mantengan en todos los nodos.
 * Lanza una excepción si se encuentra un nodo rojo con un hijo rojo.
 * 
 * @param nodo El nodo a verificar.
 */
private void verificarPropiedadesRedBlack(Nodo<T> nodo) {
    if (nodo == null) return;

    if (((NodoRB<T>) nodo).esRojo()) {  // Casteo
        if (((NodoRB<T>) nodo.getHijoIzquierdo()) != null && ((NodoRB<T>) nodo.getHijoIzquierdo()).esRojo()) {  // Casteo
            System.out.println("Error: Nodo rojo (" + nodo.getClave() + ") tiene un hijo izquierdo rojo (" + nodo.getHijoIzquierdo().getClave() + ")");
            throw new IllegalStateException("Un nodo rojo no puede tener un hijo rojo");
        }
        if (((NodoRB<T>) nodo.getHijoDerecho()) != null && ((NodoRB<T>) nodo.getHijoDerecho()).esRojo()) {  // Casteo
            System.out.println("Error: Nodo rojo (" + nodo.getClave() + ") tiene un hijo derecho rojo (" + nodo.getHijoDerecho().getClave() + ")");
            throw new IllegalStateException("Un nodo rojo no puede tener un hijo rojo");
        }
    }

    verificarPropiedadesRedBlack(nodo.getHijoIzquierdo());
    verificarPropiedadesRedBlack(nodo.getHijoDerecho());
}

/**
 * Realiza una rotación a la izquierda en un nodo.
 * 
 * @param nodo El nodo sobre el cual se realizará la rotación.
 */
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

/**
 * Realiza una rotación a la derecha en un nodo.
 * 
 * @param nodo El nodo sobre el cual se realizará la rotación.
 */
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

/**
 * Verifica si el árbol Red-Black está balanceado en términos de la altura negra.
 * 
 * @return true si el árbol está balanceado en altura negra, false en caso contrario.
 */
public boolean esBalanceadoEnAlturaNegra() {
    // Iniciar la verificación desde la raíz y un contador de nodos negros
    return esBalanceadoEnAlturaNegraRecursivo((NodoRB<T>) getRoot(), 0, -1);
}

/**
 * Método recursivo para verificar el balanceo del árbol Red-Black en términos de la altura negra.
 * 
 * @param nodo El nodo actual en la verificación.
 * @param contadorNegro El contador de nodos negros hasta el momento.
 * @param alturaNegraEsperada La altura negra esperada.
 * @return true si el árbol está balanceado en altura negra, false en caso contrario.
 */
private boolean esBalanceadoEnAlturaNegraRecursivo(NodoRB<T> nodo, int contadorNegro, int alturaNegraEsperada) {
    if (nodo == null) {
        if (alturaNegraEsperada == -1) {
            alturaNegraEsperada = contadorNegro;
        }
        return contadorNegro == alturaNegraEsperada;
    }

    int nuevoContadorNegro = contadorNegro + (nodo.esNegro() ? 1 : 0);
    return esBalanceadoEnAlturaNegraRecursivo((NodoRB<T>) nodo.getHijoIzquierdo(), nuevoContadorNegro, alturaNegraEsperada)
            && esBalanceadoEnAlturaNegraRecursivo((NodoRB<T>) nodo.getHijoDerecho(), nuevoContadorNegro, alturaNegraEsperada);
}

    
}



