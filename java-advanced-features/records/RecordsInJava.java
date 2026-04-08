package records;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Immutable Object : the state of the object cannot be changed after the object
 * is constructed e.g. classes without setter methods String, Date, Time classes
 * 
 * Advantages of immutability - make programs less compicated (as the object
 * state will not change so we don't have to worry about updates) - thread-safe
 * (in multi-threading system object will not change so syncronization is easy)
 * - immutable objects can be safely shared and reused Disadvantages - to
 * combine two immutable objects always need to create a third immutable object
 * -> so if we have to add many immutable object it will be memory insufficient
 * (e.g. String concatenation in a loop, so use String builder)
 */

class ImmutableProductClass {
    private final long id;
    private final String name;
    private final String description;

    public ImmutableProductClass(long id, String name, String description) {
        this.description = description;
        this.id = id;
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return String.format("%s(id=%s,name=%s,description=%s)", ImmutableProductClass.class.getSimpleName(), id, name,
                description);
    }
}

/**
 * To create a immutable class we have to write a lot of code Here comes the
 * record -> help in creating a immutable classed quickly
 */
record ProductRecord(long id, String name, String description) {
    // The compiler translates this to a final class
    // with private final fields for the components

    // what not allowed inside a record
    // Instance fields are not allowed like (non-statics)
    // private int value;

    // static fields are allowed
    private static int VALUE = 23;
    // Records cannot extend classes, but can implement interfaces
}

/**
 * record can be used to define - Data transfer Objects, domain specific values
 */

// Building records with the builder pattern
// we will take an example of the Order, orderLine (line items)

record Customer(String firstName, String lastName) {
    public String fullName() {
        return firstName + " " + lastName;
    }
}

record OrderLineItem(long id, String productName, BigDecimal amount, long quantity) {

}

record Order(long id, Customer customer, LocalDateTime dateTime, List<OrderLineItem> items) {
    public Order {
        items = List.copyOf(items);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder pattern taks a lot of code
     * we can use Lombok to generate Builder
     */

    public static class Builder {
        private long id;
        private Customer customer;
        private LocalDateTime dateTime;
        private List<OrderLineItem> items = new ArrayList<>();

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder forCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder atDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder addItem(OrderLineItem item) {
            this.items.add(item);
            return this;
        }

        public Order build() {
            return new Order(id, customer, dateTime, items);
        }
    }
}

public class RecordsInJava {
    public static void main(String[] args) {
        ImmutableProductClass immutableProductClass = new ImmutableProductClass(1, "product1", "product 1 description");
        ProductRecord productRecord = new ProductRecord(1, "product1", "product 1 description");

        System.out.println(immutableProductClass.toString());
        System.out.println(productRecord.toString());
        System.out.println(productRecord.id());

        Order.Builder builder1 = Order.builder();
        Order.Builder builder2 = Order.builder();
        Order.Builder builder3 = Order.builder();
        Order.Builder builder4 = new Order.Builder();

        builder1.withId(2L).forCustomer(new Customer("Jhon", "ky")).atDateTime(LocalDateTime.now())
                .addItem(new OrderLineItem(1, "apples", BigDecimal.valueOf(100.67), 10)).build();
    }

}
