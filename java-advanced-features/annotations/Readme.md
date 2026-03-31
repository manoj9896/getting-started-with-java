# Annotations

Annotations are used to add metdadata to Java code. They can be used to provide information about the code, such as what it does, how it should be used, and what it should not be used for. Annotations can also be used to generate code, such as documentation or test cases.

## Use Cases for Annotations

- Provide additional information to the compiler. For example,
  - `@Override` annotation can be used to indicate that a method is intended to override a method in a superclass. This can help the compiler catch errors if the method does not actually override a method in the superclass.
  - `@Deprecated` annotation can be used to indicate that a method or class is deprecated and should not be used. This can help the compiler generate warnings when the deprecated method or class is used.
  - `@SuppressWarnings` annotation can be used to suppress specific compiler warnings.
  - `@FunctionalInterface` annotation can be used to indicate that an interface is intended to be a functional interface.
- Processing annotations at **compile time** using an annotation processor. This can be used to generate code.
  - `Lombok` is a popular library that uses annotation processing to generate boilerplate code, such as getters and setters, constructors, and toString methods.
  - `Immutables` is another library that uses annotation processing to generate immutable value classes.
  - `MapStruct` is a library that uses annotation processing to generate mapping code between different object models.
- Processing annotations at **runtime**
  - `Spring Framework` uses annotations to configure beans and define the behavior of the application. For example, `@Autowired` annotation can be used to inject dependencies into a bean, and `@RequestMapping` annotation can be used to map HTTP requests to handler methods in a controller.
  - `Jakarta EE` (formerly Java EE) uses annotations to define the behavior of enterprise applications. For example, `@Entity` annotation can be used to indicate that a class is an entity that should be persisted to a database, and `@EJB` annotation can be used to inject an Enterprise JavaBean into a class.
  - `JUnit` uses annotations to define test methods and configure test execution. For example, `@Test` annotation can be used to indicate that a method is a test method, and `@BeforeEach` annotation can be used to indicate that a method should be executed before each test method.
  - `Jackson` uses annotations to configure how Java objects are serialized and deserialized to and from JSON. For example, `@JsonProperty` annotation can be used to specify the name of a property in JSON, and `@JsonIgnore` annotation can be used to indicate that a property should be ignored during serialization and deserialization.

## Applying Annotations

- Declaration Annotations: These annotations are applied to declarations of classes, methods, fields, local variables etc.
- Type Annotations: These annotations are applied to types. They can be used to enhance the type system.

most of annotations we use are declaration annotations, but type annotations can be very useful for static analysis and code generation.

## Definig an Annotation

refer to `examples/DefinigCustomAnnotation.java` for an example of how to define a custom annotation.

## Meta-Annotations

Meta-annotations are annotations that are applied to other annotations. They can be used to provide additional information about the annotation, such as how it should be processed or what it should be used for.

- `@Retention` meta-annotation is used to specify how long the annotation should be retained. It can be set to `SOURCE`, `CLASS`, or `RUNTIME`.
- `@Target` meta-annotation is used to specify where the annotation can be applied. It can be set to `TYPE`, `FIELD`, `METHOD`, `PARAMETER`, etc.
- `@Documented` meta-annotation is used to indicate that the annotation should be included in the Javadoc.
- `@Inherited` meta-annotation is used to indicate that the annotation should be inherited by subclasses.
- `@Repeatable` meta-annotation is used to indicate that the annotation can be applied multiple times to the same element.

## Inspecting Annotations at Runtime

You can use reflection to inspect annotations at runtime. For example, you can use the `getAnnotation` method to get a specific annotation on a class, method, or field, and then access its properties.
Annotation processors can also be used to inspect annotations at compile time and generate code based on them.

```java
MyAnnotation annotation = MyClass.class.getAnnotation(MyAnnotation.class);
if (annotation != null) {
    String value = annotation.value();
    // Do something with the value
}
```
