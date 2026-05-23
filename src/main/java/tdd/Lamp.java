package tdd;

public class Lamp {
    private double consumption;
    private boolean isOn;


    //constructor


    public Lamp(double consumption, boolean isOn) {
        this.consumption = consumption;
        this.isOn = isOn;
    }

    //getters

    public double getConsumption() {
        return consumption;
    }

    public boolean isOn() {
        return isOn;
    }

    //methods

    public void turnOn() {
        isOn = true;

    }

    public void turnOff() {
        isOn = false;


    }

    //method toggle

    public void toggle() {
        if (this.isOn == false) {
            isOn = true; /*REFACTOR A TURN ON */
        } else {

            isOn = false;/*REFACTOR TURN OFF */
        }
    }

    public double calculateConsumption(double seconds) {
        if (!this.isOn()) {
            return 0.0;
        }
        double horas = seconds/3600;
            return consumption*horas;

    }
}