public class Vehicle implements Comparable<Vehicle> {

    private String ownerName;
    private String plateNumber;
    private String carType;
    private String finishDayInsu;


    public Vehicle(String ownerName, String plateNumber, String carType, String finishDayInsu) {
        this.ownerName = ownerName;
        this.plateNumber = plateNumber;
        this.carType = carType;
        this.finishDayInsu = finishDayInsu;
    }


    public String getPlateNumber() {
        return plateNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getFinishDayInsu() {
        return finishDayInsu;
    }

    @Override
    public int compareTo(Vehicle o) {
        return this.plateNumber.compareTo(o.plateNumber);
    }
}