package lambda_expressions_and_method_references.examples;

import java.util.ArrayList;
import java.util.List;

public class CapturingLocalVariables {
    public static void main(String[] args) {
        List<String> names = List.of("Joe Smith", "Susan Miller", "Will Johnson", "Hari", "Rohit", "Mohit");
        int count = 0;

        for (String name : names) {
            System.out.println(++count + ":" + name);
        }

        /**
         * we will get error here i.e. Local variable count defined in an enclosing
         * scope must be final or effectively final
         */
        // names.forEach((name) -> {
        // System.out.println(++count + ":" + name);
        // });

        // sometime the normal plan old for loop is fine compared to functional style

        List<String> lowerCaseNames = new ArrayList<>();
        // we will not have a problem here because we are only changing the state of the
        // lowerCaseNames variable not modifying it
        // but this is not recommended as we are modifying the local variable through
        // lambda expression, can we hard to debug in complicated code
        names.forEach((name) -> lowerCaseNames.add(name.toLowerCase()));

        // better way : use stream
        List<String> lowerCaseNames2 = names.stream().map(String::toLowerCase).toList();

    }
}
