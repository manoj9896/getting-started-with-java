package try_with_resources;

public class ExampleResource implements AutoCloseable {

    private String name;

    public ExampleResource(String name) {
        this.name = name;
        System.out.println("Initializing resource: " + name);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing resource: " + name);
    }

}
