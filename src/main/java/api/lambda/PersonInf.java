package api.lambda;

public interface PersonInf<P extends Person> {
    P create(String name, Integer age);
}