package ud8.bank.domain.service.impl;

import ud8.bank.domain.entity.BankAccount;
import ud8.bank.domain.service.NotificationService;

public class NotificationServiceImpl implements NotificationService {
    @Override
    public void sendNotification(BankAccount account, String message) {
        System.out.printf("%s: %s%n", account.getIban(), message);
    }
}
