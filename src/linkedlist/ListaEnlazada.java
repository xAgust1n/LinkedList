
package linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ListaEnlazada<T> implements IList<T>, Iterable<T> {

    private class Nodo {
        T dato;
        Nodo sig;
        Nodo ant;

        Nodo(T dato) {
            this.dato = dato;
        }
    }

    private Nodo inicio;
    private Nodo fin;
    private Nodo cursor;
    private int nElementos;

    public ListaEnlazada() {
        inicio = null;
        fin = null;
        cursor = null;
        nElementos = 0;
    }

    private Nodo obtenerNodo(int index) {
        if (index < 0 || index >= nElementos) {
            throw new ListException("Indice fuera de limites");
        }

        Nodo nodo;

        if (index <= nElementos / 2) {
            nodo = inicio;
            for (int i = 0; i < index; i++) {
                nodo = nodo.sig;
            }
        } else {
            nodo = fin;
            for (int i = nElementos - 1; i > index; i--) {
                nodo = nodo.ant;
            }
        }

        return nodo;
    }

    @Override
    public void append(T elemento) throws ListException {
        Nodo nuevo = new Nodo(elemento);

        if (empty()) {
            inicio = nuevo;
            fin = nuevo;
            nuevo.sig = nuevo;
            nuevo.ant = nuevo;
        } else {
            nuevo.ant = fin;
            nuevo.sig = inicio;
            fin.sig = nuevo;
            inicio.ant = nuevo;
            fin = nuevo;
        }

        nElementos++;
        if (cursor == null) {
            cursor = inicio;
        }
    }

    @Override
    public void insert(T elemento, int index)
            throws ListException {

        if (index < 0 || index > nElementos) {
            throw new ListException("Indice fuera de limites");
        }

        if (index == nElementos) {
            append(elemento);
            return;
        }

        Nodo nuevo = new Nodo(elemento);
        Nodo siguiente = obtenerNodo(index);
        Nodo anterior = siguiente.ant;

        nuevo.sig = siguiente;
        nuevo.ant = anterior;

        anterior.sig = nuevo;
        siguiente.ant = nuevo;

        if (index == 0) {
            inicio = nuevo;
        }

        nElementos++;
    }

    @Override
    public T remove(int index) throws ListException {
        Nodo eliminado = obtenerNodo(index);
        T dato = eliminado.dato;

        if (nElementos == 1) {
            inicio = null;
            fin = null;
            cursor = null;
        } else {
            eliminado.ant.sig = eliminado.sig;
            eliminado.sig.ant = eliminado.ant;

            if (eliminado == inicio) {
                inicio = eliminado.sig;
            }

            if (eliminado == fin) {
                fin = eliminado.ant;
            }

            if (cursor == eliminado) {
                cursor = eliminado.sig;
            }
        }

        nElementos--;
        return dato;
    }

    @Override
    public boolean removeObj(T elemento)
            throws ListException {

        int index = indexOf(elemento);

        if (index == -1) {
            return false;
        }

        remove(index);
        return true;
    }

    @Override
    public int indexOf(T elemento) {
        Nodo nodo = inicio;

        for (int i = 0; i < nElementos; i++) {
            if (Objects.equals(nodo.dato, elemento)) {
                return i;
            }
            nodo = nodo.sig;
        }

        return -1;
    }

    @Override
    public T get(int index) throws ListException {
        return obtenerNodo(index).dato;
    }

    @Override
    public void set(T elemento, int index)
            throws ListException {

        obtenerNodo(index).dato = elemento;
    }

    @Override
    public void clear() {
        inicio = null;
        fin = null;
        cursor = null;
        nElementos = 0;
    }

    @Override
    public boolean empty() {
        return nElementos == 0;
    }

    @Override
    public int size() {
        return nElementos;
    }

    @Override
    public void irInicio() {
        cursor = inicio;
    }

    @Override
    public void irFinal() {
        cursor = fin;
    }

    @Override
    public void previous() {
        if (empty()) {
            throw new ListException("Lista vacia");
        }

        if (cursor == null) {
            cursor = fin;
        } else {
            cursor = cursor.ant;
        }
    }

    public void next() {
        if (empty()) {
            throw new ListException("Lista vacia");
        }

        if (cursor == null) {
            cursor = inicio;
        } else {
            cursor = cursor.sig;
        }
    }

    @Override
    public T actual() {
        if (cursor == null) {
            throw new ListException("Lista vacia");
        }

        return cursor.dato;
    }

    @Override
    public void invertir() {
        if (nElementos <= 1) {
            return;
        }

        Nodo nodo = inicio;

        for (int i = 0; i < nElementos; i++) {
            Nodo temporal = nodo.sig;
            nodo.sig = nodo.ant;
            nodo.ant = temporal;
            nodo = temporal;
        }

        Nodo temporal = inicio;
        inicio = fin;
        fin = temporal;
    }

    @Override
    public void concatenar(ListaEnlazada<T> otraLista) {
        if (otraLista == null) {
            throw new IllegalArgumentException("Lista nula");
        }

        int cantidad = otraLista.nElementos;
        Nodo nodo = otraLista.inicio;

        for (int i = 0; i < cantidad; i++) {
            append(nodo.dato);
            nodo = nodo.sig;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Nodo actual = inicio;
            private int recorridos = 0;
            private final int total = nElementos;

            @Override
            public boolean hasNext() {
                return recorridos < total;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                T dato = actual.dato;
                actual = actual.sig;
                recorridos++;

                return dato;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder("[");
        Nodo nodo = inicio;

        for (int i = 0; i < nElementos; i++) {
            texto.append(nodo.dato);

            if (i < nElementos - 1) {
                texto.append(", ");
            }

            nodo = nodo.sig;
        }

        texto.append("]");
        return texto.toString();
    }
}
