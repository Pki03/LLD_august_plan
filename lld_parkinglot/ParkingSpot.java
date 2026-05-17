package lld_parkinglot;

public interface ParkingSpot {

   

    void occupy(Vehicle vehicle);

    void vacate();

    int getSpotNumber();

    VehicleSize getSize();


    
}

public class CompactSpot implements ParkingSpot
{
    private int spotNumber;
    private Vehicle vehicle;

    CompactSpot(int spotNumber, Vehicle vehicle)
    {
        this.spotNumber=spotNumber;
        this.vehicle=vehicle;
    }

    @Override
    public int getSpotNumber()
    {
        return spotNumber;
    }

    @Override
    public boolean isAvailable()
    {
        return vehicle=null;
    }

    @Override
    public void occupy(Vehicle vehicle)
    {
        if(isAvailable)
        {
            this.vehicle=vehicle;
        }
        else
        {
            //spot occupied
        }
    }

    @Override
    public void vacate()
    {
        this.vehicle=null;
    }

    @Override
    public VehicleSize getSize()
    {
        return VehicleSize.SMALL:
    }
}


//regularSpot, oversizedSpot for the same is MEDIUM, LARGE

