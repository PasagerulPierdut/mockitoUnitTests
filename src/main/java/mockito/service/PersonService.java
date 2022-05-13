package mockito.service;

import mockito.model.Person;
import mockito.repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;

public class PersonService {

    private PersonRepository personRepository;
    private ContractService contractService;

    public PersonService(PersonRepository personRepository, ContractService contractService) {
        this.personRepository =personRepository;
        this.contractService = contractService;
    }

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAllEmployedPersons() {
        List<Person> personList = personRepository.getAll();
        return personList.stream()
                .filter(person -> contractService.isEmployed(person.getId()))
                .collect(Collectors.toList());
    }

    public List<Person> findByName(String name) {
        return personRepository.findByName(name);
    }
}
