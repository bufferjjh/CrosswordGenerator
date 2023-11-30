# CrosswordGenerator
Generates crosswords with multiple solutions

## Creating a Puzzle structure

```java
//create initial board state
Puzzle p = new Puzzle(9); //9 x 9 board
p.insertWord("    ", 0, 0, true); //inserts 4 empty cells starting from (0,0) going down since isDown is true 
p.insertWord("      ", 2, 0, false); //inserts 6 empty cells starting from (2,0) going across since isDown is false
p.insertWord("      ", 1, 3, true); //inserts 6 empty cells starting from (1,3) going down since isDown is true
p.insertWord("      ", 4, 2, false); //inserts 6 empty cells starting from (4,2) going across since isDown is false
p.insertWord("      ",1,5,true); //inserts 6 empty cells starting from (1,5) going down since isDown is true
p.insertWord("     ", 6,2, false); //inserts 5 empty cells starting from (6,2) going across since isDown is false
```

## Checking the structure of the Board
```java
p.printStructure();
```
```text
  1   2   3   4   5   6   7   8   9
+---+---+---+---+---+---+---+---+---+
| * |   |   |   |   |   |   |   |   | 1
+---+---+---+---+---+---+---+---+---+
| * |   |   | * |   | * |   |   |   | 2
+---+---+---+---+---+---+---+---+---+
| * | * | * | * | * | * |   |   |   | 3
+---+---+---+---+---+---+---+---+---+
| * |   |   | * |   | * |   |   |   | 4
+---+---+---+---+---+---+---+---+---+
|   |   | * | * | * | * | * | * |   | 5
+---+---+---+---+---+---+---+---+---+
|   |   |   | * |   | * |   |   |   | 6
+---+---+---+---+---+---+---+---+---+
|   |   | * | * | * | * | * |   |   | 7
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   | 8
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   | 9
+---+---+---+---+---+---+---+---+---+
```
## Using the Generator Class
```java
Generator g = new Generator();
ArrayList<Puzzle> gens = g.generatePuzzle(p); //generate puzzle given initial board state and returns ArrayList<Puzzle> (may be empty if no valid configs reached)
for (Puzzle gen : gens) {
  gen.print();
}
```
```text
  1   2   3   4   5   6   7   8   9
+---+---+---+---+---+---+---+---+---+
| l |   |   |   |   |   |   |   |   | 1
+---+---+---+---+---+---+---+---+---+
| a |   |   | m |   | b |   |   |   | 2
+---+---+---+---+---+---+---+---+---+
| m | a | l | a | w | i |   |   |   | 3
+---+---+---+---+---+---+---+---+---+
| b |   |   | l |   | n |   |   |   | 4
+---+---+---+---+---+---+---+---+---+
|   |   | c | o | b | a | l | t |   | 5
+---+---+---+---+---+---+---+---+---+
|   |   |   | n |   | r |   |   |   | 6
+---+---+---+---+---+---+---+---+---+
|   |   | k | e | n | y | a |   |   | 7
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   | 8
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   | 9
+---+---+---+---+---+---+---+---+---+


  1   2   3   4   5   6   7   8   9
+---+---+---+---+---+---+---+---+---+
| r |   |   |   |   |   |   |   |   | 1
+---+---+---+---+---+---+---+---+---+
| i |   |   | p |   | d |   |   |   | 2
+---+---+---+---+---+---+---+---+---+
| b | e | l | i | z | e |   |   |   | 3
+---+---+---+---+---+---+---+---+---+
| s |   |   | e |   | v |   |   |   | 4
+---+---+---+---+---+---+---+---+---+
|   |   | e | r | b | i | u | m |   | 5
+---+---+---+---+---+---+---+---+---+
|   |   |   | c |   | c |   |   |   | 6
+---+---+---+---+---+---+---+---+---+
|   |   | y | e | m | e | n |   |   | 7
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   | 8
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   | 9
+---+---+---+---+---+---+---+---+---+

...
```
