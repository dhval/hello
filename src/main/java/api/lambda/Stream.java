package api.lambda;

import java.util.*;
import java.util.stream.*;

public class Stream {
    
    private static List<Person> person = new ArrayList<>();
    
    private static void collectDemo() {
        Collector<Person, StringJoiner, String> personCollector = 
        Collector.of(
            () -> new StringJoiner(", "),
            (join, person) -> join.add(person.getName()),
            (join1, join2) -> join1.merge(join2),
            StringJoiner::toString
            );
            
        String result = person.stream().collect(personCollector);
        System.out.println(result);
    }
    
    private static void addPerson(String name, Integer age) {
        person.add(new Person(name, age));
        List<String> names = person.stream().map(Person::getName).collect(Collectors.toList());
        System.out.println(names.toString());
        collectDemo();
    }
    
    public static void main(String[] args) {
        addPerson("Matt", 38);
        addPerson("Keano", 34);
        addPerson("Will", 30);
    }
    
    
    
}