public class Vehicles  {

    String ownerName;
    String plateNumber;
    String carType;
    String finishDayInsu;


    public Vehicles(String ownerName, String plateNumber, String carType, String finishDayInsu) {
        this.ownerName = ownerName;
        this.plateNumber = plateNumber;
        this.carType = carType;
        this.finishDayInsu = finishDayInsu;
    }


    public String getPlateNumber() {
        return plateNumber;
    }

    /* public int Compare(Vehicles d, Vehicles d1) {
         return d.getPlateNumber().compareTo(d1.getPlateNumber());
     }
 */
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getFinishDayInsu() {
        return finishDayInsu;
    }



}


