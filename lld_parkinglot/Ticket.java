package lld_parkinglot;

public class Ticket {

    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public Ticket(String ticketId, Vehicle vehicle , ParkingSpot parkingSpot, LocalDateTime entryTime)
    {
        this.ticketId=ticketId;
        this.vehicle=vehicle;
        this.parkingSpot=parkingSpot;
        this.entryTime=entryTime;

    }


    public BigDecimal calculateParkingDuration()
    {
        return newBigDecimal(entryTime, exitTime i.e LocalDateTime :now);
    }

    
}
