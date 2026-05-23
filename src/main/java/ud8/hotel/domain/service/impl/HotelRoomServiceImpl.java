package ud8.hotel.domain.service.impl;

import ud8.common.exception.ResourceNotFoundException;
import ud8.hotel.domain.entity.HotelRoom;
import ud8.hotel.domain.service.HotelRoomService;
import ud8.hotel.domain.service.NotificationService;
import ud8.hotel.persistance.repository.HotelRoomRepository;

public class HotelRoomServiceImpl implements HotelRoomService {
    private final HotelRoomRepository repository;
    private final NotificationService notificationService;

    public HotelRoomServiceImpl(HotelRoomRepository repository, NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    @Override
    public HotelRoom findByCode(String code) throws ResourceNotFoundException {
        HotelRoom room = repository.findByCode(code);
        if (room == null)
            throw new ResourceNotFoundException("Hotel room not found");
        return room;
    }

    @Override
    public HotelRoom create() {
        String newCode = generateCode();
        HotelRoom room = new HotelRoom(newCode);
        repository.save(room);
        notificationService.sendNotification(room, "Your new hotel room has been registered!");
        return room;
    }

    public String generateCode() {
        HotelRoom latest = repository.latest();
        int number = latest == null ? 1 : latest.getRoomNumber() + 1;
        return String.format("HAB%0" + HotelRoom.ROOM_NUMBER_LENGTH + "d", number);
    }

    @Override
    public boolean update(HotelRoom room) {
        if (!repository.existsByCode(room.getCode())) {
            return false;
        }
        repository.save(room);
        return true;
    }

    @Override
    public boolean delete(String code) {
        if (!repository.existsByCode(code)) {
            return false;
        }
        repository.delete(code);
        return true;
    }

    @Override
    public boolean addCharge(HotelRoom room, double amount) {
        if (amount <= 0) {
            return false;
        }
        room.setConsumption(room.getConsumption() + amount);
        repository.save(room);
        return true;
    }

    @Override
    public boolean payCharge(HotelRoom room, double amount) {
        if (amount <= 0 || room.getConsumption() < amount) {
            return false;
        }
        room.setConsumption(room.getConsumption() - amount);
        repository.save(room);
        return true;
    }

    @Override
    public boolean moveCharge(HotelRoom from, HotelRoom to, double amount) {
        if (amount <= 0 || from.getConsumption() < amount) {
            return false;
        }
        from.setConsumption(from.getConsumption() - amount);
        to.setConsumption(to.getConsumption() + amount);

        repository.save(from);
        repository.save(to);

        notificationService.sendNotification(from, "Charge of " + amount + " moved to " + to.getCode());
        notificationService.sendNotification(to, "Charge of " + amount + " moved from " + from.getCode());
        return true;
    }
}