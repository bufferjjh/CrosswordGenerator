import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Puzzle p = new Puzzle(9);
        p.insertWord("    ", 0, 0, true);
        p.insertWord("      ", 2, 0, false);
        p.insertWord("      ", 1, 3, true);
        p.insertWord("      ", 4, 2, false);
        p.insertWord("      ",1,5,true);
        p.insertWord("     ", 6,2, false);
        Generator g = new Generator();
        ArrayList<Puzzle> bestGen = g.generatePuzzle(p);
        int bestDiff = g.evaluateDifference(bestGen);
        for (int i = 0; i < 200000; i++) {
            ArrayList<Puzzle> nextGen = g.generatePuzzle(p);
            int currDiff = g.evaluateDifference(nextGen);
            if(currDiff > bestDiff) {
                bestDiff = currDiff;
                bestGen = nextGen;
            }
        }
        for (Puzzle i : bestGen) {
            i.print();
        }
    }
}
