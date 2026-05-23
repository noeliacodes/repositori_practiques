package ud8.bank.domain.service;

import ud8.bank.domain.entity.BankAccount;

public interface NotificationService {
    void sendNotification(BankAccount account, String message);
}