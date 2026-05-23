package ud8.bank.domain.entity;

import java.util.Objects;

public class BankAccount {
    public final static int ACCOUNT_NUMBER_LENGTH = 2;

    private final String iban;
    private double balance;

    public BankAccount(String iban) {
        this(iban, 0);
    }

    public BankAccount(String iban, double balance) {
        this.iban = iban;
        this.balance = balance;
    }

    public String getIban() {
        return iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return Integer.parseInt(iban.substring(2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankAccount that)) return false;
        return Double.compare(balance, that.balance) == 0 && Objects.equals(iban, that.iban);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "iban='" + iban + '\'' +
                ", balance=" + balance +
                '}';
    }
}