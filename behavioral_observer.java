public class behavioral_observer {
    
}

//one to many dependency bw objects so that when one object changes its state , others get notified abt itand updated automatically.

/*
Components of the Observer Design Pattern
Subject: The subject maintains a list of observers and notifies them of state changes.
Observer: The observer interface defines the contract for concrete observer classes.
ConcreteSubject: A class that implements the subject interface and manages the observers.
ConcreteObserver: A class that implements the observer interface and receives notifications.
*/


//observer
public interface StockObserver {
    void update(String stockSymbol, double stockPrice);
}

public class Investor implements StockObserver{

    private String name;

    public Investor(String name)
    {
        this.name=name;
    }

    @Override
    public void update(String stockSymbol, double stockPrice)
    {
        System.out.println(name + "mbkbvgugvuivi");
    }
}


//subject
public interface StockMarket
{
    void registerObserver(StockObserver observer);
    void removeObserver(StockObserver observer);
    void notifyObserver(String stockSymbol, StockObserver observer);
}

public class StockMarketImpl implements StockMarket
{
    private List<StockObserver> observers = new ArrayList<>();

    @Override
    public void registerObserver(stockObserver observer)
    {
        observers.add(observer);
    }

    @Override 
    public void removeObserver(stockObserver observer)
    {
        observers.remove(observer);
    }

    @Override 
    public void notifyObservers(String stockSymbol, stockObserver observer)
    {
        for (StockObserver observer : observers) {
            observer.update(stockSymbol, stockPrice);
        }
    }

    public void setStockPrice(String stockSymbol, double stockPrice) {
        notifyObservers(stockSymbol, stockPrice);
    }
}


public class Main {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarketImpl();

        StockObserver investor1 = new Investor("Alice");
        StockObserver investor2 = new Investor("Bob");

        stockMarket.registerObserver(investor1);
        stockMarket.registerObserver(investor2);

        stockMarket.setStockPrice("INFY", 1250.0); // Both investors receive updates
        stockMarket.setStockPrice("TCS", 2500.0); // Both investors receive updates

        stockMarket.removeObserver(investor1);

        stockMarket.setStockPrice("WIPRO", 700.0); // Only investor2 receives the update
    }
}




