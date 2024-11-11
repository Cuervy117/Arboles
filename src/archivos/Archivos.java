package archivos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import arboles.AVL.*;
import arboles.aritmetico.ArbolExp;
import arboles.binario.ArbolBinario;
import arboles.heap.Heap;

public class Archivos {

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
