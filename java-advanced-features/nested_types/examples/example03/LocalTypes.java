package nested_types.examples.example03;

public class LocalTypes {

    private String name = "local type";

    public void example(int x, int y) {
        int number = 34;

        class Local {
            void run() {
                System.out.println(x + y);
                System.out.println(number);
                // note : local variables reference inside the inner class must be final or
                // effectively final
                // applicable for the method parameters and local variables inside method
                // not on the member varible of the class e.g
                System.out.println(name);

            }
        }
        Local liLocal = new Local();
        liLocal.run();
        // number++; -> this is not allowed
        name = "test new local type"; // allowed for class members
    }
}

/**
 * local classes behave like the inner class
 * local interfaces, records, and enums behave as if they are static
 * local class cannot be static
 */
