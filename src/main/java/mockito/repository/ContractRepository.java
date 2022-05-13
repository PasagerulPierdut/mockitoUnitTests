package mockito.repository;

import mockito.model.Contract;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ContractRepository {

    List<Contract> contractList = List.of(
            new Contract(1, LocalDateTime.of(2028, 5, 16, 10, 15)),
            new Contract(2, LocalDateTime.of(2028, 5, 16, 10, 15)),
            new Contract(3, LocalDateTime.of(2028, 5, 16, 10, 15)),
            new Contract(4, LocalDateTime.of(2028, 5, 16, 10, 15)),
            new Contract(5, LocalDateTime.of(2028, 5, 16, 10, 15)),
            new Contract(6, LocalDateTime.of(2022, 5, 12, 10, 15))
    );

    public boolean existsById(int id) {
        return contractList.stream()
                .anyMatch(contract -> contract.getPersonId() == id);
    }

    public Contract getContractById(int id) {
        Optional<Contract> opt = contractList.stream()
                .filter(contract -> contract.getPersonId() == id)
                .findAny();
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new NoSuchElementException("Object not present");
        }
    }
}
