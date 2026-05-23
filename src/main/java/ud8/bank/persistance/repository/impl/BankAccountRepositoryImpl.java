package ud8.bank.persistance.repository.impl;

import ud8.bank.domain.entity.BankAccount;
import ud8.bank.persistance.dao.BankAccountDao;
import ud8.bank.persistance.repository.BankAccountRepository;

public class BankAccountRepositoryImpl implements BankAccountRepository {
    private final BankAccountDao dao;

    public BankAccountRepositoryImpl(BankAccountDao dao) {
        this.dao = dao;
    }

    @Override
    public BankAccount findByIBAN(String iban) {
        return dao.findByIBAN(iban);
    }

    @Override
    public boolean existsByIBAN(String iban) {
        return dao.findByIBAN(iban) != null;
    }

    @Override
    public BankAccount latest() {
        return dao.latest();
    }

    @Override
    public void save(BankAccount bank) {
        if (dao.findByIBAN(bank.getIban()) == null)
            dao.insert(bank);
        else
            dao.update(bank);
    }

    @Override
    public void delete(String iban) {
        dao.delete(iban);
    }
}
