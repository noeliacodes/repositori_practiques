package ud8.hotel.domain.service;

import ud8.hotel.domain.entity.HotelRoom;

public interface NotificationService {
    void sendNotification(HotelRoom room, String message);
}