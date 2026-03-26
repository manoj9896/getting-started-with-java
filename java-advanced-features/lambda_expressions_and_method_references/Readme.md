# Lambda Expressions and Method References

Lambda expressions is way to create anonymous methods. They are used primarily to define inline implementation of a functional interface.
Method references is a shorthand notation of a lambda expression to call a method. They are used to refer to a method without invoking it.

## Functional Interfaces

A functional interface is an interface that has only one abstract method. They can have multiple default or static methods, but only one abstract method.
A lambda expression implements the abstract method of a functional interface.
The `@FunctionalInterface` annotation is used to indicate that an interface is a functional interface. It is not mandatory, but it helps to prevent accidental addition of abstract methods, help compile-time checking, and improve readability.

```java
@FunctionalInterface
public interface MyFunctionalInterface {
    void myMethod();
}
```

Only create your own functional interfaces if there is no existing one that fits your needs. Java provides several built-in functional interfaces in the `java.util.function` package, such as `Consumer`, `Supplier`, `Function`, and `Predicate`.

## Lambda Expressions

A lambda expression consists of three parts: the parameter list, the arrow token `->`, and the body. The parameter list can be empty or contain one or more parameters. The body can be a single expression or a block of code.

```java
// A lambda expression that takes no parameters and returns void
() -> System.out.println("Hello, World!");
// A lambda expression that takes one parameter and returns void
x -> System.out.println(x);
// A lambda expression that takes two parameters and returns their sum
(x, y) -> x + y;
```

## Capturing Local Variables

A lambda expression can capture local variables from the enclosing scope. However, these variables must be effectively final, meaning that they cannot be modified after they are assigned.

```java
public void myMethod() {
    int x = 10; // effectively final
    Runnable r = () -> System.out.println(x); // captures x
    r.run(); // prints 10
}
```

## Working with Checked Exceptions

Functional interfaces don't allow checked exceptions to be thrown directly from a lambda expression.
If a lambda expression throws a checked exception, it must be handled or declared in the method signature. You can use a try-catch block inside the lambda expression to handle the exception.

```java
Function<String, Integer> parseInt = s -> {
    try {
        return Integer.parseInt(s);
    } catch (NumberFormatException e) {
        e.printStackTrace();
        return null; // or throw a RuntimeException
    }
};
```

## Method References

A method reference is a shorthand notation of a lambda expression to call a method. It can be used to refer to a static method, an instance method of a particular object, or an instance method of an arbitrary object of a particular type.

```java
// A method reference to a static method
Function<String, Integer> parseInt = Integer::parseInt; // TypeName::staticMethodName
// A method reference to an instance method of a particular object
String str = "Hello, World!";
Supplier<Integer> length = str::length; // objectReference::instanceMethodName
// A method reference to an instance method of an arbitrary object of a particular type
Function<String, String> toUpperCase = String::toUpperCase; // TypeName::instanceMethodName
// constructor reference
Supplier<List<String>> listSupplier = ArrayList::new; // ClassName::new
```
