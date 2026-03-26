# Generics

Generics enable types (classes and interfaces) to have type parameters. It provides a way to create classes, interfaces, and methods that can operate on any type of data while providing compile-time type safety.

## Generic Types

- A generic type is a type that has Type parameters, which is instantiated with actual types when the generic type is used.
- define generic types with type parameters `<K, V>` - `examples/example01`

## More about Generic Types

- Single capital letter names for type parameters are widely accepted convention, but you can use any name that makes sense in the context of your code.
- You cannot use primitive types as type arguments for generic types.
- Anonymous inner classes, enums and exception classes cannot have type parameters.

## Generic Methods

- A generic method is a method that has its own type parameters, which are independent of any generic type parameters.
- refer -  `examples/example02`

## Bounded Type Parameters

- Bounded type parameters allow you to specify constraints on the types that can be used as type arguments for a generic type or method.
- You can use the `extends` keyword to specify an upper bound for a type parameter, which means that the type argument must be a subtype of the specified type.
- You can also use the `super` keyword to specify a lower bound for a type parameter, which means that the type argument must be a supertype of the specified type.
- refer - `examples/example03`

## Raw Types

A raw type is a generic type that is used without type arguments.

```java
List list = new ArrayList(); // raw type
```

- Raw types are provided for backward compatibility with older versions of Java that did not support generics.
- However, using raw types can lead to runtime errors and should be avoided in favor of using parameterized types with explicit type arguments.

## Sub Types

When one type is a subtype of another type, it normally means that there is an "IS-A" relationship between the subtype and the supertype. For example, if `Dog` is a subtype of `Animal`, then we can say that "a Dog IS-A Animal". This means that any instance of `Dog` can be treated as an instance of `Animal`, and we can use polymorphism to call methods defined in the `Animal` class on instances of `Dog`.

refer - `examples/example04`

Note: Generics are invariant in Java, which means that `List<Dog>` is not a subtype of `List<Animal>`, even though `Dog` is a subtype of `Animal`. This means that if two types, S and T have a subtype relationship, it does not necessarily mean that the corresponding parameterized types, `List<S>` and `List<T>`, will have a subtype relationship. This is because of type erasure, which removes generic type information at runtime.

## Wildcards

A wildcard is a way to refer a family of types.

- `?` unbounded wildcard : refers to family of all types (unknwown type).
- `? extends T` upper bounded wildcard : refers to family of types that are subtypes of T(sometype) (including T itself).
- `? super T` lower bounded wildcard : refers to family of types that are supertypes of T(sometype) (including T itself).
- we can use wildcards as type arguments for generic types and methods.
refer - `examples/example05`
We can pass two types of arguments to a generic type or method:
- Concrete types: These are specific types that we want to use as type arguments. For example, `List<String>` is a concrete type where `String` is the type argument.
- Wildcards: These are special type arguments that allow us to refer to a family of types. For example, `List<?>` is a wildcard type that can refer to any type of list, while `List<? extends Number>` is a wildcard type that can refer to any list of a subtype of `Number`.

### Using Wildcards

- Wildcards are often used when we want to write code that can work with a variety of types, but we don't know exactly what those types will be at compile time.
- For example, if we have a method that takes a `List<? extends Number>`, we can pass in a `List<Integer>`, `List<Double>`, or any other list of a subtype of `Number`. This allows us to write more flexible and reusable code.
- defining methods that take parameters of parameterized types, avoid unnecessary restrictions.

### how `? extends` vs `? super` behave

```java
    List<? extends Number> nums1 = new ArrayList<Integer>();
    List<? super Number> nums2 = new ArrayList<Number>();

    nums1.add(1);   // ❌ error
    nums2.add(1);  // ✅ works
```

- nums1 is a list of some unknown subtype of `Number`, so we cannot add any specific type to it, because we don't know what the actual type is. It could be a `List<Integer>`, `List<Double>`, or any other list of a subtype of `Number`. Therefore, adding an element to `nums1` would result in a compile-time error.
  - "I don’t know the exact subtype" → ❌ can’t add
  - `? extends` → read only
- nums2 is a list of some unknown supertype of `Number`, which means that it can accept any type that is a supertype of `Number`, including `Number` itself. Therefore, adding an element to `nums2` is allowed, because we know that it can accept any type that is a supertype of `Number`.
  - "I don’t know the exact supertype, but it can hold Number" → ✅ can add
  - `? super` → write only
- PECS Principle: "Producer Extends, Consumer Super"
  - If a parameterized type produces T values (e.g., it has methods that return T), then you should use `? extends T`. If a parameterized type consumes T values (e.g., it has methods that take T as a parameter), then you should use `? super T`.  

## Type Erasure

Note: Generics are a compild-time feature, and Generic and parameterized types, type parameters and type arguments do not exist at runtime. (not information is retained in the bytecode.)
Type erasure is the process by which the compiler removes all generic type information from the code after it has been compiled. This means that at runtime, all generic types are treated as raw types, and any type parameters are replaced with their upper bounds (or `Object` if no upper bound is specified).

## Heap Pollution

Heap pollution occurs when a variable of a parameterized type refers to an object that is not of that parameterized type. This can lead to runtime errors and can occur when using raw types or when using varargs with generics.

## Generics and Variable Arguments

- A variable argument (varargs) is a method parameter that can accept a variable number of arguments of the same type. It is denoted by an ellipsis (`...`) after the parameter type.
- When using varargs with generics, you can encounter issues with type safety and heap pollution. Heap pollution occurs when a variable of a parameterized type refers to an object that is not of that parameterized type.

## Reifiable vs Non-Reifiable Types

- A reifiable type is a type that retains its type information at runtime. Examples of reifiable types include primitive types, non-generic classes, and raw types.
- A non-reifiable type is a type that does not retain its type information at runtime. Examples of non-reifiable types include parameterized types, type variables, and wildcard types.
- Because of type erasure, non-reifiable types cannot be used in certain contexts, such as in instanceof checks or as the type of an array. This is because the type information is not available at runtime, and it can lead to runtime errors if you try to use non-reifiable types in these contexts.
