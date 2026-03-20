package nested_types.examples.example02;

import nested_types.examples.example02.Enclosing.Inner;

public class TestExample02 {

    public static void main(String[] args) {
        Enclosing enclosing = new Enclosing();
        Inner innerClass = enclosing.new Inner();
    }
    
}
