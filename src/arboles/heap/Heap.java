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
    public void add(T clave){
        super.add(clave);
        Nodo<T> nodo;
        try {
            nodo = search(clave);     
        } catch (Exception e) {
            System.out.println("No fue posible agregar el nodo");
            return;
        }
        while (nodo.getPadre() != null && nodo.getClave().compareTo(nodo.getPadre().getClave()) < 0) {
            nodeSwapHeap(nodo.getPadre(), nodo);
        }
    }

    @Override
    public void delete(T clave){
        super.delete(clave);
        minHeapify(root);
    }

    private void minHeapify(Nodo<T> r){
        if(r == null || r.isLeaf()  ) return;
        
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
            nodeSwapHeap(r, pequeño);
            minHeapify(r);
        }
    }

    
    public void nodeSwapHeap(Nodo<T> origen, Nodo<T> objetivo){
        Nodo<T> auxDerecho = origen.getHijoDerecho();
        Nodo<T> auxIzquierdo = origen.getHijoIzquierdo();
        Nodo<T> auxPadre = origen.getPadre();
        
        origen.setHijoDerecho(objetivo.getHijoDerecho());
        origen.setHijoIzquierdo(objetivo.getHijoIzquierdo());

        actualizarPadre(objetivo, origen);

        actualizarPadre(origen, objetivo);

        origen.setPadre(objetivo);

        if(origen == root){
            setRoot(objetivo);
            objetivo.setPadre(null);
        } else {
            objetivo.setPadre(auxPadre);
        }

        if(objetivo == auxDerecho) {
            objetivo.setHijoDerecho(origen);
            objetivo.setHijoIzquierdo(auxIzquierdo);

        } else {
            objetivo.setHijoIzquierdo(origen);
            objetivo.setHijoDerecho(auxDerecho);
        }

        if(objetivo.getHijoDerecho() != null){
            objetivo.getHijoDerecho().setPadre(objetivo);
        }
        
        if(objetivo.getHijoIzquierdo() != null){
            objetivo.getHijoIzquierdo().setPadre(objetivo);
        }

        if(origen.getHijoDerecho() != null){
            origen.getHijoDerecho().setPadre(origen);
        }
        
        if(origen.getHijoIzquierdo() != null){
            origen.getHijoIzquierdo().setPadre(origen);
        }
    }
}
