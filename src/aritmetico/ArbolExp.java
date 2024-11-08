package aritmetico;

import java.util.List;
import java.util.Stack;
import arbolBinario.ArbolBin;
import arbolBinario.Nodo;

public class ArbolExp extends ArbolBin<String> {

    private static final List<String> operadores = List.of("+", "-", "*", "/");

    public ArbolExp(String[] elementos) {
        root = crearArbolDeExpresion(elementos);
    }

    public Nodo<String> crearArbolDeExpresion(String[] elementos) {
        Stack<String> operadores = new Stack<>();
        Stack<Nodo<String>> operandos = new Stack<>();

        for (String elemento : elementos) {
            if (esOperando(elemento)) {
                operandos.push(new Nodo<String>(elemento));
            } else if (esOperador(elemento)) {
                while (!operadores.isEmpty() && definirPrioridad(operadores.peek()) >= definirPrioridad(elemento)) {
                    obtenerSubarbol(operadores, operandos);
                }
                operadores.push(elemento);
            } else if (elemento.equals("(")) {
                operadores.push(elemento);
            } else if (elemento.equals(")")) {
                while (!operadores.isEmpty() && !operadores.peek().equals("(")) {
                    obtenerSubarbol(operadores, operandos);
                }
                operadores.pop();
            }
        }

        while (!operadores.isEmpty()) {
            obtenerSubarbol(operadores, operandos);
        }

        return operandos.pop();
    }

    public static boolean esOperador(String elemento) {
        return operadores.contains(elemento);
    }

    public static boolean esOperando(String elemento) {
        try {
            Double.parseDouble(elemento);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int definirPrioridad(String operador) {
        if ("+".equals(operador) || "-".equals(operador)) {
            return 1;
        } else if ("*".equals(operador) || "/".equals(operador)) {
            return 2;
        } else {
            return -1;
        }
    }

    public void obtenerSubarbol(Stack<String> operadores, Stack<Nodo<String>> operandos) {
        if (operadores.isEmpty() || operandos.size() < 2) return;

        String operador = operadores.pop();
        Nodo<String> der = operandos.pop();
        Nodo<String> izq = operandos.pop();

        Nodo<String> nodoOperador = new Nodo<>(operador);
        nodoOperador.setHijoIzquierdo(izq);
        nodoOperador.setHijoDerecho(der);

        operandos.push(nodoOperador);
    }
}