package lambda_expressions_and_method_references.examples;

import java.math.BigDecimal;
import java.util.List;

record Product(long id, String name, BigDecimal price) {
}

public class MethodReference {
    public static void main(String[] args) {
        List<String> names = List.of("Joe Smith", "Susan Miller", "Will Johnson", "Hari", "Rohit", "Mohit");
        // print the names using lambda expression
        System.out.println("**** Using Lambda expression ****");
        names.forEach(name -> System.out.println(name));
        // using method reference
        System.out.println("**** Using Method Reference ****");
        names.forEach(System.out::println); // reference to an instance method of a specific object (of type System.out)

        // different type of method reference

        List<Product> products = List.of(
                new Product(1, "apples", new BigDecimal(2.23)),
                new Product(2, "oranges", new BigDecimal(4.09)),
                new Product(3, "cheese", new BigDecimal(6.99)));

        // static reference
        // Equivalent to: MethodReference.isExpensive(a, b)
        List<Product> expensiveProducts = products.stream().filter(MethodReference::isExpensive).toList();
        // reference to an instance method of a specific object
        expensiveProducts.forEach(System.out::println);
        // this is a method reference to an instance method that lets the context
        // determine on which object it should be called.
        // For instance methods, the first parameter becomes the object
        // Equivalent to:(a, b) -> a.add(b)
        double totalPrice = products.stream().map(Product::price).reduce(BigDecimal.ZERO, BigDecimal::add)
                .doubleValue();

        System.out.println(totalPrice);

    }

    static boolean isExpensive(Product product) {
        return product.price().compareTo(new BigDecimal(4.00)) >= 0;
    }
}
