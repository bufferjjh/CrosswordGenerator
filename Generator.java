import java.util.*;
import java.io.*;
public class Generator {
    String[][] groups;
    HashMap<Integer,ArrayList<Integer>> lengthsToGroupNum;
    Generator() throws IOException {
        groups = new String[41][1];
        Scanner sc = new Scanner(new File("cat.txt"));
        for (int i = 0; i < 41; i++) {
            groups[i] = sc.nextLine().split(" ");
        }
        for (int i = 0; i < groups.length; i++) {
            for (int j = 0; j < groups[i].length; j++) {
                groups[i][j] = groups[i][j].toLowerCase();
            }
        }
        lengthsToGroupNum = new HashMap<>();
        for (int i = 0; i < groups.length; i++) {
            if(!lengthsToGroupNum.containsKey(groups[i][0].length())) {
                lengthsToGroupNum.put(groups[i][0].length(), new ArrayList<>());
            }
            lengthsToGroupNum.get(groups[i][0].length()).add(i);
        }
    }
    public int selectRandom(ArrayList<Integer> arr) {
        Random random = new Random();
        return arr.get(random.nextInt(arr.size()));
    }
    public ArrayList<Puzzle> generatePuzzle(Puzzle initialState) {
        //assign groups nums to words -> make sure group length = mag of that word
        //order -> downs then across
        boolean[] used = new boolean[groups.length];
        int[] groupAssignments = new int[initialState.downCords.size() + initialState.acrossCords.size()];
        for (int i = 0; i < initialState.downCords.size(); i++) {
            int groupAssign = selectRandom(lengthsToGroupNum.get(initialState.downMag.get(i)));
            while(used[groupAssign]) groupAssign = selectRandom(lengthsToGroupNum.get(initialState.downMag.get(i)));
            groupAssignments[i] = groupAssign;
            used[groupAssign] = true;
        }
        for (int i = 0; i < initialState.acrossCords.size(); i++) {
            int groupAssign = selectRandom(lengthsToGroupNum.get(initialState.acrossMag.get(i)));
            while(used[groupAssign]) groupAssign = selectRandom(lengthsToGroupNum.get(initialState.acrossMag.get(i)));
            groupAssignments[i + initialState.downCords.size()] = groupAssign;
            used[groupAssign] = true;
        }
        //iterative dfs for all assignments
        ArrayList<Puzzle> validGenerations = new ArrayList<>();
        Stack<Puzzle> s = new Stack<>();
        Stack<Integer> step = new Stack<>();
        s.push(initialState);
        step.push(0);
        while(!s.isEmpty()) {
            Puzzle state = s.pop();
            int currStep = step.pop();
            //System.out.println(currStep);
            if(currStep == initialState.downCords.size() + initialState.acrossCords.size()) {
                validGenerations.add(state);
                continue;
            }
            for (String i : groups[groupAssignments[currStep]]) {
                if(currStep >= state.downCords.size()) {
                    int[] start = state.acrossCords.get(currStep - state.downCords.size());
                    int mag = state.acrossMag.get(currStep - state.downCords.size());
                    boolean good = true;
                    for (int j = 0; j < mag; j++) {
                        if(state.board[start[0]-1][start[1]+j-1] != ' ' && state.board[start[0]-1][start[1]+j-1] != i.charAt(j)) {
                            good = false;
                            break;
                        }
                    }
                    if(good) {
                        Puzzle p2 = state.copy();
                        for (int j = 0; j < mag; j++) {
                            p2.board[start[0]-1][start[1]+j-1] = i.charAt(j);
                        }
                        s.push(p2);
                        step.push(currStep+1);
                    }
                }
                else {
                    int[] start = state.downCords.get(currStep);
                    int mag = state.downMag.get(currStep);
                    boolean good = true;
                    for (int j = 0; j < mag; j++) {
                        if(state.board[start[0]+j-1][start[1]-1] != ' ' && state.board[start[0]+j-1][start[1]-1] != i.charAt(j)) {
                            good = false;
                            break;
                        }
                    }
                    if(good) {
                        Puzzle p2 = state.copy();
                        for (int j = 0; j < mag; j++) {
                            p2.board[start[0]+j-1][start[1]-1] = i.charAt(j);
                        }
                        s.push(p2);
                        step.push(currStep+1);
                    }
                }
            }
        }
        //if(validGenerations.size() < 2) return generatePuzzle(initialState); causes stack overflow
        return validGenerations;
    }
    public int evaluateDifference(ArrayList<Puzzle> gens) {
        int maxDiff = 0;
        for (int i = 0; i < gens.size(); i++) {
            for (int j = i + 1; j < gens.size(); j++) {
                maxDiff = Math.max(maxDiff, gens.get(i).difference(gens.get(j)));
            }
        }
        return maxDiff;
    }
}