package mockito.service;

import mockito.model.Contract;
import mockito.model.Person;
import mockito.repository.ContractRepository;
import mockito.repository.PersonRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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

        PersonRepository mockPersonRepository = Mockito.mock(PersonRepository.class);
        ContractRepository mockContractRepository = Mockito.mock(ContractRepository.class);
        ContractService mockContractService = new ContractService(mockContractRepository);
        PersonService mockPersonService = new PersonService(mockPersonRepository, mockContractService);

        Mockito.when(mockPersonRepository.getAll()).thenReturn(personList);
        Mockito.when(mockContractRepository.existsById(Mockito.anyInt())).thenReturn(true).thenReturn(false);
        Mockito.when(mockContractRepository.getContractById(1)).thenReturn(new Contract(1, LocalDateTime.of(2027, 5, 5, 12, 15)));

        List<Person> result = mockPersonService.findAllEmployedPersons();

        Assertions.assertEquals(p1, result.get(0));
        Mockito.verify(mockContractRepository, Mockito.times(1)).getContractById(Mockito.anyInt());
    }

    @Test
    void testIfFindByNameWithNoRegisteredArgumentReturnsEmptyList() {

        PersonRepository mockPersonRepository = new PersonRepository();
        ContractRepository mockContractRepository = Mockito.mock(ContractRepository.class);
        ContractService mockContractService = new ContractService(mockContractRepository);
        PersonService mockPersonService = new PersonService(mockPersonRepository, mockContractService);

        List<Person> result = mockPersonService.findByName("Martin");

        Assertions.assertEquals(0, result.size());
    }

    @Test
    void verifyIfFindByNameReturnsMultipleInstances() {

        List<Person> testList = List.of(
                new Person(1, "Gigi", 24),
                new Person(2, "Gigi", 28)
        );

        PersonRepository mockPersonRepository = Mockito.mock(PersonRepository.class);
        PersonService mockPersonService = new PersonService(mockPersonRepository);
        Mockito.when(mockPersonRepository.getAll()).thenReturn(testList);
        Mockito.when(mockPersonService.findByName(Mockito.anyString())).thenCallRealMethod();
        Mockito.when(mockPersonRepository.getAll()).thenReturn(testList);

        List<Person> result = mockPersonService.findByName("Gigi");

        Assertions.assertEquals(2, result.size());
    }
}