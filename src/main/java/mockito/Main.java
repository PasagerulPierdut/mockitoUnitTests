package mockito;

import mockito.repository.ContractRepository;
import mockito.repository.PersonRepository;
import mockito.service.ContractService;
import mockito.service.PersonService;

public class Main {

    public static void main(String[] args) {


        PersonService personService = new PersonService(new PersonRepository(), new ContractService(new ContractRepository()));

        System.out.println(personService.findAllEmployedPersons());

        System.out.println(personService.findByName("Joh"));
    }
}
