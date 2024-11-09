package arboles.heap;

import arboles.binario.ArbolBinario;
import arboles.binario.Nodo;

public class Heap<T> extends ArbolBinario<T>{
    
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
        
    }

    @Override
    public void delete(Nodo<T> nodo){
        super.delete(nodo);

    }

    private void heapify(){

    }

}
