package Redblack;

public class Arbolredblack extends ArbolBinarioBusqueda{
    public Arbolredblack(){
        super();
    }
    public Arbolredblack(Nodo raiz){
        super(raiz);
    }

    public void insertar(Nodo nodo) {
        // Mientras el nodo padre es rojo, se necesita rebalancear
        añadir(nodo);
        while (nodo != raiz && Nodo.color(nodo.getPadre())) {
            Nodo abuelo = nodo.getPadre().getPadre();
            Nodo tio = Nodo.getTio(nodo);
            if (nodo.getPadre().esHijoIzquierdo()) {
                if (tio != null && tio.esRojo()) { //aso 1: el tío es rojo
                    nodo.getPadre().setColor(false);
                    tio.setColor(false);
                    abuelo.setColor(true);
                    nodo = abuelo;
                } else { // El tío es negro
                    if (!nodo.esHijoIzquierdo()) { //Caso 2: nodo es hijo derecho
                        nodo = nodo.getPadre();
                        rotarIzquierda(nodo);
                    }
                    // Caso 3:nodo es hijo izquierdo
                    nodo.getPadre().setColor(false);
                    abuelo.setColor(true);
                    rotarDerecha(abuelo);
                }
            } else {
                if (tio != null && tio.esRojo()) { //Caso 1: tio rjo
                    nodo.getPadre().setColor(false);
                    tio.setColor(false);
                    abuelo.setColor(true);
                    nodo = abuelo;
                } else { // El tío es negro
                    if (nodo.esHijoIzquierdo()) { // 2: el nodo es hijo izquierdo
                        nodo = nodo.getPadre();
                        rotarDerecha(nodo);
                    }
                    // Caso 3: nodo hijo derecho
                    nodo.getPadre().setColor(false);
                    abuelo.setColor(true);
                    rotarIzquierda(abuelo);
                }
            }
        }
        raiz.setColor(false); //recolorear la raíz siempre de negro
    }

    private void rotarIzquierda(Nodo nodo) {
        Nodo temp = nodo.getDerecha();
        nodo.setDerecha(temp.getIzquierda());

        if (temp.getIzquierda() != null) {
            temp.getIzquierda().setPadre(nodo);
        }
        temp.setPadre(nodo.getPadre());

        if (nodo.getPadre() == null) {
            raiz = temp;
        } else if (nodo.esHijoIzquierdo()) {
            nodo.getPadre().setIzquierda(temp);
        } else {
            nodo.getPadre().setDerecha(temp);
        }

        temp.setIzquierda(nodo);
        nodo.setPadre(temp);
    }

    private void rotarDerecha(Nodo nodo) {
        Nodo temp = nodo.getIzquierda();
        nodo.setIzquierda(temp.getDerecha());

        if (temp.getDerecha() != null) {
            temp.getDerecha().setPadre(nodo);
        }
        temp.setPadre(nodo.getPadre());

        if (nodo.getPadre() == null) {
            raiz = temp;
        } else if (nodo.esHijoIzquierdo()) {
            nodo.getPadre().setIzquierda(temp);
        } else {
            nodo.getPadre().setDerecha(temp);
        }

        temp.setDerecha(nodo);
        nodo.setPadre(temp);
    }

    public static void recorridoPreOrden(Nodo raiz) {
        if(raiz != null){
            System.out.println("Nodo: " + raiz.getDato() + " " + raiz.esRojo());
            if(raiz.getPadre() != null) System.out.println("Padre: " + raiz.getPadre().getDato() );
            if(raiz.getIzquierda() != null) System.out.println("Izquierda: " + raiz.getIzquierda().getDato());
            if(raiz.getDerecha() != null) System.out.println("Derecha: " + raiz.getDerecha().getDato());
            recorridoPreOrden(raiz.getIzquierda());
            recorridoPreOrden(raiz.getDerecha());
        }
    }
    public void eliminar(int valor) {
        Nodo nodoEliminar = buscarNodo(raiz, valor);
        if (nodoEliminar != null) {
            eliminarNodo(nodoEliminar);
        } else {
            System.out.println("Nodo con valor " + valor + " no encontrado en el árbol.");
        }
    }

    private Nodo buscarNodo(Nodo nodo, int valor) {
        while (nodo != null) {
            if (valor < nodo.getDato()) {
                nodo = nodo.getIzquierda();
            } else if (valor > nodo.getDato()) {
                nodo = nodo.getDerecha();
            } else {
                return nodo;
            }
        }
        return null;
    }

    private void eliminarNodo(Nodo nodo) {
        Nodo nodoReemplazo = nodo;
        Nodo nodoFijo;
        boolean nodoReemplazoOriginalColor = nodoReemplazo.esRojo();

        if (nodo.getIzquierda() == null) {
            nodoFijo = nodo.getDerecha();
            reemplazarNodo(nodo, nodo.getDerecha());
        } else if (nodo.getDerecha() == null) {
            nodoFijo = nodo.getIzquierda();
            reemplazarNodo(nodo, nodo.getIzquierda());
        } else {
            nodoReemplazo = minimo(nodo.getDerecha());
            nodoReemplazoOriginalColor = nodoReemplazo.esRojo();
            nodoFijo = nodoReemplazo.getDerecha();

            if (nodoReemplazo.getPadre() == nodo) {
                if (nodoFijo != null) nodoFijo.setPadre(nodoReemplazo);
            } else {
                reemplazarNodo(nodoReemplazo, nodoReemplazo.getDerecha());
                nodoReemplazo.setDerecha(nodo.getDerecha());
                nodoReemplazo.getDerecha().setPadre(nodoReemplazo);
            }

            reemplazarNodo(nodo, nodoReemplazo);
            nodoReemplazo.setIzquierda(nodo.getIzquierda());
            nodoReemplazo.getIzquierda().setPadre(nodoReemplazo);
            nodoReemplazo.setColor(nodo.esRojo());
        }

        if (!nodoReemplazoOriginalColor) {
            balancearDespuesDeEliminar(nodoFijo);
        }
    }

    private void reemplazarNodo(Nodo nodoOriginal, Nodo nodoNuevo) {
        if (nodoOriginal.getPadre() == null) {
            raiz = nodoNuevo; 
        } else if (nodoOriginal == nodoOriginal.getPadre().getIzquierda()) {
            nodoOriginal.getPadre().setIzquierda(nodoNuevo);
        } else {
            nodoOriginal.getPadre().setDerecha(nodoNuevo);
        }

        if (nodoNuevo != null) {
            nodoNuevo.setPadre(nodoOriginal.getPadre());
        }
    }

    private Nodo minimo(Nodo nodo) {
        while (nodo.getIzquierda() != null) {
            nodo = nodo.getIzquierda();
        }
        return nodo;
    }

    private void balancearDespuesDeEliminar(Nodo nodo) {
        while (nodo != raiz && (nodo == null || !nodo.esRojo())) {
            if (nodo == null || nodo.getPadre() == null) {
                break;
            }
    
            if (nodo == nodo.getPadre().getIzquierda()) {
                Nodo hermano = nodo.getPadre().getDerecha();
    
                if (hermano != null && hermano.esRojo()) { 
                    hermano.setColor(false);
                    nodo.getPadre().setColor(true);
                    rotarIzquierda(nodo.getPadre());
                    hermano = nodo.getPadre().getDerecha();
                }
    
                if ((hermano.getIzquierda() == null || !hermano.getIzquierda().esRojo()) &&
                    (hermano.getDerecha() == null || !hermano.getDerecha().esRojo())) {
                    hermano.setColor(true);
                    nodo = nodo.getPadre();
                } else {
                    if (hermano.getDerecha() == null || !hermano.getDerecha().esRojo()) {
                        if (hermano.getIzquierda() != null) hermano.getIzquierda().setColor(false);
                        hermano.setColor(true);
                        rotarDerecha(hermano);
                        hermano = nodo.getPadre().getDerecha();
                    }
                    hermano.setColor(nodo.getPadre().esRojo());
                    nodo.getPadre().setColor(false);
                    if (hermano.getDerecha() != null) hermano.getDerecha().setColor(false);
                    rotarIzquierda(nodo.getPadre());
                    nodo = raiz;
                }
            } else {
                Nodo hermano = nodo.getPadre().getIzquierda();
    
                if (hermano != null && hermano.esRojo()) {
                    hermano.setColor(false);
                    nodo.getPadre().setColor(true);
                    rotarDerecha(nodo.getPadre());
                    hermano = nodo.getPadre().getIzquierda();
                }
    
                if ((hermano.getDerecha() == null || !hermano.getDerecha().esRojo()) &&
                    (hermano.getIzquierda() == null || !hermano.getIzquierda().esRojo())) {
                    hermano.setColor(true);
                    nodo = nodo.getPadre();
                } else {
                    if (hermano.getIzquierda() == null || !hermano.getIzquierda().esRojo()) {
                        if (hermano.getDerecha() != null) hermano.getDerecha().setColor(false);
                        hermano.setColor(true);
                        rotarIzquierda(hermano);
                        hermano = nodo.getPadre().getIzquierda();
                    }
                    hermano.setColor(nodo.getPadre().esRojo());
                    nodo.getPadre().setColor(false);
                    if (hermano.getIzquierda() != null) hermano.getIzquierda().setColor(false);
                    rotarDerecha(nodo.getPadre());
                    nodo = raiz;
                }
            }
        }
        if (nodo != null) nodo.setColor(false);
    }
    
}
