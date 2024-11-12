package archivos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import arboles.AVL.*;
import arboles.aritmetico.ArbolExp;
import arboles.binario.ArbolBinario;
import arboles.heap.Heap;

/**
 * Clase que contiene los métodos necesarios para el guardado y la lectura de archivos de objetos, los cuales
 * contienen un tipo de arbol binario.
 * Su proposito es la de proporcionar una manera de guardar los datos de cada ejecución de cualquier tipo de arbol
 * permitiendo la recuperación de arboles ya antes creados.
 */
public class Archivos {
    /**
     * Método el cual leerá el objeto almacenado en la ruta especificada, dicho objeto es necesariamente del tipo 
     * ArbolBinario.
     * 
     * No se verifica la clase a la que pertenece el objeto leido debido a que se busca lanzar cualquier 
     * error en caso de no poder realizar el casteo explicito, indicando que si el archivo existe pero el objeto 
     * almacenado no es del tipo deseado es equivalente a que no exista directamente la base de datos especifica.
     * @param <T>   El tipo de dato generico que trata el arbol leido. 
     * @param ruta  El nombre de la base de datos que se quiere leer.
     * @return  El arbol leido de la base de datos.
     */
    @SuppressWarnings("unchecked")
    public static <T> ArbolBinario<T> leerBaseDeDatos(String ruta){
        try(ObjectInputStream archivo = new ObjectInputStream(new FileInputStream(ruta))){
            ArbolBinario<T> arbol = ((ArbolBinario<T>) archivo.readObject());
            System.out.println("Arbol leido");
            return arbol;
        }catch(EOFException e){
            System.out.println("Base de datos actualizada");
        }catch(IOException | ClassNotFoundException e){
            System.out.println("No se encontró la base de datos para " + ruta);
        }
        return null;
    }
    /**
     * Método el cual crea un archivo que almacena un objeto del tipo ArbolBinario en la ruta especificada.
     * Determinará el tipo de ArbolBinario en especifico que se le pasa como parametro para realizar el debido 
     * casteo de clase, esto para que no existan problemas al momento de leer y/o guardar en el futuro.
     * @param <T> El tipo generico que trata el arbol ingresado 
     * @param arbol El arbol que se desea guardar en un archivo
     * @param ruta El nombre del archivo en donde se desea guardar al arbol
     */
    @SuppressWarnings("rawtypes")
    public static <T> void guardarDatos(ArbolBinario<T> arbol, String ruta){
        try(ObjectOutputStream archivo = new ObjectOutputStream(new FileOutputStream(ruta))){
            if(arbol instanceof ArbolAVL){
                archivo.writeObject((ArbolAVL) arbol);
            } else if(arbol instanceof Heap){
                archivo.writeObject((Heap) arbol);
            } else if(arbol instanceof ArbolExp){
                archivo.writeObject((ArbolExp) arbol);
            }
            System.out.println("Arbol guardado");
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
