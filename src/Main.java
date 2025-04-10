import java.util.Scanner;

public class Main {

    public static void showPlayingField(String[][] markedPlaces) {
        String spacer = "-";
        int columnSize = markedPlaces.length;
        for (int i = 0; i < columnSize; i++) {
            int rowSize = markedPlaces[i].length;
            for (int j = 0; j < rowSize; j++) {
                if (j != 0) {
                    System.out.print(" | ");
                }
                if (markedPlaces[i][j] == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(markedPlaces[i][j]);
                }
            }
            System.out.println();
            if (i != columnSize - 1) {
                System.out.println(spacer.repeat(4 * (rowSize - 1) + 1));
            }
        }
    }

    public static boolean checkWinner(String[][] markedPlaces) {
        boolean winner = false;
        for (int i = 0; i < markedPlaces.length; i++) {
            for (int j = 0; j < markedPlaces[i].length; j++) {
                if (markedPlaces[i][j] != null) {
                    // check horizontal direction
                    if (!winner && j > 0 && j < markedPlaces[i].length - 1) {
                        winner = markedPlaces[i][j].equals(markedPlaces[i][j - 1]) &&
                                markedPlaces[i][j].equals(markedPlaces[i][j + 1]);
                        // check diagonal direction
                        if (!winner && i > 0 && i < markedPlaces.length - 1) {
                            winner = (markedPlaces[i][j].equals(markedPlaces[i - 1][j - 1]) &&
                                    markedPlaces[i][j].equals(markedPlaces[i + 1][j + 1])) ||
                                    (markedPlaces[i][j].equals(markedPlaces[i - 1][j + 1]) &&
                                            markedPlaces[i][j].equals(markedPlaces[i + 1][j - 1]));
                        }
                    }
                    // check vertical direction
                    if (!winner && i > 0 && i < markedPlaces.length - 1) {
                        winner = markedPlaces[i][j].equals(markedPlaces[i - 1][j]) &&
                                markedPlaces[i][j].equals(markedPlaces[i + 1][j]);
                    }
                }
            }
        }
        return winner;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = 4;
        String[][] markedPlaces = new String[size][size];

        String winner = "";
        String currentPlayer = "O";
        int row;
        int column;
        int count = 0;

        System.out.println("Here is the playing field:");
        showPlayingField(markedPlaces);
        System.out.println("Player 'O' goes first and player 'X' goes second.");

        while (winner.isEmpty()) {
            System.out.println("Give me the position (numbers from 1 to " + size + "), where you want put your symbol (" +
                    currentPlayer + ") -> example for first row and third column: 1;3");
            try {
                String[] position = scanner.nextLine().replaceAll("\\s", "").split(";");
                row = (Integer.parseInt(position[0]) - 1);
                column = (Integer.parseInt(position[1]) - 1);
                if (markedPlaces[row][column] == null) {
                    markedPlaces[row][column] = currentPlayer;
                } else {
                    System.out.println("The place is taken, look at the playing field and try again!!!");
                    showPlayingField(markedPlaces);
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Invalid input, try again!!!");
                continue;
            }

            showPlayingField(markedPlaces);

            if (checkWinner(markedPlaces)) {
                winner = currentPlayer;
                System.out.println("Player '" + currentPlayer + "' wins!!!");
            } else if (currentPlayer.equals("O")) {
                currentPlayer = "X";
                count++;
            } else {
                currentPlayer = "O";
                count++;
            }

            if (count == Math.pow(size, 2)) {
                System.out.println("Draw!!!");
                break;
            }
        }
    }
}