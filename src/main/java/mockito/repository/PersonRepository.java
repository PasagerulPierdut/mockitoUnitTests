package mockito.repository;

import mockito.model.Person;

import java.util.List;
import java.util.NoSuchElementException;
public class PersonRepository {

    List<Person> repository = List.of(
            new Person(1, "John", 30),
            new Person(2, "Jim", 30),
            new Person(3, "Johnnie", 38),
            new Person(4, "Mickey", 19),
            new Person(5, "Donald", 45),
            new Person(6, "Mary", 56),
            new Person(7, "Linus", 27),
            new Person(8, "Nicolae", 61)
    );

    public Person findByName(String name) {
        return getAll().stream()
                .filter(person -> person.getName().equals(name))
                .findAny().orElseThrow(()-> new NoSuchElementException("This person does not exist in the repository"));
    }

    public List<Person> getAll() {
        return repository;
    }
}
