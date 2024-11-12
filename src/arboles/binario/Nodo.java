package arboles.binario;

import java.io.Serializable;

/**
 * Clase para el nodo en un árbol binario.
 * @param <T> el tipo de dato que utiliza el nodo
 */
public class Nodo<T> implements Serializable{
    
    protected T clave;
    protected Nodo<T> hijoIzquierdo;
    protected Nodo<T> hijoDerecho;
    protected Nodo<T> padre;

    /**
     * Constructor vacío.
     */
    public Nodo(){

    }

    /**
     * Constructor que crea el nodo con una clave indicada.
     * @param clave el valor que contiene el nodo
     */
    public Nodo(T clave){
        this.clave = clave;
        this.hijoDerecho = this.hijoIzquierdo = this.padre = null;
    }

    /**
     * Establece el nodo hijo izquierdo.
     * @param nodoHijo el nodo que se establece como hijo izquierdo
     */
    public void setHijoIzquierdo(Nodo<T> nodoHijo) {
        this.hijoIzquierdo = nodoHijo;
    }
    
    /**
     * Establece el nodo hijo derecho.
     * @param nodoHijo el nodo que se establece como hijo derecho
     */
    public void setHijoDerecho(Nodo<T> nodoHijo) {
        this.hijoDerecho = nodoHijo;
    }

    /**
     * Establece la clave del nodo.
     * @param clave el valor que se establece a la clave del nodo
     */
    public void setClave(T clave){
        this.clave = clave;
    }

    /**
     * Obtiene el nodo hijo izquierdo.
     * @return el nodo hijo izquierdo
     */
    public Nodo<T> getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    /**
     * Obtiene el nodo hijo derecho.
     * @return el nodo hijo derecho
     */
    public Nodo<T> getHijoDerecho() {
        return hijoDerecho;
    }

    /**
     * Obtiene la clave del nodo.
     * @return el valor de la clave del nodo
     */
    public T getClave(){
        return clave;
    }
    
    /**
     * Obtiene el nodo padre.
     * @return el nodo padre
     */
    public Nodo<T> getPadre(){
        return padre;
    }

    /**
     * Establece el nodo padre.
     * @param nodoPadre el nodo que se establece como padre
     */
    public void setPadre(Nodo<T> nodoPadre){
        this.padre = nodoPadre;
    }

    /**
     * Verifica si el nodo es una hoja.
     * @return true si el nodo es una hoja, false si no lo es
     */
    public boolean isLeaf(){
        return (hijoIzquierdo == null) && (hijoDerecho == null) ? true : false;
    }

    /**
     * Verifica si el nodo tiene ambas posiciones de hijos ocupadas.
     * @return true si el nodo tiene ambos hijos, false si no es así
     */
    public boolean isFull(){
        return (hijoDerecho != null) && (hijoIzquierdo != null) ? true : false;
    }

    /**
     * Obtiene el nodo sucesor del nodo por medio de un recorrido en los hijos.
     * @return el nodo sucesor o null si no existe
     */
    public Nodo<T> getNodoSucesor(){
        if(this.isLeaf()) return null;
        Nodo<T> reemplazo = this.getHijoDerecho();
        if(reemplazo != null){
            while (reemplazo.getHijoIzquierdo() != null) {
                reemplazo = reemplazo.getHijoIzquierdo();
            }
            return reemplazo;
        }else{
            reemplazo = this.getHijoIzquierdo();
            while (reemplazo.getHijoDerecho() != null) {
                reemplazo = reemplazo.getHijoDerecho();
            }
            return reemplazo;
        }
    }

}
