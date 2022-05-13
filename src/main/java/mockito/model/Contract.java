package mockito.model;

import java.time.LocalDateTime;

public class Contract {

    private int personId;
    LocalDateTime expirationDate;

    public Contract(int personId, LocalDateTime expirationDate) {
        this.personId = personId;
        this.expirationDate = expirationDate;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "personId=" + personId +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
