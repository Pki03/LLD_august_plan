package lld_parkinglot;


/*Faster Parking Spot Management
The mapping we currently have is one-way: from Vehicle to ParkingSpot. This allows us to quickly find the parking spot assigned to a specific vehicle. But what if we want to find which vehicle is parked in a specific spot? Without a reverse mapping, we would need to search through all parking spots, which isn’t efficient. Can we do better?

We can enhance this by introducing another HashMap, called spotToVehicleMap, to track the reverse mapping from ParkingSpot to Vehicle.

With this approach, we use two HashMaps:

vehicleToSpotMap: Tracks the parking spot for each vehicle.
spotToVehicleMap: Tracks the vehicle parked in each spot.
Below is the updated ParkingManager class. */
public class ParkingManager {
    private final Map<VehicleSize, List<ParkingSpot>> availableSpots;
    private final Map<Vehicle, ParkingSpot> vehicleToSpotMap;
    private final Map<ParkingSpot, Vehicle> spotToVehicleMap;

    // Create Parking Manager based on a given map of available spots
    public ParkingManager(Map<VehicleSize, List<ParkingSpot>> availableSpots) {
        this.availableSpots = availableSpots;
        this.vehicleToSpotMap = new HashMap<>();
        this.spotToVehicleMap = new HashMap<>();
    }

    public ParkingSpot findSpotForVehicle(Vehicle vehicle) {
        // No change in the method
    }

    public ParkingSpot parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = findSpotForVehicle(vehicle);
        if (spot != null) {
            spot.occupy(vehicle);
            // Record bidirectional mapping
            vehicleToSpotMap.put(vehicle, spot);
            spotToVehicleMap.put(spot, vehicle);
            // Remove the spot from the available list
            availableSpots.get(spot.getSize()).remove(spot);
            return spot; // Parking successful
        }
        return null; // No spot found for this vehicle
    }

    public void unparkVehicle(Vehicle vehicle) {
        ParkingSpot spot = vehicleToSpotMap.remove(vehicle);
        if (spot != null) {
            spotToVehicleMap.remove(spot);
            spot.vacate();
            availableSpots.get(spot.getSize()).add(spot);
        }
    }

    // Find vehicle's parking spot
    public ParkingSpot findVehicleBySpot(Vehicle vehicle) {
        return vehicleToSpotMap.get(vehicle);
    }

    // Find which vehicle is parked in a spot
    public Vehicle findSpotByVehicle(ParkingSpot spot) {
        return spotToVehicleMap.get(spot);
    }
}