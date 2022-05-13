package mockito.service;

import mockito.model.Person;
import mockito.repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;

public class PersonService {

    private PersonRepository personRepository;
    private ContractService contractService;

    public PersonService(PersonRepository personRepository, ContractService contractService) {
        this.personRepository =new  PersonRepository();
        this.contractService = contractService;
    }

    public List<Person> findAllEmployedPersons() {
        return personRepository.getAll().stream()
                .filter(person -> contractService.isEmployed(person.getId()))
                .collect(Collectors.toList());
    }

    public Person findByName(String name) {
        return personRepository.findByName(name);
    }
}
