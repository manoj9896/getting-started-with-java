package working_with_optional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

record Product(long id, String name, BigDecimal price) {
}

public class OptionalExample {
    private static final List<Product> PRODUCTS = Arrays.asList(
            new Product(1000l, "Orange", new BigDecimal("1.65")),
            new Product(1001l, "Apples", new BigDecimal("2.01")),
            new Product(1002l, "Plates", new BigDecimal("12.95")),
            new Product(1003l, "Pencils", new BigDecimal("5.79")),
            new Product(1004l, "Notebook", new BigDecimal("4.99")));

    public static void main(String[] args) {

        Random random = new Random();

        Optional<Product> product = getProductByIdV2(1000L);
        Optional<Product> product2 = getProductByIdV2(10001L);
        System.out.println(product);
        System.out.println(product2);

        // we can also use optional on the getProductById as well
        // convert from nullabel world to optional world

        Optional<Product> product3 = Optional.ofNullable(getProductById(0L));

        System.out.println(product3);

        // always check if value is present then use it

        if (product.isPresent()) {
            System.out.println("this product is present " + product.get().id());
        }

        // orElse : get back from optional world to nullable world
        Product product4 = product.orElse(null);
        // orElseGet : product2 is empty we supply the product
        Product product5 = product2.orElseGet(() -> PRODUCTS.get((random.nextInt(PRODUCTS.size()))));

        System.out.println(product5);

        // orElseThrow
        product2.orElseThrow();
        product2.orElseThrow(() -> new NoSuchElementException("Not Found"));
        // product.or

    }

    // without optinal
    private static Product getProductById(long id) {

        for (Product product : PRODUCTS) {
            if (product.id() == id)
                return product;
        }
        return null;
    }
    // with optional

    private static Optional<Product> getProductByIdV2(long id) {
        for (Product product : PRODUCTS) {
            if (product.id() == id)
                return Optional.of(product);
        }
        return Optional.empty();
    }
}
