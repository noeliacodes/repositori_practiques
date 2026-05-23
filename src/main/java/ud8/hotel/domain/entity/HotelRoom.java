package ud8.hotel.domain.entity;

public class HotelRoom {
    public static final int ROOM_NUMBER_LENGTH = 2;

    private String code;
    private double consumption;

    public HotelRoom(String code) {
        this.code = code;
        this.consumption = 0.0;
    }

    public HotelRoom(String code, double consumption) {
        this.code = code;
        this.consumption = consumption;
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public double getConsumption() { return consumption; }
    public void setConsumption(double consumption) { this.consumption = consumption; }

    public int getRoomNumber() {
        return Integer.parseInt(code.substring(3));
    }
}