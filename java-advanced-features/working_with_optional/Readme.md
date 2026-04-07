# Optional

In Java, variables of any reference type can have the special value `null`, which can lead to `NullPointerException` (where a program attempts to use an object reference that has the `null` value) error (one of the most frequent errors in Java).
`java.util.Optional` (is a safe alternative to `null` references) is a container object which can either contain a value or be empty. It provides methods to check if a value is present, retrieve the value, and perform various operations on the value if it is present. It is a way to represent optional values without using `null` references, which can help to avoid `NullPointerException` errors and make code more readable and maintainable.

## Properties of Optional

- It is a container object which may or may not contain a non-null value.
- `Optional` is an immutable class, which means that once an `Optional` object is created, its value cannot be changed.
- should not be used for fields, method parameters, or return types in public APIs. It is primarily intended for use as a return type for methods that may not always return a value.
- should not be used fro synchronization as it is not thread-safe.(it is not designed for concurrent access and does not provide any synchronization mechanisms), an instance of the `Optional` class is an immutable value object, with value objects syncronization does not make sense as they are not designed to be modified after they are created.
- `Optional` is not serializable, which means that it cannot be used in situations where serialization is required (e.g., when sending data over a network or saving it to a file).
- `Optional` is not intended to be used as a replacement for all `null` references. It is primarily designed to represent optional values in method return types, and it may not be suitable for all use cases.

## Usage of Optional

- refer `OptionalExample.java`
- refer `OptionalExample2.java`
