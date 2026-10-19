package linkedlist;

public class LinkedList {

    public static void main(String[] args) {

        Alumnito a1 = new Alumnito("Brianda", 25, "216578", 10.00);
        Alumnito a2 = new Alumnito("Agustin", 25, "266989", 8.00);
        Alumnito a3 = new Alumnito("Marco", 50, "002121", 9.00);
        Alumnito a4 = new Alumnito("Ximena", 25, "285678", 10.00);
        Alumnito a5 = new Alumnito("Alex", 23, "482635", 10.00);

        ListaEnlazada<Alumnito> lista = new ListaEnlazada<>();

         titulo("LISTA DOBLEMENTE ENLAZADA CIRCULAR");

        seccion("01 - AGREGAR ALUMNOS");
        lista.append(a1);
        lista.append(a2);
        lista.append(a3);
        mostrarLista(lista);

        seccion("02 - INSERTAR ALUMNO");
        lista.insert(a4, 2);
        System.out.println("Irma insertada en indice 2");
        mostrarLista(lista);

        seccion("03 - CONSULTAR ELEMENTO");
        mostrarAlumno("Indice 1", lista.get(1));

        seccion("04 - ELIMINAR ELEMENTO");
        mostrarAlumno("Eliminado", lista.remove(1));
        mostrarLista(lista);

        seccion("05 - ESTADISTICAS");
        System.out.println("Total de nodos: " + lista.size());
        System.out.println("Indice de Brianda: " + lista.indexOf(a1));

        seccion("06 - IR AL INICIO");
        lista.irInicio();
        mostrarAlumno("Cabeza", lista.actual());

        seccion("07 - IR AL FINAL");
        lista.irFinal();
        mostrarAlumno("Cola", lista.actual());

        seccion("08 - RETROCEDER CON PREVIOUS");
        lista.previous();
        mostrarAlumno("Nodo anterior", lista.actual());

        seccion("09 - PROBAR CIRCULARIDAD HACIA ATRAS");
        lista.irInicio();
        mostrarAlumno("Estamos en cabeza", lista.actual());

        lista.previous();
        mostrarAlumno("Anterior de cabeza", lista.actual());

        seccion("10 - PROBAR CIRCULARIDAD HACIA ADELANTE");
        lista.irFinal();
        mostrarAlumno("Estamos en cola", lista.actual());

        lista.next();
        mostrarAlumno("Siguiente de cola", lista.actual());

        seccion("11 - RECORRIDO HACIA ADELANTE");
        mostrarLista(lista);

        seccion("12 - RECORRIDO HACIA ATRAS");
        lista.irFinal();

        for (int i = 0; i < lista.size(); i++) {
            System.out.printf("%-4d %-12s %-10s%n",
                    i + 1,
                    lista.actual().getNombre(),
                    lista.actual().getId());
            lista.previous();
        }

        seccion("13 - INVERTIR LISTA");
        lista.invertir();
        mostrarLista(lista);

        seccion("14 - CONCATENAR LISTAS");
        ListaEnlazada<Alumnito> segunda = new ListaEnlazada<>();
        segunda.append(a5);
        lista.concatenar(segunda);
        mostrarLista(lista);

        seccion("15 - VACIAR LISTA");
        lista.clear();
        System.out.println("Total de nodos: " + lista.size());
        System.out.println("Lista vacia: " + lista.empty());

        titulo("PROGRAMA FINALIZADO CORRECTAMENTE");
    }

    public static void titulo(String texto) {
        System.out.println();
        System.out.println("=====================================================");
        System.out.println("          " + texto);
        System.out.println("=====================================================");
    }

    public static void seccion(String texto) {
        System.out.println();
        System.out.println("-----------------------------------------------------");
        System.out.println(" " + texto);
        System.out.println("-----------------------------------------------------");
    }

    public static void mostrarAlumno(String mensaje, Alumnito alumno) {
        System.out.printf("%-23s : %-12s ID: %s%n",
                mensaje,
                alumno.getNombre(),
                alumno.getId());
    }

    public static void mostrarLista(ListaEnlazada<Alumnito> lista) {
        System.out.printf("%-6s %-14s %-8s %-10s %-12s%n",
                "NODO", "NOMBRE", "EDAD", "ID", "CALIFICACION");

        System.out.println("-----------------------------------------------------");

        int i = 1;

        for (Alumnito alumno : lista) {
            System.out.printf("%-6d %-14s %-8d %-10s %-12.1f%n",
                    i++,
                    alumno.getNombre(),
                    alumno.getEdad(),
                    alumno.getId(),
                    alumno.getCalificacion());
        }

        System.out.println("-----------------------------------------------------");
        System.out.println("Total: " + lista.size() + " nodos");
    }
}