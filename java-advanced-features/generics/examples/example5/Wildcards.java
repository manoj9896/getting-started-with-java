package generics.examples.example5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

interface Animal {
}

record Dog(String name) implements Animal {
}

record Cat(String name) implements Animal {
}

public class Wildcards {
    public static void main(String[] args) {

        List<Dog> dogs = new ArrayList<>();

        dogs.add(new Dog("daisy"));
        dogs.add(new Dog("lucky"));
        // with wildcards this works fine

        // a list of objects of a particular, but unknown type that extends Animal
        // wildcard hide this fact
        List<?> animals = dogs;
        List<? extends Animal> animals2 = dogs;

        // you can not add cat, dog, animal now to the above list
        // wildcard has capture the unknown type which extends Animal
        // A wildcard stands for a particular, but unknown type

        // below statements will be error now
        // animals.add(new Cat("cat1"));
        // animals.add(new Animal());
        // animals.add(new Dog("doggy"));
        // animals2.add(new Cat("cat1"));
        // animals2.add(new Animal());
        // animals2.add(new Dog("doggy"));

        // with same logic below statement is also give error
        // reason : A wildcard stands for a particular, but unknown type

        // List<?> anyList = new ArrayList<>();
        // anyList.add(new String());

        // List<? extends Number> nums = new ArrayList<Integer>();
        // nums.add(null);
        // Object obj = nums.get(0);

        // List<Object> objs = new ArrayList<>(); // Line 1
        // List<? super Integer> dest = objs; // Line 2
        // dest.add(42); // Line 3
        // Integer val = dest.get(0); // Line 4

    }

    static void howToUseWildcardsInPractice() {
        // public static <T> void copy(List<? super T> dest, List<? extends T> src)
        // why not like this
        // public static <T> void copy(List<T> dest, List<T> src)
        // because this way we can only copy dogs to Dog type list
        // not to Animal type list
        // copy methods allow all subtype of T (src) and can copy them to superType T
        // (dest)

        // Upper bounded wildcards for in parameters
        // Lower bounded wildcards for out parameter

        // Unbouded wildcard if the method does not need to know what the wildcard
        // stands for
        // avoid using wildcards in the return type of a method

        // public static int size(List<?> list)

        // understand other method as well
        // class Collections
        // interface Comparator
        // interface Stream
        // class Collectors

    }
}
