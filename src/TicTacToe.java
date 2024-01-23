import java.util.Scanner;

public class TicTacToe {
    int size = 9;
    int racineCarre = (int) Math.sqrt(size);
    Player player1 = new Player();
    Player player2 = new Player("| O ", "player 2");
    int line;
    int column;

    private final Cell[] board = new Cell[size];
//    Player joueur = new Player();
    TicTacToe() { //constructeur
        for (int i=0; i<board.length;i++ ){
            board[i] = new Cell();
        }
    }
    public void separator(){
        System.out.println("-------------");
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
    private void move() {
        getInputLine();
        getInputColumn();

    }
    public Scanner getInputLine() {
        Scanner scanner = new Scanner(System.in);

        do{
            System.out.println("Entrez une ligne entre 0 et 2 : ");
            try {
                line = Integer.parseInt(scanner.next());
            } catch (Exception e) {
                System.out.println("entree un nombree pour ligne!");
                continue;
            }
            if (line >= 0 && line < racineCarre) {
                break;
            } else {
                System.out.println("ligne invalide");
            }
        }while (true);

        return scanner;
    }

    public Scanner getInputColumn() {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Entrez une colonne entre 0 et 2 : ");
            try {
                column = Integer.parseInt(scanner.next());
            } catch (Exception e) {
                System.out.println("Entrez un nombre valide pour la colonne !");
                continue;
            }

            if (column >= 0 && column < racineCarre) {
                System.out.println("Colonne: " + column);
                break;
            } else {
                System.out.println("Colonne invalide");
            }
        } while (true);

        return scanner;
    }


    public boolean isValidMove() {
        //verifier si les players n'ont pas jouè au mm endroit
        // verifie si la cellule est libre
        System.out.println("invalide place, entree un autre");
        return !(board[line * 3 + column].getRepresentation().equals(player1.getRepresentation())
                || board[line * 3 + column].getRepresentation().equals(player2.getRepresentation()));
    }

    public void play (){
//        player 1, joue par premier
        Player currentPlayer = player1;
        while (!isOver()) {
            gameTurn(currentPlayer);
            display();
            currentPlayer = switchPlayer(currentPlayer);
        }
    }

    private boolean isOver() {
        if (checkPlayerWin(player1) || checkPlayerWin(player2)) {
            return true;
        }
        //
        for (Cell cell : board) {
            if (!(cell.getRepresentation().equals(player1.getRepresentation())
                    || cell.getRepresentation().equals(player2.getRepresentation()))) {
                return false;
            }
        }
        System.out.println("match null, game over!");
        return true;
    }

    // verifier si il a rempi un ligne
    private boolean checkPlayerWin(Player player) {
        // Check rows
        for (int i = 0; i < racineCarre; i++) {
            if (checkRow(i, player) || checkColumn(i, player) || checkDiagonal(player)) {
                System.out.println(player.getName() + " wins this game!");
                return true;
            }
        }
        return false;
    }

// verification si les lignes sont rempli
    private boolean checkRow(int row, Player player) {
        int start = row * racineCarre;
        return board[start].getRepresentation().equals(player.getRepresentation()) &&
                board[start + 1].getRepresentation().equals(player.getRepresentation()) &&
                board[start + 2].getRepresentation().equals(player.getRepresentation());
    }
//  verification si le colonne sont rempli
    private boolean checkColumn(int col, Player player) {
        return board[col].getRepresentation().equals(player.getRepresentation()) &&
                board[col + racineCarre].getRepresentation().equals(player.getRepresentation()) &&
                board[col + 2 * racineCarre].getRepresentation().equals(player.getRepresentation());

    }
//  verification  diagonal sont rempli
    private boolean checkDiagonal(Player player) {
        return board[0].getRepresentation().equals(player.getRepresentation()) &&
                board[4].getRepresentation().equals(player.getRepresentation()) &&
                board[8].getRepresentation().equals(player.getRepresentation()) ||
                board[2].getRepresentation().equals(player.getRepresentation()) &&
                board[4].getRepresentation().equals(player.getRepresentation()) &&
                board[6].getRepresentation().equals(player.getRepresentation());
        //        | 0 | 1 | 2 |
        //        | 3 | 4 | 5 |
        //        | 6 | 7 | 8 |
    }

    private Player switchPlayer(Player currentPlayer) {
        System.out.println("last player: "+currentPlayer.getName());
        if (currentPlayer == this.player1){
                return this.player2;
        } else {
            return this.player1;
        }
    }

    private void gameTurn(Player current) {
        // choisi line + column
        move();
        // verifie le mouvement
        while (!isValidMove()){
            move();
        }
        // assigne la position au joueur
        placePlayer(current);
    }
    private void placePlayer(Player current) {
        board[line*3 + column].representation = current.getRepresentation();
    }
}