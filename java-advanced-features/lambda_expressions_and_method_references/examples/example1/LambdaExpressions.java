package lambda_expressions_and_method_references.examples.example1;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
interface Compare<T extends Number> {
    int compare(T o1, T o2);
}

class SortList {
    static <T extends Number> List<T> sort(List<T> list, Compare<T> fn) {
        List<T> sortedList = list.subList(0, list.size());
        sortedList.sort((o1, o2) -> {
            return fn.compare(o1, o2);
        });
        return sortedList;
    }

}

public class LambdaExpressions {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>(List.of(23, 45, 1, 3, 22, 50, 11, 66));

        System.out.println(SortList.sort(integers, (o1, o2) -> {
            return o1 - o2;
        }));
    }
}
