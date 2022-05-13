package mockito.service;

import mockito.model.Contract;
import mockito.repository.ContractRepository;

import java.time.LocalDateTime;
public class ContractService {

    private final ContractRepository contractRepository;
    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }
    public boolean isEmployed(int personId) {
        if (contractRepository.existsById(personId)) {
            return (isContractValid(personId));
        }
        return false;
    }
    private boolean isContractValid(int personId) {
        Contract contract = contractRepository.getContractById(personId);
        return (contract.getExpirationDate().toLocalDate().isAfter(
                LocalDateTime.now().toLocalDate().minusDays(1)));
    }
}

