# Penguin Pizza Kitchen

Object-oriented pizza order system for the Data Structures course extra project.

## Project structure

```
src/main/java/com/pizzeria/
├── Main.java                          - entry point, console menu
├── model/
│   ├── ingredients/
│   │   ├── Ingredient.java            - abstract base class
│   │   ├── Base.java
│   │   ├── Sauce.java
│   │   └── Topping.java
│   ├── Pizza.java                     - uses Stack for ingredient history
│   └── Order.java                     - single customer order
├── controller/
│   └── KitchenController.java         - manages queue + active pizza
└── util/
    ├── OrderGenerator.java            - generates sample orders
    └── IngredientCatalog.java         - available ingredients
```

## Data structures used

- **Queue** (`LinkedList` as `Queue<Order>`) — orders are processed FIFO inside `KitchenController`.
- **Stack** (`Stack<Ingredient>`) — ingredients are added with `push` and removed (undo) with `pop` inside `Pizza`.

## How to compile and run

```bash
# Compile
javac -d out $(find src -name "*.java")

# Run
java -cp out com.pizzeria.Main
```

Requires Java 17+.

## Typical session

1. Option 1 — generate sample orders (loads 3 orders into the queue).
2. Option 2 — load the next order from the queue.
3. Option 3 — see what ingredients the order requires.
4. Option 4 — add ingredients one by one.
5. Option 5 — undo the last ingredient if you made a mistake.
6. Option 7 — submit; the system compares your pizza with the order and shows the score.
