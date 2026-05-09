public class creator_abstractfactory {
    
}


//abstact products

public interface Chair {
    void sitOn();  // Common functionality for all chairs
}

public interface Table {
    void use();    // Common functionality for all tables
}

//concrete products

// Concrete implementation of Modern Chair
public class ModernChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("Sitting on a modern chair!");
    }
}

// Concrete implementation of Modern Table
public class ModernTable implements Table {
    @Override
    public void use() {
        System.out.println("Using a modern table!");
    }
}

// Concrete implementation of Victorian Chair
public class VictorianChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("Sitting on a Victorian chair!");
    }
}

// Concrete implementation of Victorian Table
public class VictorianTable implements Table {
    @Override
    public void use() {
        System.out.println("Using a victorian table!");
    }
}

//abstract factory

public interface FurnitureFactory {
    Chair createChair();  // Method to create a Chair
    Table createTable();  // Method to create a Table
}

//concrete factory

// Factory for creating modern furniture
public class ModernFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new ModernChair(); // Create a modern chair
    }

    @Override
    public Table createTable() {
        return new ModernTable(); // Create a modern table
    }
}

public class VictorianFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new VictorianChair();
    }

    @Override
    public Table createTable() {
        return new VictorianTable();
    }
}

//client code

public class Main {
    public static void main(String[] args) {
        // Create modern furniture using the ModernFurnitureFactory
        FurnitureFactory modernFactory = new ModernFurnitureFactory();
        Chair modernChair = modernFactory.createChair();
        Table modernTable = modernFactory.createTable();

        // Use the modern furniture
        modernChair.sitOn();
        modernTable.use();

        // Create Victorian furniture using the VictorianFurnitureFactory
        FurnitureFactory victorianFactory = new VictorianFurnitureFactory();
        Chair victorianChair = victorianFactory.createChair();
        Table victorianTable = victorianFactory.createTable();

        // Use the Victorian furniture
        victorianChair.sitOn();
        victorianTable.use();
    }
}
