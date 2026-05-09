public class creator_factory {
    
}

//product
//concrete product
//factory
//concrete factory


interface Dish
{
    void prepare();
    void serve();

}

class Pizza implements Dish
{
    @Override
    public void prepare()
    {
        //dffs pizza
    }

    @Override
    public void serve()
    {
        //dfdkjfkdjkf pizza
    }
}

class Sushi implements Dish
{
    @Override
    public void prepare()
    {
        //dffs sushi
    }

    @Override
    public void serve()
    {
        //dfdkjfkdjkf sushi
    }
    
}


interface DishFactory
{
    Dish createDish();

}

class PizzaFactory implements DishFactory
{
    @Override
    public Dish createDish()
    {
        return new Pizza();
    }
}

class SushiFactory implements DishFactory
{
    @Override
    public Dish createDish()
    {
        return  new Sushi();

    }
}


//client code

public class Main
{
    public static void main(String[] args)
    {
        DishFactory pizzaFactory = new PizzaFactory();
        Dish pizza = pizzaFactory.createDish();
    }
}



/*Pros and Cons of the Factory Pattern
Pros:
The Factory Pattern encapsulates object creation logic, ensuring it remains hidden from client code. This promotes cleaner and more maintainable code.
It allows for the addition of new object types (Concrete Products) without altering existing code. New Concrete Factories can be introduced for creating these objects, enhancing code extensibility.
By centralizing object creation in factories, you can reuse the same factory logic across multiple parts of your application, reducing redundancy.
The Factory Pattern enforces uniform object creation following a common interface (Product). This consistency simplifies code maintenance and minimizes errors.
The Factory Pattern plays a significant role in dependency injection frameworks, allowing for the creation and management of dependencies.
Cons:
Implementing factories can raise the overall complexity of the codebase.
The Factory Pattern introduces an extra layer of abstraction, potentially confusing developers unfamiliar with the pattern. Adequate documentation and training may be necessary.
While client code is decoupled from Concrete Products, it remains tightly coupled with Concrete Factories. Significant changes in factory logic can impact multiple parts of the code.
The Factory Pattern is useful when object creation is complex or variable. In situations with straightforward creation processes and little variability, using the Factory Pattern may introduce unnecessary complexity.
Like any design pattern, the Factory Pattern can be overused. It’s essential to assess whether the pattern is the right solution for your specific problem. */



/*The primary difference is that the Factory Method creates a single 
product, while the Abstract Factory creates families of related or dependent products. Factory Method relies on inheritance and subclassing, whereas Abstract Factory relies on object composition and delegating creation to another class. */