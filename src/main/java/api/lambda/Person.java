package api.lambda;

public class Person {
    private String name;
    private Integer age;
    
    public Person() {
        System.out.println("Constructor: Person");
    }

    public Person(String name, Integer age) {
        System.out.println("Constructor: Person(name, age)");
        this.name = name;
        this.age = age;
    }

    public String getName() { return name;}
    public void setName(String name) { this.name = name;}
    
    public Integer getAge() { return age;}
    public void setAge(Integer age) {
        this.age = age; 
        
    }

    
}