public class behavioral_strategy {
    
}

/*
Defining the Strategy Pattern
The primary goal of the Strategy pattern is to define a set of algorithms, encapsulate them in their classes, 
and make them interchangeable within context objects. Through this encapsulation, 
the Strategy pattern promotes the open/closed principle — software entities should be open for extension but closed for modification.
Key Components of the Strategy Pattern
Context: The Context is the class that utilizes the Strategy. It references one of the strategies and 
can switch between them. The Context delegates the task to a Strategy object instead of implementing the algorithm directly, which makes the Context independent of the algorithm’s variations.
Strategy Interface: The Strategy interface declares a common method or set of algorithm execution techniques. This method serves as a contract that all Concrete Strategies must implement, ensuring that they can be used interchangeably by the Context.
Concrete Strategies: Concrete Strategies are classes that implement the Strategy interface. Each Concrete Strategy class provides a unique implementation of the algorithm, enabling a runtime decision based on the current strategy without requiring any change in the client code.
*/


// strategy
public interface Strategy
{
    void sort(List<Integer> numbers);
}

//concrete strategy
public class BubbleSortStrategy implements ConcreteStrategy
{
    @Override
    public void sort(List<Integer> numbers)
    {
        "dhsjidbis via bubble sort";
    }
}

public class QuickSortStrategy implements ConcreteStrategy
{
    @Override
    public void sort(List<Integer> numbers)
    {
        "dhsjidbis via quick sort";
    }
}

//context
public class Sorter
{
    private SortingStrategy strategy;

    public Sorter(SortingStrategy strategy)
    {
        this.strategy=strategy;
    }
    public void setStrategy(SortingStrategy strategy)
    {
         this.strategy = strategy;
    }

    public void sort()
    {
        "fhdkfhk";
    }

}

//client code
public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 3, 2, 8, 1);
        
        Sorter sorter = new Sorter(new BubbleSortStrategy());
        sorter.sortNumbers(numbers);

        // Switch to a different strategy
        sorter.setStrategy(new QuickSortStrategy());
        sorter.sortNumbers(numbers);
    }
}



/*
Benefits of Using the Strategy Pattern
Flexibility and Reusability:
By adhering to the Strategy pattern, algorithms can be changed or extended without modifying the existing client code. This flexibility promotes code reusability and reduces the risk of introducing bugs during modifications.
Separation of Concerns:
By delegating the algorithm’s execution to Strategy classes, the Context class is simplified and focused on its primary responsibility rather than the intricacies of various algorithm implementations.
Ease of Testing:
Individual strategies can be tested independently, enhancing the testability of code. Unit tests for each Concrete Strategy ensure that all algorithm implementations are properly evaluated in isolation.
Open for Extension:
New strategies can be easily introduced without affecting the existing system, thus adhering to the open/closed principle. This feature is particularly useful in evolving systems where new requirements frequently arise.
Drawbacks and Considerations
Increased Number of Classes:
While the Strategy pattern enhances flexibility and maintainability, it can lead to an increased number of classes, which might complicate the initial design. Proper documentation and naming conventions are crucial to managing this complexity.
Overhead:
Using the Strategy pattern might introduce some overhead due to the abstraction layers. It’s essential to weigh this overhead against the benefits of flexibility and code organization.
 */