package nested_types.examples.examples01;

import java.time.LocalDate;

/**
 * when members of a class are static,
 * it means that those members belong to the class itself
 * and are not associated with a particular instance of the class
 */

public class Enclosing {
    private static int num = 20;
    private String name = "name";

    private static void printNumber() {
        System.out.println(num);
        // can only access the static member or types
    }

    public void printName() {
        System.out.println(name);
    }

    private static LocalDate date = LocalDate.of(2026, 3, 20);

    private static void printDate() {
        System.out.println(date);
    }

    static class Nested {
        void run() {
            // accessing the static members of the enclosing class is ok
            System.out.println(num);
            printNumber();
            printDate(); // nested class
            Enclosing.printDate(); // enclosing class
        }
        // enclosing and nested classed can access each othe private members
        // they are one tightly couple of code

        // shadowing the date and print date method of the enclosing class

        private static LocalDate date = LocalDate.of(2026, 3, 20);

        private static void printDate() {
            System.out.println(date);
        }
    }

}
