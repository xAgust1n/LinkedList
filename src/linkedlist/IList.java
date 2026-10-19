
package linkedlist;

public interface IList<T> {

    void append(T elemento) throws ListException;

    void insert(T elemento, int index) throws ListException;

    T remove(int index) throws ListException;

    boolean removeObj(T elemento) throws ListException;

    int indexOf(T elemento);

    T get(int index) throws ListException;

    void set(T elemento, int index) throws ListException;

    void clear();

    boolean empty();

    int size();

    void invertir();

    void concatenar(ListaEnlazada<T> otraLista);

    void irInicio();

    void irFinal();

    void previous();

    T actual();
}