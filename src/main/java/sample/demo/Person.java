package sample.demo;

public class Person implements Human {

  private String name;
  private String age;

  public String getName() {
    return name;
  }

  public void setName(String name) {

    this.name = name;
  }

  public void sayHello(String name) {
    System.out.println("Hello !"  + this.name);
  }

}