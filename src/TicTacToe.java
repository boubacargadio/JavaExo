import java.lang.reflect.Array;
import java.util.Scanner;

public class TicTacToe {
    int size = 9;
    int racineCarre = (int) Math.sqrt(size);
//    int racineCarre = (int) Math.pow(size, 2);
    int line = 3;
    int col = 3;

    Cell[] board = new Cell[size];
//    Player joueur = new Player();
    TicTacToe() { //constructeur
        for (int i=0; i<board.length;i++ ){
            board[i] = new Cell();
        }
    }
    public void display (){
        separator();
        for (int i=0; i<board.length;i++) {
            String tableau = board[i].getRepresentation();
            System.out.print(tableau);
            if ((i + 1) % racineCarre ==0){
                System.out.print("|");
                System.out.println();
                separator();
            }
        }
    }

    public void getMoveFromPlayer(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez une ligne entre 0 et 2 : ");

        int line;
        try {
            line = Integer.parseInt(scanner.next());
        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide. Veuillez entrer un nombre.");
            return;
        }

        if (line >= 0 && line < racineCarre) {
            System.out.println("ligne: " + line);

        } else {
            System.out.println("ligne ou colonne invalide");
            return;
        }

        System.out.println("Entrez une colonne 0 et 2 : ");

        int column;
        try {
            column = Integer.parseInt(scanner.next());
        } catch (NumberFormatException e) {
            System.out.println("Veuillez entrer un nombre.");
            return;
        }

        if (column >= 0 && column < racineCarre) {
            System.out.println("colonne: " + column);

        } else {
            System.out.println("ligne ou colonne invalide");
        }
        Player player1 = new Player();
        board[line*3 + column].representation = player1.getRepresentation();
        display();
    }

    public void separator(){
        System.out.println("-------------");
    }
}
