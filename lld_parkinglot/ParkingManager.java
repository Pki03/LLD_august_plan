package lld_parkinglot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingManager 
{
    private final Map<VehicleSize,List<ParkingSpot>> availableSpots;
    private final Map<Vehicle, ParkingSpot> vehicletoSpotMap;

    public ParkingManger(Map<VehicleSize,List<ParkingSpot>> availableSpots, Map<Vehicle,ParkingSpot> vehicletoSpotMap)
    {
        this.availableSpots=availableSpots;
        this.vehicleSpotMap=new HashMap<>();

    }

    public ParkingSpot findSpotForVehicle(Vehicle vehicle)
    {
        VehicleSize vehicleSize= vehicle.getSize();

        for(VehicleSize size : vehicleSize.values())
        {
            if(size.ordinal()>=vehicleSize.ordinal())
            {
                List<ParkingSpot> spots = availableSpots.get(size);
                for(ParkingSpot spot: spots)
                {
                    if(spot.iAvailable())
                    {
                        return spot;
                    }
                }
            }
        }
        return null;
    }

    public ParkingSpot parkVehicle(Vehicle vehicle)
    {
        ParkingSpot spot = findSpotForVehicle(vehicle);
        if(spot!=null)
        {
            spot.occupy(vehicle);
            vehicleToSpotMap.put(vehicle, spot);
            availableSpots.get(spot.getSize().remove(spot));

            return spot;
        }
    }

    public void unparkVehicle(Vehicle vehicle) {
        ParkingSpot spot = vehicleToSpotMap.remove(vehicle);
        if (spot != null) {
            spot.vacate();
            availableSpots.get(spot.getSize()).add(spot);
        }
    }

    
}
