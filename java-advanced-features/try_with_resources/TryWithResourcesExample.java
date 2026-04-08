package try_with_resources;

public class TryWithResourcesExample {
    public static void main(String[] args) {

        /**
         * How the initialization and closing happens with try with resources
         * resource 2 will get closed first then resource 1
         */
        try (ExampleResource resource1 = new ExampleResource("1");
                ExampleResource resource2 = new ExampleResource("2")) {
            System.out.println("Inside the try-block");
        } catch (Exception e) {

        }

        /**
         * How exceptions are handled
         * if it occurs inside the try block
         */

        System.out.println("\n****************************************");
        System.out.println("How exceptions are handled");
        System.out.println("****************************************\n");

        try (ExampleResource resource3 = new ExampleResource("3");
                ExampleResource resource4 = new ExampleResource("4")) {
            System.out.println("Inside try-block");
            throw new Exception("Exception thrown inside the try-block");
        } catch (Exception e) {// Note resources are closed before the catch block execution
            System.out.println("Inside the catch-block");
            e.printStackTrace();
        }

        /**
         * How exceptions are handled
         * if it occurs while closing the resource
         */

        System.out.println("\n****************************************");
        System.out.println("How exceptions are handled : if it occurs while closing the resource");
        System.out.println("****************************************\n");

        try (ExampleResource resource5 = new ExampleResource("5");
                ExampleResourceThrowException resource6 = new ExampleResourceThrowException("6")) {
            System.out.println("Inside try-block");
            throw new Exception("Exception thrown inside the try-block");
        } catch (Exception e) {
            System.out.println("Inside the catch-block");
            e.printStackTrace();
        }
    }
}
