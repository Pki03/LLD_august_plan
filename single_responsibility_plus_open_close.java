
//Open-Close Principle

public class PaymentProcessor
{
    public void processPayment(String type,double amount)
    {
        if(type.equals("xxx"))
        {
            System.out.println("xxx");
        }
        else if(type.equals("yyy"))
        {
            System.out.println("yyy");
        }
        else
        {
            System.out.println("zzz");
        }
    }
}

// the above is the problem 

public interface PaymentGateway
{
    void process(double amount);
    
}

//baaki saare isse import karenge

public class CreditCardGateway implements PaymentGateway
{
    @Override
    public void process(double amount)
    {
        System.out.println(amount);
    }
    
}

public class yyGateway implements PaymentGateway
{
    @Override
    public void process(double amount)
    {
        Sytem.out.println(amount);
    }
}

//in the solution what we do is use a orchestrator

public class PaymentProcessor
{
    private final PaymentGateway gateway;
    
    public PaymentProcessor(PaymentGateway gateway)
    {
        this.gateway=gateway;
    }
    
    public void process(double amount)
    {
        gateway.process(amount);
    }
}

public class Main{
    public static void main(String[] args)
    {
        PaymentProcessor processor=new PaymentProcessor(new CryptoGateway());
        processor.process(32898392);
        
        
    }
}





// Single Responsnility Principle

public class UserService
{
    public void saveUser(User user)
    {
        Conection con = DriverManager.hjdhsjkdh
        smt= skjdksd
        stm.print();
        stmt.print();
    }
    
    public void sendEmail(User user)
    {
        Props props= new Properties();
        props.put();
        Transport.send(message);
    }
    
    public void generate(User user)
    {
        jdskjkdsjs
        djskdjkjdk
        djskjdskd
    }
    
    
// here if the error comes in userservice then even sendEmail and generate also gets fucked

//solution

//each class has one reason to change

public class UserRepository
{
    public void save(User user)
    {
        jksjdkdjkwjdk
    }
}

public class EmailService
{
    public void sendEmail(User user)
    {
        djkjskdjs
    }
}

public class UserController
{
    private final UserRepository repo;
    private final EmailService emailservice;
    
    public UserController(UserRepository repo,Email emailService)
    {
        this.repo=repo;
        this.emailservice=emailservice;
    }
    
}
    
    
    
}


// Q1: What is SRP?
// A: A class should have only ONE reason to change (one actor/stakeholder).

// Q2: What does “reason to change” mean?
// A: A specific business concern or actor (e.g., DB team, email team).

// Q3: Why is SRP important in production?
// A: Reduces side effects — changing one feature won't break unrelated logic.

// Q4: Identify violation:
// class UserService { saveUser(), sendEmail(), generateReport() }
// A: Violates SRP because it handles persistence, communication, reporting.

// Q5: How to fix SRP violation?
// A: Split into:
// - UserRepository (DB)
// - EmailService (email)
// - ReportService (reporting)

// Q6: What is an "actor" in SRP?
// A: A stakeholder/team that can demand a change in the class.

// Q7: Is having multiple methods always SRP violation?
// A: NO — if all methods belong to same responsibility/domain.

// Q8: Example where SRP is NOT violated:
// class User { validateEmail(), formatName() }
// A: Both belong to User domain → valid SRP.

// Q9: Real-world SRP violation impact?
// A: Email bug breaks user registration → high coupling.

// Q10: Interview one-liner for SRP:
// A: "If multiple actors can change a class, SRP is violated."






// Q1: What is OCP?
// A: Open for extension, closed for modification.

// Q2: What does it mean practically?
// A: Add new behavior without modifying existing code.

// Q3: Identify violation:
// if(type == "A") else if(type == "B")
// A: Violates OCP — every new type requires modifying code.

// Q4: Why is this bad?
// A: Risk of breaking existing functionality (regression bugs).

// Q5: How to fix OCP violation?
// A: Use abstraction (interface/abstract class).

// Q6: Example fix:
// interface Payment { process(); }
// class CardPayment, UpiPayment, CryptoPayment
// A: Add new class instead of modifying existing.

// Q7: What design pattern supports OCP?
// A: Strategy Pattern (most common answer in interviews).

// Q8: What is the core idea behind OCP?
// A: Replace conditionals with polymorphism.

// Q9: Real-world benefit?
// A: New features added safely without touching tested code.

// Q10: When NOT to use OCP?
// A: Small systems, low change probability → avoid over-engineering.