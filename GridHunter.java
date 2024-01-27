import java.util.Random;
import java.util.Scanner;

public class GridHunter {
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
    
    }


    public static String[][] createGrid(int size){
        String[][] grid = new String[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                grid[i][j] =  ".";
            }
        }

        return grid;
    }
}
