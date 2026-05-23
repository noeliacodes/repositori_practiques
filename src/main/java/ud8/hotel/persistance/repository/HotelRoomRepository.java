package ud8.hotel.persistance.repository;

import ud8.hotel.domain.entity.HotelRoom;

public interface HotelRoomRepository {
    HotelRoom findByCode(String code);
    boolean existsByCode(String code);
    HotelRoom latest();
    void save(HotelRoom room);
    void delete(String code);
}