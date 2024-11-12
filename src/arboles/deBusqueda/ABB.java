package arboles.deBusqueda;

import arboles.binario.ArbolBinario;
import arboles.binario.Nodo;
/**
 * Clase que representa un arbol binario de busqueda.
 * Esta clase extiende de un arbolBinario generico. 
 * Dicho valor generico debe tener forzosamente el método "compareTo" para un correcto funcionamiento
 * 
 * Contiene métodos escenciales para la inserción, siguiendo la propiedad de los ABB donde su sub-arbol izquierdo
 * siempre contiene valores menor a la raiz y su sub-arbol derecho siempre contiene valores mayor a la raiz.
 * 
 * Contiene métodos escenciales para el borrado de nodos, manteniendo las propiedades del arbol binario
 */
public class ABB<T extends Comparable<T>> extends ArbolBinario<T> {
    /**
     * Crea un arbol binario de busqueda con raiz nula.
     */
    public ABB(){
        super();
    }
    /**
     * Constructor de un arbol binario de busqueda el cual tendrá raiz no nula.
     * @param clave Valor que adoptará el nodo raiz del arbol.
     */
    public ABB(T clave){
        super(clave);
    }
    /**
     * constructor de un Arbol binario de busqueda el cual tendrá raiz no nula.
     * @param root nodo el cual será la raiz del arbol creado.
     */
    public ABB(Nodo<T> root){
        super(root);
    }

    /**
     * Método que se encarga de la inserción de un nodo en el arbol.
     * Primero se realiza una verificación en caso de que exista algun nodo con el valor que se intenta insertar,
     * Se compara el valor del nodo a insertar con los nodos que corresponden a su naturaleza, dependiendo si es grande o pqueeño.
     */
    @Override
    public void add(Nodo<T> nodo){
        try{
            search(nodo.getClave());
            System.out.println("El valor ya se encuentra en el arbol");
            return;
        }catch (Exception e){
        }
        if(nodo == null) return;
        Nodo<T> rootAux = getRoot();
        
        if(rootAux == null ){
            setRoot(nodo);;
            return;
        }
        while(true){
            int comparacion = nodo.getClave().compareTo(rootAux.getClave());
            if(comparacion == 0) break;
            else if(comparacion > 0){
                if(rootAux.getHijoDerecho() != null){
                    rootAux = rootAux.getHijoDerecho();
                }else{
                    nodo.setPadre(rootAux);
                    rootAux.setHijoDerecho(nodo);
                    break;
                }
            }else{
                if(rootAux.getHijoIzquierdo() != null){
                    rootAux = rootAux.getHijoIzquierdo();
                }else{
                    nodo.setPadre(rootAux);
                    rootAux.setHijoIzquierdo(nodo);
                    break;
                }                    
            }
        }
    }

    /**
     * Método responsable de la eliminación de un nodo, para esto se busca el nodo mas pequeño del sub-arbol derecho 
     * o el nodo mas grande del subarbol izquierdo, se intercambia con el nodo que se desea eliminar. 
     * En caso de que el sustituo sea una hoja se realizará un unico intercambio y se eliminará como una hoja cualquier.
     * En caso de que este sustituo tenga hijos se reasignaran al nodo que se desea eliminar y se buscará denuevo el nodo
     * que sustiuirá hasta encontrar un nodo hoja sustituo.
     * 
     * @param clave Valor del nodo que se desea eliminar
     * @throws Exception Error que corresponde a la busqueda fallida del nodo que se desea eliminar.
     */
    @Override
    public void delete(T clave) throws Exception{
        if(root == null) return;
        Nodo<T> nodo;
        Nodo<T> nodoSucesor;
        nodo = search(clave);
        Nodo<T> nodoAux = nodo;
        if(nodo.isLeaf()){
            eliminarHoja(nodo);
        } else {
            while(true){
                nodoSucesor = nodo.getNodoSucesor();
                if(nodoSucesor.getHijoDerecho() != null){
                    nodoAux = nodoSucesor.getHijoDerecho();
                    nodeSwap(nodo, nodoSucesor);
                    nodo.setHijoDerecho(nodoAux);
                } else if(nodoSucesor.getHijoIzquierdo() != null){
                    nodoAux = nodoSucesor.getHijoIzquierdo();
                    nodeSwap(nodo, nodoSucesor);
                    nodo.setHijoIzquierdo(nodoAux);
                } else if(nodoSucesor.isLeaf()) {
                    nodeSwap(nodo, nodoSucesor);
                    eliminarHoja(nodo);
                    break;
                }
            }
        }
    }

    /**
     * Realiza una busqueda sobre el arbol binario comparando los valores de los nodos con el del valor que se busca.
     * Si el valor que se busca es igual al de la raiz, retorna el nodo que corresponde a la raiz, si no, compara el valor 
     * determinando si es mayor o menor a la raiz, en cada caso se iterá de la misma manera sobre los sub-arboles.
     * @param clave Clave generica que se desea buscar.
     */
    @Override
    public Nodo<T> search(T clave) throws Exception{
        Nodo<T> auxRoot = this.root;
        while (auxRoot != null) {
            int comparacion = auxRoot.getClave().compareTo(clave);
            if(comparacion == 0) return auxRoot;
            else if(comparacion > 0) auxRoot = auxRoot.getHijoIzquierdo();
            else auxRoot = auxRoot.getHijoDerecho();
        }
        throw new Exception("El nodo con dicho valor no existe");
    }

}
