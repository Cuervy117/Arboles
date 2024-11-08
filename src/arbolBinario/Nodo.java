package arbolBinario;

import java.io.Serializable;

public class Nodo<T> implements Serializable{
    
    protected T clave;
    protected Nodo<T> hijoIzquierdo;
    protected Nodo<T> hijoDerecho;
    protected Nodo<T> padre;

    public Nodo(){

    }

    public Nodo(T clave){
        this.clave = clave;
        this.hijoDerecho = this.hijoIzquierdo = this.padre = null;
    }

    public void setHijoIzquierdo(Nodo<T> nodoHijo) {
        this.hijoIzquierdo = nodoHijo;
    }
    
    public void setHijoDerecho(Nodo<T> nodoHijo) {
        this.hijoDerecho = nodoHijo;
    }

    public void setValor(T valor){
        this.clave = valor;
    }

    public Nodo<T> getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public Nodo<T> getHijoDerecho() {
        return hijoDerecho;
    }

    public T getClave(){
        return clave;
    }
    
    public Nodo<T> getPadre(){
        return padre;
    }

    public void setPadre(Nodo<T> nodoPadre){
        this.padre = nodoPadre;
    }
}
