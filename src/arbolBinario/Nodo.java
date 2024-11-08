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

    public void setClave(T clave){
        this.clave = clave;
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

    public boolean isLeaf(){
        return (hijoIzquierdo == null) && (hijoDerecho == null) ? true : false;
    }

    public Nodo<T> getReemplazo(){
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
