import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void showPlayingField(ArrayList<ArrayList<String>> markedPlaces) {
        String spacer = "-";
        int columnSize = markedPlaces.size();
        for (int i = 0; i < columnSize; i++) {
            int rowSize = markedPlaces.get(i).size();
            for (int j = 0; j < rowSize; j++) {
                if (j != 0) {
                    System.out.print(" | ");
                }
                System.out.print(markedPlaces.get(i).get(j));
            }
            System.out.println();
            if (i != columnSize - 1) {
                System.out.println(spacer.repeat(rowSize * 3));
            }
        }
    }

    public static boolean checkWinner(ArrayList<ArrayList<String>> markedPlaces) {
        boolean winner = false;
        for (int i = 0; i < markedPlaces.size(); i++) {
            for (int j = 0; j < markedPlaces.get(i).size(); j++) {
                if (!markedPlaces.get(i).get(j).equals(" ")) {
                    // check horizontal direction
                    if (!winner && j > 0 && j < markedPlaces.get(i).size() - 1) {
                        winner = markedPlaces.get(i).get(j).equals(markedPlaces.get(i).get(j - 1)) &&
                                markedPlaces.get(i).get(j).equals(markedPlaces.get(i).get(j + 1));
                        // check diagonal direction
                        if (!winner && i > 0 && i < markedPlaces.size() - 1) {
                            winner = (markedPlaces.get(i).get(j).equals(markedPlaces.get(i - 1).get(j - 1)) &&
                                    markedPlaces.get(i).get(j).equals(markedPlaces.get(i + 1).get(j + 1))) ||
                                    (markedPlaces.get(i).get(j).equals(markedPlaces.get(i - 1).get(j + 1)) &&
                                            markedPlaces.get(i).get(j).equals(markedPlaces.get(i + 1).get(j - 1)));
                        }
                    }
                    // check vertical direction
                    if (!winner && i > 0 && i < markedPlaces.size() - 1) {
                        winner = markedPlaces.get(i).get(j).equals(markedPlaces.get(i - 1).get(j)) &&
                                markedPlaces.get(i).get(j).equals(markedPlaces.get(i + 1).get(j));
                    }
                }
            }
        }
        return winner;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = 3;
        ArrayList<ArrayList<String>> markedPlaces = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            markedPlaces.add(new ArrayList<>());
            for (int j = 0; j < size; j++) {
                markedPlaces.get(i).add(" ");
            }
        }

        String winner = "";
        String currentPlayer = "O";
        int row;
        int column;

        System.out.println("Here is the playing field:");
        showPlayingField(markedPlaces);
        System.out.println("Player 'O' goes first and player 'X' goes second.");

        while (winner.isEmpty()) {
            System.out.println("Give me the position, where you want put your symbol (" + currentPlayer + ")" +
                    " (example for first row and third column: 1;3)");
            try {
                String[] position = scanner.nextLine().replaceAll("\\s", "").split(";");
                row = (Integer.parseInt(position[0]) - 1);
                column = (Integer.parseInt(position[1]) - 1);
                String place = markedPlaces.get(row).get(column);
                if (place.equals(" ")) {
                    markedPlaces.get(row).set(column, currentPlayer);
                } else {
                    System.out.println("The place is taken, try again!!!");
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
            } else {
                currentPlayer = "O";
            }
        }
    }
}