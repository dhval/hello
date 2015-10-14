package sample.demo;

public class Demo {

  static public void simpleFor() {

    int j = 0;
    while (j < 10) {

      if (j % 2 == 1) j++;
    }

    int i = 0;
    for (; i < 10; i = i + 1, System.out.println("i=" + i))
      System.out.println("iii=" + i);
  }

  static public Person simpleFor(String[] s) {
    for (String str: s) {
      System.out.println("" + str);
    }
    return new Person();
  }

  static void say(Human person) {
    person.setName("Marry");
    person.sayHello("John");
  }

  public static void main(String[] s) {
    Human human = new Human();
    Person person = new Person();
    say(person);
  }
}