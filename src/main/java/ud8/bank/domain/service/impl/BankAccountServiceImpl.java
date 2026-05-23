package ud8.bank.domain.service.impl;

import ud8.common.exception.ResourceNotFoundException;
import ud8.bank.domain.entity.BankAccount;
import ud8.bank.domain.service.BankAccountService;
import ud8.bank.domain.service.NotificationService;
import ud8.bank.persistance.repository.BankAccountRepository;

public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository repository;
    private final NotificationService notificationService;

    public BankAccountServiceImpl(BankAccountRepository repository, NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    @Override
    public BankAccount findByIBAN(String iban) throws ResourceNotFoundException {
        BankAccount bank = repository.findByIBAN(iban);
        if (bank == null)
            throw new ResourceNotFoundException("Bank account not found");
        return bank;
    }

    @Override
    public BankAccount create() {
        String newIBAN = generateIBAN();
        BankAccount account = new BankAccount(newIBAN);
        repository.save(account);
        notificationService.sendNotification(account, "Your new bank account has been created!");
        return account;
    }

    public String generateIBAN() {
        BankAccount latest = repository.latest();
        int accountNumber = latest == null ? 1 : latest.getAccountNumber() + 1;
        return String.format("ES%0" + BankAccount.ACCOUNT_NUMBER_LENGTH + "d", accountNumber);
    }

    @Override
    public boolean update(BankAccount bank) {
        if (!repository.existsByIBAN(bank.getIban())) {
            return false;
        }

        repository.save(bank);
        return true;
    }

    @Override
    public boolean delete(String iban) {
        if (!repository.existsByIBAN(iban)) {
            return false;
        }

        repository.delete(iban);
        return true;
    }

    @Override
    public boolean deposit(BankAccount account, double amount) {
        if (amount <= 0) {
            return false;
        }
        account.setBalance(account.getBalance() + amount);

        repository.save(account);
        return true;
    }

    @Override
    public boolean withdraw(BankAccount account, double amount) {
        if (amount <= 0 || account.getBalance() < amount) {
            return false;
        }
        account.setBalance(account.getBalance() - amount);

        repository.save(account);
        return true;
    }

    @Override
    public boolean transfer(BankAccount from, BankAccount to, double amount) {
        if (amount <= 0 || from.getBalance() < amount) {
            return false;
        }
        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        repository.save(from);
        repository.save(to);

        notificationService.sendNotification(from, "Transfer of " + amount + " to " + to.getIban());
        notificationService.sendNotification(to, "Transfer of " + amount + " from " + from.getIban());
        return true;
    }
}
