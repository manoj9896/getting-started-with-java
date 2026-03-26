package generics.examples.example3;

import java.util.Comparator;
import java.util.List;

// multiple bounds to a type parameters

record Product(long id, String name, String description) {
}

interface HasId {
    long id();
}

interface HasName {
    String name();
}

// product which implements these interfaces
record ProductWithContract(long id, String name, String description) implements HasId, HasName {
}

public class MultiBoundedTypes {
    public static void main(String[] args) {
        /**
         * TODO: write a method that takes a list of products, sorts the products by id
         * and then returns a list containing the names of the sorted products
         */

        var products = List.of(new Product(43248L, "bread", "whole-wheat loaf"),
                new Product(32842L, "cheese", "gouda cheese"),
                new Product(33249L, "apples", "tasty red apples"));

        System.out.println(sortByIdAndExtractNames(products));
    }

    // non generic method it only work with products
    static List<String> sortByIdAndExtractNames(List<Product> products) {
        return products.stream().sorted(Comparator.comparingLong(Product::id)).map(Product::name).toList();
    }

    // generic method - lets make the sort to work with any type which has id and
    // name
    // & : intersection type
    static <T extends HasId & HasName> List<String> sortByIdAndExtractNamesWithGeneric(List<T> data) {
        return data.stream().sorted(Comparator.comparingLong(HasId::id)).map(HasName::name).toList();
    }

}
