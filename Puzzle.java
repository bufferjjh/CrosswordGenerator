import java.util.*;

public class Puzzle {

    char[][] board;
    boolean[][] down;
    ArrayList<int[]> downCords;
    ArrayList<int[]> acrossCords;
    ArrayList<Boolean> isDoneDown;
    ArrayList<Boolean> isDoneAcross;
    ArrayList<Integer> downMag;
    ArrayList<Integer> acrossMag;
    Puzzle(int n) {
        board = new char[n][n];
        for (char[] i : board) {
            Arrays.fill(i, '#');
        }
        down = new boolean[n][n];
        downCords = new ArrayList<>();
        acrossCords = new ArrayList<>();
        downMag = new ArrayList<>();
        acrossMag = new ArrayList<>();
        isDoneDown = new ArrayList<>();
        isDoneAcross = new ArrayList<>();
    }
    public void insertWord(String word, int r, int c, boolean isDown) {
        //assume valid inserts
        down[r][c] = isDown;
        if(isDown) {
            for (int i = 0; i < word.length(); i++) {
                board[r + i][c] = word.charAt(i);
            }
            downCords.add(new int[] {r+1,c+1});
            isDoneDown.add(false);
            downMag.add(word.length());
        }
        else {
            for (int i = 0; i < word.length(); i++) {
                board[r][c + i] = word.charAt(i);
            }
            acrossCords.add(new int[] {r+1,c+1});
            isDoneAcross.add(false);
            acrossMag.add(word.length());
        }
    }
    public void print() {
        for (int i = 0; i < board.length; i++) {
            if(i < 10) System.out.print("  " + (i+1) + " ");
            else System.out.print(" " + (i+1) + " ");
        }
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print("+---");
            }
            System.out.print("+\n");
            System.out.print("|");
            for (int j = 0; j < board.length; j++) {
                if(board[i][j] == '#') {
                    System.out.print(" " + " " + " |");
                }
                else System.out.print(" " + board[i][j] + " |");
            }
            System.out.println(" " + (i+1));
        }
        for (int j = 0; j < board.length; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
        System.out.println();
    }
    public void printStructure() {
        for (int i = 0; i < board.length; i++) {
            if(i < 10) System.out.print("  " + (i+1) + " ");
            else System.out.print(" " + (i+1) + " ");
        }
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print("+---");
            }
            System.out.print("+\n");
            System.out.print("|");
            for (int j = 0; j < board.length; j++) {
                if(board[i][j] == '#') {
                    System.out.print(" " + " " + " |");
                }
                else System.out.print(" " + '*' + " |");
            }
            System.out.println(" " + (i+1));
        }
        for (int j = 0; j < board.length; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
        System.out.println();
    }
    public void printDirections() {
        System.out.println("DOWN");
        for (int[] i : downCords) {
            System.out.println(Arrays.toString(i));
        }
        System.out.println();
        System.out.println("ACROSS");
        for (int[] i : acrossCords) {
            System.out.println(Arrays.toString(i));
        }
    }
    public Puzzle copy() {
        Puzzle p2 = new Puzzle(board.length);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                p2.board[i][j] = board[i][j];
            }
        }
        for (int i = 0; i < downCords.size(); i++) {
            p2.downCords.add(downCords.get(i));
            p2.downMag.add(downMag.get(i));
            p2.isDoneDown.add(isDoneDown.get(i));
        }
        for (int i = 0; i < acrossCords.size(); i++) {
            p2.acrossCords.add(acrossCords.get(i));
            p2.acrossMag.add(acrossMag.get(i));
            p2.isDoneAcross.add(isDoneAcross.get(i));
        }
        return p2;
    }
    public int difference(Puzzle p2) {
        //assume same dimensions
        int diff = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(board[i][j] != p2.board[i][j]) diff++;
            }
        }
        return diff;
    }
}
