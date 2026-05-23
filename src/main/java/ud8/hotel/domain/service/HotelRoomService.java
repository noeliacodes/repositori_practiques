package ud8.hotel.domain.service;

import ud8.common.exception.ResourceNotFoundException;
import ud8.hotel.domain.entity.HotelRoom;

public interface HotelRoomService {
    HotelRoom findByCode(String code) throws ResourceNotFoundException;
    HotelRoom create();
    boolean update(HotelRoom room);
    boolean delete(String code);
    boolean addCharge(HotelRoom room, double amount);
    boolean payCharge(HotelRoom room, double amount);
    boolean moveCharge(HotelRoom from, HotelRoom to, double amount);
}