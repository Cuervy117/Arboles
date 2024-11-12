package principal;
import java.util.ArrayList;

import arboles.binario.ArbolBinario;
import arboles.binario.Nodo;
import arboles.deBusqueda.*;
public class test {
    public static void main(String[] args) {
        ABB<Integer> arbol = new ABB<>();
        ArrayList<Integer> tal = new ArrayList<>();
        arbol.add(new Nodo<>(12));
        arbol.add(new Nodo<>(6));
        arbol.add(new Nodo<>(24));
        arbol.add(new Nodo<>(15));
        arbol.add(new Nodo<>(18));
        arbol.add(new Nodo<>(17));
        arbol.add(new Nodo<>(20));
        arbol.add(new Nodo<>(22));
        arbol.add(new Nodo<>(21));
        ArbolBinario.notPrefija(arbol.getRoot(), tal);
        System.out.println(tal.toString());
        try{
            arbol.delete(12);
        } catch (Exception e){
            System.out.println("no se pudo eliminar");
        }
        tal.clear();
        ArbolBinario.notPrefija(arbol.getRoot(), tal);
        System.out.println(tal.toString());
    }
}
