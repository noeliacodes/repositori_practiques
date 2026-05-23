package ud8.bank.persistance.repository;

import ud8.bank.domain.entity.BankAccount;

public interface BankAccountRepository {
    BankAccount findByIBAN(String iban);
    boolean existsByIBAN(String iban);
    BankAccount latest();
    void save(BankAccount bank);
    void delete(String iban);
}
