public class Vehicle  {

    String ownerName;
    String plateNumber;
    String carType;
    String finishDayInsu;


    public Vehicle(String ownerName, String plateNumber, String carType, String finishDayInsu) {
        this.ownerName = ownerName;
        this.plateNumber = plateNumber;
        this.carType = carType;
        this.finishDayInsu = finishDayInsu;    }


    public String getPlateNumber() { return plateNumber;  }

    public String getOwnerName()   { return ownerName;    }

    public String getFinishDayInsu() {  return finishDayInsu; } }