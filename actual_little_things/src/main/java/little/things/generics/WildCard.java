package little.things.generics;

import little.things.generics.model.SubClass;
import little.things.generics.model.SuperClass;

import java.util.Arrays;
import java.util.List;

public class WildCard {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("first", "second");
        List<Integer> integers = Arrays.asList(1, 2);

        checkWildcardList(strings);
        checkWildcardList(integers);
    }

    //You can work with multiple types without knowing which type you are working with.
    //But only one type in each collection to keep invariants
    private static void checkWildcardList(List<?> wildCardList) {
        System.out.println(wildCardList);
    }

    //Customer - super
    private static void checkWildcardListSuper(List<? super SuperClass> wildCardList) {
        SubClass subClass = new SubClass();
        wildCardList.add(subClass);
    }

    //Producer - extends
    private static void checkWildcardListExtends(List<? extends SuperClass> wildCardList) {
        for (SuperClass superClass : wildCardList) {
            System.out.println(superClass);
        }
    }

    //PECS
    public static void copyList(List<? extends SuperClass> from, List<? super SuperClass> to) {
        to.addAll(from);
    }
}
