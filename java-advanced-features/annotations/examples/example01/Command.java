package annotations.examples.example01;

/**
 * Defining an annotation
 * put @ sign in front of the keyword interface, which make the Command an
 * annotation
 */
public @interface Command {
    /**
     * annotations can have elements, which let the user specify metadata
     * Note : an annotation is not really an interface, and this is not really a
     * method. It just looks like this because the syntax for defining interfaces
     * was reused for defining annotations.
     */

    String value();

    String description() default ""; // can define default values, null is not allowed

    int order() default 0;

    // Note allowed element types
    // - primitive type String, an enum type, the type java.lang.Class, or another
    // annotation type, or an array of one of those five types,
    // anything else is not allowed.

    // Element values must be compile-time constants
    // cannot use expressions that have to be evaluated at runtime

    // annotations and there elements cannot be generics
}
