package ud8.bank.domain.service;

import ud8.common.exception.ResourceNotFoundException;
import ud8.bank.domain.entity.BankAccount;

public interface BankAccountService {
    BankAccount findByIBAN(String iban) throws ResourceNotFoundException;
    BankAccount create();
    boolean update(BankAccount bank);
    boolean delete(String iban);

    boolean deposit(BankAccount account, double amount);
    boolean withdraw(BankAccount account, double amount);
    boolean transfer(BankAccount from, BankAccount to, double amount);
}