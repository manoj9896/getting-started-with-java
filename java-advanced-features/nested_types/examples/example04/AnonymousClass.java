package nested_types.examples.example04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AnonymousClass {
    public static void main(String[] args) {
        // with an anonymous class, you implement an interface or extend another class
        // on the spot
        example();

        // all rules of the local classes is also applicable to anonymous classes
        // An anonymous class can access local variables of its enclosing method and
        // members of the its enclosing class as local class
        // (remember rules - effectively final, static, shadowing apply here as well)

        // we cannot define the constructoe in Anonymous class (as they don't have a
        // name)

        // anonymous class can extend a class as well
        // we can pass the argument list inside the parentheses
        // this list is passed to the constructor of the class which are we extending
        // in many cases we can replace the anonymous classes by lambda expressions or
        // method references
        List<String> names = new ArrayList<>(List.of("Joe", "smith", "john", "Louise", "sky", "rohi"));
        names.sort(Comparator.comparingInt(String::length));
    }

    static void example() {
        List<String> names = new ArrayList<>(List.of("Joe", "smith", "john", "Louise", "sky", "rohi"));
        // lets sort them based on length

        // a new class is defined on the spot here
        names.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }

        });

        System.out.println(names);
    }
}
