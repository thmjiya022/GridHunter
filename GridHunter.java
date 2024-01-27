import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class GridHunter {
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) throws IOException {

        System.out.print("Size? ");
        int size = scanner.nextInt();
        String[][] grid = createGrid(size);
        int[] playerPosition = placeEntity(grid, size, "P");
        placeFood(grid, size);
        printGrid(grid, size);
        displayInstructions();

        String option;
        do {
            clearScreen();
            printGrid(grid, size);
            displayInstructions();
            option = scanner.nextLine().toUpperCase().trim();
            movePlayer(option, grid, size, playerPosition);
        } while (!option.equals("Q"));

        scanner.close();
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

    public static void placeFood(String[][]grid, int size){
        int foodPopulationSize = (int) (size * size * 0.1);

        for(int i = 0; i < foodPopulationSize; i++){
            int randomX,randomY;
            do{
                randomX = random.nextInt(size);
                randomY = random.nextInt(size);
            }while(!grid[randomX][randomY].equals("."));
            grid[randomX][randomY] = "*";
        }
    }

    public static int[] placeEntity(String[][] grid, int size, String entity){
        int[] position = new int[2];
        int x, y;
        do {
            x = random.nextInt(size);
            y = random.nextInt(size);
        } while (!grid[x][y].equals("."));
        grid[x][y] = entity;
        position[0] = x;
        position[1] = y;
        return position;
    }

    public static void printGrid(String[][] grid, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void displayInstructions() {
        System.out.println("W- Move Up\n" +
                "S- Move Down\n" +
                "A- Move Left\n" +
                "D- Move Right\n" +
                "Q- Quit");
    }

    public static void movePlayer(String option, String[][] grid, int size, int[] playerPosition) {
        int playerX = playerPosition[0];
        int playerY = playerPosition[1];
        switch (option) {
            case "W":
                if (playerX > 0 && grid[playerX - 1][playerY].equals(".")) {
                    grid[playerX][playerY] = ".";
                    grid[playerX - 1][playerY] = "P";
                    playerPosition[0] = playerX - 1;
                }
                break;
            case "S":
                if (playerX < size - 1 && grid[playerX + 1][playerY].equals(".")) {
                    grid[playerX][playerY] = ".";
                    grid[playerX + 1][playerY] = "P";
                    playerPosition[0] = playerX + 1;
                }
                break;
            case "A":
                if (playerY > 0 && grid[playerX][playerY - 1].equals(".")) {
                    grid[playerX][playerY] = ".";
                    grid[playerX][playerY - 1] = "P";
                    playerPosition[1] = playerY - 1;
                }
                break;
            case "D":
                if (playerY < size - 1 && grid[playerX][playerY + 1].equals(".")) {
                    grid[playerX][playerY] = ".";
                    grid[playerX][playerY + 1] = "P";
                    playerPosition[1] = playerY + 1;
                }
                break;
            case "Q":
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
