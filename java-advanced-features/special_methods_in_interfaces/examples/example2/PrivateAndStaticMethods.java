package special_methods_in_interfaces.examples.example2;

public interface PrivateAndStaticMethods {
    private void helperMethod() {
        System.out.println(PrivateAndStaticMethods.class);
    }

    private static void printInterfaceName() {
        System.out.println(PrivateAndStaticMethods.class);
    }

    public static void main(String[] args) {
        printInterfaceName();
        System.out.println(PrivateAndStaticMethods.getInterfaceName());
    }

    void print();

    default void printClassName() {
        helperMethod();
    }

    static String getInterfaceName() {
        return "PrivateAndStaticMethods";
    }
}
