package try_with_resources;

public class ExampleResourceThrowException implements AutoCloseable {

    private String name;

    public ExampleResourceThrowException(String name) {
        this.name = name;
        System.out.println("Initializing resource: " + name);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing resource: " + name);
        throw new Exception("Exception thrown while closing the resource: " + name);
    }

}
