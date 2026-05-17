package lld_parkinglot;

public interface Vehicle
{
    String getLicensePlate();
    VehicleSize getSize();

}

private class Car implements Vehicle
{

    private String licensePlate;

    public Car(String licensePlate)
    {
        this.licensePlate=licensePlate;
    }

    @Override
    public String getLicensePlate()
    {
        return this.licensePlate;
    }

    @Override
    public VehicleSize getSize()
    {
        return VehicleSize.MEDIUM;
    }

}

// similarly for Bike, Truck

public enum VehicleSize
{
    SMALL,
    MEDIUM,
    LARGE
}
