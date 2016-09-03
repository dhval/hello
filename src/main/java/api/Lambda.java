package api;

import java.util.*;
import java.util.stream.*;
import api.lambda.*;

@FunctionalInterface
interface Convertor<F, T> {
    T convert(F func);
}

public class Lambda {
    
    static List<String> list = Arrays.asList("Mark", "Paidi", "Josh", "Eric", "Chris");
    
    public static void main(String[] args) {
        
        Collections.sort(list, (s1, s2) -> s1.compareTo(s2));
        
        PersonInf person = Person::new;
        
        System.out.println(list);
        
        Convertor<String, Integer> convertor = Integer::valueOf;
        System.out.println(convertor.convert("123"));
        
        list.stream().map(s -> s.toUpperCase()).forEach(System.out::println);
        
        
    }
}