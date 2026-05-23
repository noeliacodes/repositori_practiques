package ud8.bank.persistance.dao;

import ud8.bank.domain.entity.BankAccount;

public interface BankAccountDao {
    BankAccount findByIBAN(String iban);
    BankAccount latest();
    int insert(BankAccount bank);
    int update(BankAccount bank);
    int delete(String iban);
}
