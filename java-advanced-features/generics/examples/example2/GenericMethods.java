package generics.examples.example2;

import java.util.function.BiFunction;

record Pair<T, U>(T first, U second) {
    /**
     * Generic static method
     * static methods don't have access to the type parameters of their enclosing
     * type (T,U, here), reason is static method belong to the class itself and not
     * associated to a particular instance
     * i.e. (T,U) will be passed by the instance of the class
     * 
     * @param <V>
     * @param <W>
     * @param first
     * @param second
     * @return
     */
    public static <V, W> Pair<V, W> of(V first, W second) {
        return new Pair<>(first, second);
    }

    /**
     * since these are instance methods, they can access the (T,U) type parameter of
     * the enclosing class
     * 
     * @param newFirst
     * @return
     */
    public Pair<T, U> withFirst(T newFirst) {
        return Pair.of(newFirst, second);
    }

    public Pair<T, U> withSecond(U newSecond) {
        return Pair.of(first, newSecond);
    }

    // generic instance method
    public <V, W> Pair<V, W> map(BiFunction<T, U, Pair<V, W>> fn) {
        return fn.apply(first, second);
    }
}

public class GenericMethods {
    public static void main(String[] args) {
        Pair<Integer, String> pair = Pair.<Integer, String>of(1, "one");
        Pair<Integer, String> pair2 = Pair.of(2, "two");

        var p3 = pair2.map((first, second) -> Pair.of(second, first));
        // type safe version
        var p4 = pair2.<String, Integer>map((Integer first, String second) -> Pair.of(second, first));
    }
}