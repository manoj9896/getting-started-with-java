# Special methods in interfaces

an interface defines a contract for the classes the implement it. It can contain abstract methods, default methods, static methods, and private methods.

## Default methods

it a default implementation of a method in an interface. It allows to add new methods to an interface without breaking the existing implementations. It is defined using the `default` keyword.
so the classes that implement the interface can choose to override the default method or use the default implementation provided by the interface.
so in future if we want to add a new method to an interface, we can provide a default implementation for it, so that the existing implementations of the interface will not break. (backward compatibility)
refer to `examples/example1`

now this way interfaces and abstract may look similar but they are different in the way they are used and the features they provide.

- **Interface** : define a common contract for implementing classes.
- **Abstract class** : provide a common base for subclasses, allowing them to share code and define common behavior. (provide a way to share implementation code)

## Private methods

it is a method that can only be accessed within the interface. It is defined using the `private` keyword. It is used to provide helper methods for the default methods in the interface. It cannot be accessed by the implementing classes.

## Static methods

it is a method that can be accessed without creating an instance of the interface. It is defined using the `static` keyword. It can be accessed using the interface name. It is used to provide utility methods related to the interface.

refer to `examples/example2`
