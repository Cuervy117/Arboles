package arboles.heap;

import arboles.binario.ArbolBinario;
import arboles.binario.Nodo;

public class Heap<T extends Comparable<T>> extends ArbolBinario<T>{
    
    public Heap(){
        super();
    }

    public Heap(Nodo<T> nodo){
        super(nodo);
    }

    public Heap(T clave){
        super(clave);
    }

    @Override
    public void add(Nodo<T> nodo){
        super.add(nodo);
        minHeapifyUp(nodo);
    }

    @Override
    public void delete(Nodo<T> nodo){
        super.delete(nodo);

    }

    private void minHeapifyUp(Nodo<T> nodo) {
        Nodo<T> padre = nodo.getPadre();
        while(padre != null && padre.getClave().compareTo(nodo.getClave()) > 0){
            nodeSwap(nodo, padre);
            padre = nodo.getPadre();
        }
    }

    private void minHeapify(Nodo<T> r){
        if(r == null) return;
        if(r.getHijoIzquierdo() != null){
            minHeapify(r.getHijoIzquierdo());
        }
        if(r.getHijoDerecho() != null) {
            minHeapify(r.getHijoDerecho());
        }
        Nodo<T> der = r.getHijoDerecho();
        Nodo<T> izq = r.getHijoIzquierdo();
        Nodo<T> pequeño = r;

        if(izq != null && izq.getClave().compareTo(pequeño.getClave()) < 0) {
            pequeño = izq;  
        }
        if(der != null && der.getClave().compareTo(pequeño.getClave()) < 0) {
            pequeño = der;
        }
        if(pequeño != r){
            nodeSwap(r, pequeño);
        }
    }

}
