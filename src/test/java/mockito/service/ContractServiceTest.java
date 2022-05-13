package mockito.service;

import mockito.repository.ContractRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class ContractServiceTest {
    @Test
    void testIfisEmployedFiltersArgumentsInOrders() {

        ContractRepository mockContractRepository = mock(ContractRepository.class);
        ContractService mockContractService = new ContractService(mockContractRepository);

        when(mockContractRepository.existsById(1)).thenReturn(false);

        boolean result = mockContractService.isEmployed(1);

        org.junit.jupiter.api.Assertions.assertFalse(result);
    }
}