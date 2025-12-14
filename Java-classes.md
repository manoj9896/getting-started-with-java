# Classes in Java

- java is an object-oriented language
- allow storage and manipulation details to be hidden
- Separates what is to be done from how it is done
- A class is a template for creating objects.

```java
class Flight{
    int passengers;
    int seats;

    Flight(){
        seats = 150;
        passengers = 0;
    }

    void add1Passenger(){
        if(passengers < seats){
            passengers +=1;
        }
    }
}
```

- A class is made up of both state and executable code.
- Fields - store the object state.
- methods - executable code that manipulates the state and performs operations.
- constructors - executable code used during object creation to set inital state.
- Classes in Java are Reference Types

## Encapsulation and Access Modifiers

- The implementation details of a class are generally hidden
- Java uses access modifiers to achieve encapsulation
- access modifiers - controls the visibility of classes and their members

| Modifier  | Visibility | Usable on Classes | Usable on Members |
|-------|-----|-----------|-----|
| No access modifier | Only within its own package (a.k.a package private) | Yes | Yes |
| public | Everywhere | Yes | Yes |
| private | Only withib the declaring class | No <p>( private is available to nested-classes )</p> | Yes |

## this 
- implicit reference to current object 
- Useful for reducing ambiguity
- Allows an object to pass itself as a parameter

## null
- represents an uncreated object
- can be assigned to any reference variable

## Overloading
- having multiple versions of a particular method or multiple versions of our constructor.
- each constructor and method must have a unique signature. 
  - can be achieved through having different no. of parameters
  - can be achieved through having different data types of parameters
  
## Object Class
- java supports inheritance, allows one class to be declared with characteristics of another.
- Object class - root of Java class hierarchy
  - can reference an instance of any class
  ```java
  Object[] stuff = new Object[3];
  stuff[0] = new Flight();
  stuff[1] = new MathEquation();
  stuff[2] = "I like Java"
  ```

## Primitive wrapper classes
- can hold primitive data values
- provide methods
- enable compatibility with richer aspects of Java type system
- each primitive type has a wrapper class
    | Primitive Type | Wrapper Class |
    |----------------|-----------|
    |byte | Byte|
    |short | Short|
    |int | Integer|
    |long | Long|
    |float | Float|
    |double | Double|
    |char| Character|
    |boolean | Boolean|
- converting to and from a wrapper class (java compiler does that automatically)
  - Boxing - primitive to wrapper class
  - Unboxing - wrapper class to primitive

# Enums
- enumeration types - used for defining a type with a finite list of valid values
  - declare using `enum` keyword
  - provide comma-separated value list
  - by convention value names are all upper case
```java
enum Status{
    START,
    IN_PROGRESS,
    COMPLETED,
    FAILED
}
```
- a variable of type Status will hold only on value from the above statuses
- enum types are also classes - inherit form Java's Enum Class
- enum types can have members like classes (fields, methods, constructors)
- Each value with in an enum type is actually an instance of the enum type
- refer `EnumsInJava.java` class for examples

# Record
- some classes serve only as data carriers
- initialized with required data values and those values never change, for instance
  ```java
  class Person{
    private String name;
    private String DOB;
    public Person(String name, String dob){
        this.name = name;
        this.DOB = dob;
    }
  }
  ```
- with `record` - simplifies creating data-only classes (no need to write so many code)
    - created class is immutable
    - it will automatically generates common code like, constructor, getter methods
    ```java
    record Person(String name, String DOB){}
    ```

# Streams in Java
- A stream is a sequence of data elements supporting sequential and parallel aggregate operations.
- Streams can be created from various data sources, such as collections, arrays, or I/O channels.
- Streams support functional-style operations, such as map, filter, and reduce, allowing for concise and expressive data processing.
- Streams can be either sequential or parallel, enabling efficient processing of large data sets.
- Streams do not store data; they operate on the source data, producing a new stream as a result.
- Streams in java don't run until a terminal operation is invoked.