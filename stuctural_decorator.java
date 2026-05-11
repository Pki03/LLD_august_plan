public class stuctural_decorator {
    
}


// Component Interface
interface Pizza {
    String getDescription();
    double getCost();
}

// Concrete Component
class PlainPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Plain Pizza";
    }

    @Override
    public double getCost() {
        return 5.0;
    }
}

// Decorator
abstract class PizzaDecorator implements Pizza {
    protected Pizza decoratedPizza;

    public PizzaDecorator(Pizza pizza) {
        this.decoratedPizza = pizza;
    }
}

// Concrete Decorators
class CheeseDecorator extends PizzaDecorator {
    public CheeseDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return decoratedPizza.getDescription() + ", Cheese";
    }

    @Override
    public double getCost() {
        return decoratedPizza.getCost() + 1.5;
    }
}

class PepperoniDecorator extends PizzaDecorator {
    public PepperoniDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return decoratedPizza.getDescription() + ", Pepperoni";
    }

    @Override
    public double getCost() {
        return decoratedPizza.getCost() + 2.0;
    }
}

public class DecoratorMain {
    public static void main(String[] args) {
        // Create a plain pizza
        Pizza pizza = new PlainPizza();

        // Decorate the pizza with cheese and pepperoni
        pizza = new CheeseDecorator(pizza);
        pizza = new PepperoniDecorator(pizza);

        // Get the description and cost of the decorated pizza
        System.out.println("Description: " + pizza.getDescription());
        System.out.println("Cost: $" + pizza.getCost());
    }
}