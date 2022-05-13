package mockito.service;

import mockito.model.Contract;
import mockito.model.Person;
import mockito.repository.ContractRepository;
import mockito.repository.PersonRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Test
    void testIfFindAllEmployedPersonsReturnsOnlyEmployees() {
        List<Person> personList = new ArrayList<>();
        Person p1 = new Person(1, "test", 19);
        Person p2 = new Person(2, "test2", 24);
        personList.add(p1);
        personList.add(p2);

        List<Contract> testContract = List.of(
                new Contract(1, LocalDateTime.of(2027, 5, 5, 12, 15)
        ));

        PersonRepository mockPersonRepository = Mockito.mock(PersonRepository.class);
        ContractRepository mockContractRepository = Mockito.mock(ContractRepository.class);
        ContractService mockContractService = new ContractService(mockContractRepository);
        PersonService mockPersonService = new PersonService(mockPersonRepository, mockContractService);

        Mockito.when(mockPersonRepository.getAll()).thenReturn(personList);
        Mockito.when(mockContractRepository.existsById(Mockito.anyInt())).thenReturn(true).thenReturn(false);
        Mockito.when(mockContractRepository.getAll()).thenReturn(testContract);
        Mockito.when(mockContractRepository.getContractById(1)).thenReturn(new Contract(1, LocalDateTime.of(2027, 5, 5, 12, 15)));

        List<Person> result = mockPersonService.findAllEmployedPersons();
        Assertions.assertEquals(p1, result.get(0));
        Mockito.verify(mockContractRepository, Mockito.times(1)).getContractById(Mockito.anyInt());
    }
    @Test
    void testIfFindByNameWithNoRegisteredArgumentThrows() {

        PersonRepository mockPersonRepository = Mockito.mock(PersonRepository.class);
        ContractRepository mockContractRepository = Mockito.mock(ContractRepository.class);
        ContractService mockContractService = new ContractService(mockContractRepository);
        PersonService mockPersonService = new PersonService(mockPersonRepository, mockContractService);

        NoSuchElementException thrown = Assertions.assertThrows(NoSuchElementException.class, () -> {
                    mockPersonService.findByName("Martin");
                });
            Assertions.assertEquals("This person does not exist in the repository", thrown.getMessage());
    }
}