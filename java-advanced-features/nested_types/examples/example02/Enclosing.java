package nested_types.examples.example02;

// recommendation - if we need a nested class make in static by default
// only make it inner if there is good reason to do so

public class Enclosing {

    private String name="enclosing";
    class Inner{
        // shadowing
        private String name="inner";

        void run(){
            System.out.println(name); // name in inner class
            System.out.println(Enclosing.this.name); // name in enclosing class
            // this is also how you access the hidden reference Enclosing.this
        }
    }

    public void createInner(){
        Inner inner = new Inner();
    }
}
