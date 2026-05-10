class Singleton
{
    private static volatile Singleton single_instance=null;

    private String s;

    private Singleton()
    {
        s="thejhjkfhsahkfhka singleton";
    }


    private static Singleton getInstance()
    {
        if(single_instance==null)
        {
            synchronised(Singleton.class)
            {
                if(single_instance==null)
                {
                    single_instance=new Singleton();
                }
            }
        }
        return single_instance();
    }


}


/*
# Singleton Design Pattern in Java

## What is it?

A Singleton class is a class that allows **only one object (instance) to ever exist**. No matter how many times you try to create a new object, you always get back the same one.

Think of it like a **President of a country** — there's only one at a time, and everyone refers to the same person.

---

## Why do we need it?

| Benefit | Real-world example |
|---|---|
| **Memory efficiency** | Create DB connection once, reuse it |
| **Resource control** | One logger writing to a file |
| **Thread safety** | Only one thread accesses a shared resource |

Common real-world uses: database connections, logging, configuration settings, thread pools.

---

## How to Build One — Step by Step

### Step 1: Private Constructor
```java
private Singleton() {
    s = "Hello I am a string";
}
```
Making the constructor `private` means **nobody outside the class can do** `new Singleton()`. This blocks unlimited object creation.

### Step 2: Private Static Variable
```java
private static Singleton single_instance = null;
```
- `static` → belongs to the class, not any object
- Starts as `null`, holds the one instance once created
- Shared across ALL references

### Step 3: Public Static `getInstance()` Method
```java
public static synchronized Singleton getInstance() {
    if (single_instance == null)        // first time? create it
        single_instance = new Singleton();
    return single_instance;             // always return the same one
}
```
This is the **only door** to get the object. The `if` check ensures the object is created **only once ever**.

---

## Visual Flow

```
First call:   getInstance() → single_instance is null → CREATE new object → return it
Second call:  getInstance() → single_instance exists  → SKIP creation    → return SAME object
Third call:   getInstance() → single_instance exists  → SKIP creation    → return SAME object
```

So `x`, `y`, `z` all point to the **exact same memory address**:
```
x ──┐
y ──┼──► [ Single Singleton Object in Heap ]
z ──┘
```
That's why all three hashcodes are identical in the output.

---

## The `synchronized` Keyword

```java
public static synchronized Singleton getInstance()
```
In multi-threaded apps, two threads could both check `single_instance == null` simultaneously and both create objects, breaking the pattern. `synchronized` ensures **only one thread enters this method at a time**, preventing that race condition.

---

## Two Forms

| Form | When object is created | 
|---|---|
| **Early Instantiation** | At class load time (immediately) |
| **Lazy Instantiation** | Only when first requested (the `if null` check approach) |

The examples above use **Lazy Instantiation** — more memory efficient since the object is only created if actually needed.

---

## Key Takeaway: Normal Class vs Singleton Class

| | Normal Class | Singleton Class |
|---|---|---|
| Object creation | `new ClassName()` | `ClassName.getInstance()` |
| Number of objects | Unlimited | Exactly **one** |
| Constructor | Public | Private |
| Lifecycle | Dies with scope | Lives for entire app lifetime |

---

## The "Shared State" Effect (Example 2)

Since all variables point to the same object, **changing state through one variable affects all others**:

```java
x.s = (x.s).toUpperCase();   // changes the ONE shared object
System.out.println(y.s);     // y also shows UPPERCASE — same object!
```

This is both the **power** and the **risk** of Singleton — great for shared resources, but be careful about unintended state changes.
*/



/*
# Double-Checked Locking in Singleton

## The Problem with Simple `synchronized`

The basic synchronized version works, but has a **performance issue:**

```java
public static synchronized Singleton getInstance() {
    if (single_instance == null)
        single_instance = new Singleton();
    return single_instance;
}
```

Every single time any thread calls `getInstance()`, it has to **wait and acquire the lock** — even after the object is already created. This is **unnecessarily slow**.

> 99% of the time, the object already exists. Why lock every time just to return it?

---

## The Solution — Double-Checked Locking

```java
public static Singleton getInstance() {
    
    // FIRST CHECK — no lock yet
    if (single_instance == null) {                    // Line 1
        
        synchronized (Singleton.class) {             // Line 2 — lock only here
            
            // SECOND CHECK — inside the lock
            if (single_instance == null) {            // Line 3
                single_instance = new Singleton();    // Line 4
            }
        }
    }
    return single_instance;                          // Line 5
}
```

It checks **twice** — once without locking, once with. That's why it's called **Double-Checked Locking**.

---

## Why Two Checks? — The Logic

### First Check (Line 1) — Without Lock
```java
if (single_instance == null)
```
- If object **already exists** → skip everything, just return it ✅
- If object **doesn't exist** → enter the synchronized block
- This avoids locking in the common case (object already created)

### Synchronized Block (Line 2)
```java
synchronized (Singleton.class)
```
- Only entered when object **might not exist yet**
- Only **one thread** can be inside this block at a time

### Second Check (Line 3) — Inside Lock
```java
if (single_instance == null)
```
This is the **critical part** — here's why it's needed:

```
Thread A checks Line 1  → null, enters sync block
Thread B checks Line 1  → null, WAITS at sync block

Thread A creates object, exits sync block
Thread B now enters sync block...

Without 2nd check → Thread B creates ANOTHER object ❌
With 2nd check    → Thread B sees object exists, skips ✅
```

---

## Step-by-Step Visual

```
SCENARIO: Thread A and Thread B call getInstance() simultaneously

FIRST TIME (object doesn't exist yet):

Thread A                          Thread B
────────                          ────────
Check 1: null? YES                Check 1: null? YES
Enter sync block ✓                WAITING at sync block...
Check 2: null? YES   
Create object ✅                  
Exit sync block                   Enter sync block ✓
                                  Check 2: null? NO → skip ✅
                                  Exit sync block

Both return the SAME object ✅


EVERY TIME AFTER (object exists):

Thread A                          Thread B
────────                          ────────
Check 1: null? NO                 Check 1: null? NO
↓                                 ↓
Return existing object ✅         Return existing object ✅

NO LOCK ACQUIRED — super fast ⚡
```

---

## The `volatile` Keyword — Must Use This!

```java
private static volatile Singleton single_instance = null;
```

Without `volatile`, double-checked locking can **still break** due to CPU instruction reordering.

### What is instruction reordering?

When the JVM creates an object (`new Singleton()`), it actually does **3 steps internally:**

```
Step 1: Allocate memory
Step 2: Initialize the object
Step 3: Assign memory address to single_instance
```

The CPU/JVM can **reorder** these steps for optimization:

```
Step 1: Allocate memory
Step 3: Assign memory address  ← assigned BEFORE initialization!
Step 2: Initialize the object
```

### Why this breaks without `volatile`:

```
Thread A: Allocates memory
Thread A: Assigns address to single_instance (NOT initialized yet!)
Thread B: Check 1 → sees single_instance is NOT null
Thread B: Returns the object — but it's HALF INITIALIZED 💥
Thread A: Finishes initializing...too late!
```

### `volatile` fixes this by:
- Preventing instruction reordering
- Ensuring every thread reads the **latest value** from main memory, not CPU cache

---

## Complete Correct Implementation

```java
class Singleton {
    
    // volatile is MANDATORY
    private static volatile Singleton single_instance = null;
    
    private String s;
    
    // Private constructor
    private Singleton() {
        s = "I am the only instance!";
    }
    
    // Double-checked locking
    public static Singleton getInstance() {
        
        if (single_instance == null) {               // 1st check — no lock
            synchronized (Singleton.class) {
                if (single_instance == null) {       // 2nd check — with lock
                    single_instance = new Singleton();
                }
            }
        }
        return single_instance;
    }
}
```

---

## Comparison of All Approaches

| Approach | Thread Safe | Performance | 
|---|---|---|
| No synchronization | ❌ Not safe | ⚡ Fast |
| Full `synchronized` method | ✅ Safe | 🐢 Slow (locks every time) |
| Double-checked locking | ✅ Safe | ⚡ Fast (locks only once) |

---

## Summary in One Line

> **Check without lock → if needed, lock → check again inside lock → create if still needed**

This gives you the **safety of synchronization** with the **speed of no synchronization** — the best of both worlds. */