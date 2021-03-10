
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var ok = true;
        Scanner reader = new Scanner(System.in);
        int dimensiuneCoada;
        do {
            System.out.println("Introduceti dimensiunea cozii");
            dimensiuneCoada = tryParse(reader.nextLine());
        } while (dimensiuneCoada < 0);

        Coada coada = new Coada(dimensiuneCoada);
        while (ok == true) {

            System.out.println("\nMENIU");
            System.out.println("1. Adaugare");
            System.out.println("2. Eliminare");
            System.out.println("3. Vizualizare");
            System.out.println("4. Oprire\n");
            System.out.println("Tasteaza numarul corespunator operatiei dorite:");

            var str = reader.nextLine();

            switch (str) {
                case "1" -> {
                    int elem;
                    do {
                        System.out.println("Introduceti elementul dorit: ");
                        elem = tryParse(reader.nextLine());
                    } while (elem < 0);

                    coada.adaugare(elem);
                    System.out.println("Operatia de adaugare la coada s-a terminat\n");
                }
                case "2" -> {
                    coada.eliminare();
                    System.out.println("Operatia de eliminare a primului element din coada s-a terminat.\n");
                }
                case "3" -> {
                    coada.vizualizare();
                    System.out.println("Operatia de vizualizare s-a terminat.\n");
                }
                case "4" -> {
                    ok = false;
                    System.out.println("Programul s-a oprit\n");
                }
                default ->
                    System.out.println("Comanda gresita. Icearca din nou.\n");
            }
        }
    }

    public static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            System.out.println("Valoare introdusa gresit. Incercati din nou.\n");
            return -1;
        }
    }
}

class Coada {

    private int[] sir;
    private int primul;
    private int ultimul;
    private int capacitate;
    private int capacitateCurenta;

    // Constructor to initialize a queue
    Coada(int dimensiune) {
        sir = new int[dimensiune];
        capacitate = dimensiune;
        primul = 0;
        ultimul = -1;
        capacitateCurenta = 0;
    }

    public void vizualizare() {
        if (esteGoala()) {
            System.out.println("Nu exista niciun element in coada");
            return;
        }

        for (int el : sir) {
            System.out.print(el + " ");
        }
        System.out.println();
    }

    public void eliminare() {
        if (esteGoala()) {
            System.out.println("Nu exista niciun element in coada");
            return;
        }

        System.out.println("Se sterge: " + sir[primul]);

        primul = (primul + 1) % capacitate;
        capacitateCurenta--;
    }

    public void adaugare(int element) {
        if (estePlina()) {
            System.out.println("Coada a atins capacitatea maxima.");
            return;
        }

        System.out.println("Element adaugat: " + element);

        ultimul = (ultimul + 1) % capacitate;
        sir[ultimul] = element;
        capacitateCurenta++;
    }

    public int primulElement() {
        if (esteGoala()) {
            System.out.println("Nu exista niciun element in coada");
            return -1;
        }
        return sir[primul];
    }

    public int dimensiuneCurenta() {
        return capacitateCurenta;
    }

    public Boolean esteGoala() {
        return (dimensiuneCurenta() == 0);
    }

    public Boolean estePlina() {
        return (dimensiuneCurenta() == capacitate);
    }
}
