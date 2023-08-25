package logica.DataStructure.Pila;

public class Pila<T> {

    private Node<T> tope;

    public Pila() {
        tope = null;
    }

    public boolean isEmpty() {
        return tope == null;
    }

    public void push(T dato) {
        Node<T> nuevoNodo = new Node<>(dato);
        if (isEmpty()) {
            tope = nuevoNodo;
        } else {
            nuevoNodo.siguiente = tope;
            tope = nuevoNodo;
        }
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T datoEliminado = tope.dato;
        tope = tope.siguiente;
        return datoEliminado;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return tope.dato;
    }
}