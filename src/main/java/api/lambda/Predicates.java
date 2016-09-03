package api.lambda;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.function.Predicate;
import java.util.*;

public class Predicates {
    
    static class Person {
        int age;
        
        Person(Integer a) {
            age = a;
        }
        
        public String toString() {
            return Integer.toString(age);
        }
    }
    
    public static void filter(List<Person> list, Predicate<Person> predicate) {
        list.stream().filter(predicate).forEach(System.out::println);
    }
    
    public static Predicate<Person> isGreaterThan10() {
        return p -> p.age > 10;
    }
    
    public static void main(String[] s) {
        List<Person> person = IntStream.range(10, 20).boxed().map(Person::new).collect(Collectors.toList());
        filter(person, isGreaterThan10());
    }
    
}