package working_with_optional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

record Product(long id, String name, BigDecimal price) {
}

public class OptionalExample2 {

    private static final List<Product> PRODUCTS = Arrays.asList(
            new Product(1000l, "Orange", new BigDecimal("1.65")),
            new Product(1001l, "Apples", new BigDecimal("2.01")),
            new Product(1002l, "Plates", new BigDecimal("12.95")),
            new Product(1003l, "Pencils", new BigDecimal("5.79")),
            new Product(1004l, "Notebook", new BigDecimal("4.99")));

    public static void main(String[] args) {
        Optional<Product> optional = getProductById(1000l);
        Optional<Product> optional2 = getProductById(10000);
        optional.ifPresent((p) -> System.out.println("Product found : " + p));
        optional2.ifPresentOrElse((p) -> System.out.println("Product found : " + p),
                () -> System.out.println("Product not found"));

        Set<Long> productIds = Set.of(1000l, 1001l, 1002l, 10000l, 10001l);

        List<Product> products = productIds.stream().map(OptionalExample2::getProductById).flatMap(Optional::stream)
                .toList();

        System.out.println(products);
        System.out.println(optional2.stream().count());

    }

    private static Optional<Product> getProductById(long id) {

        return PRODUCTS.stream().filter(p -> p.id() == id).findFirst();
    }
}
