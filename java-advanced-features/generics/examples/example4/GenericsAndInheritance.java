package generics.examples.example4;

import java.util.ArrayList;
import java.util.List;

interface Animal {
}

record Dog(String name) implements Animal {
}

record Cat(String name) implements Animal {
}

public class GenericsAndInheritance {
    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();

        dogs.add(new Dog("daisy"));
        dogs.add(new Dog("lucky"));

        // compilation error - List of Dog cannot be converted to List of Animal.
        // The reason for this is that generics are invariant.
        // List<Animal> animals = dogs;
    }
}
