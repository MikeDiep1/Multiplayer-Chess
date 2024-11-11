// Written by Michael Diep, diep0020
import java.util.Scanner;
public class Game {
    public static void main(String[] args) {
        Board myBoard = new Board();
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", myBoard);
        //boolean isBlack = false;
        boolean isWhite = true; //Board starts with white's move
        while (!myBoard.isGameOver()) { // while isGameOver is not over or false, continue to ask for user input for moves
            System.out.println(myBoard.toString());
            if (isWhite) { //
                System.out.println("It is currently white's turn to play.");
            } else {
                System.out.println("It is currently black's turn to play.");
            }
            System.out.println("What is your move? (format: [start row] [start col] [end row] [end col])");
            Scanner myScanner = new Scanner(System.in);
            String move;
            move = myScanner.nextLine();
            String[] rowsCols = move.split(" ");
            while ((0 > (Integer.parseInt(rowsCols[0]))) || (8 <= (Integer.parseInt(rowsCols[0]))) || (0 > (Integer.parseInt(rowsCols[1]))) || ((8 <= Integer.parseInt(rowsCols[1])))
                    || myBoard.getPiece(Integer.parseInt(rowsCols[0]), Integer.parseInt(rowsCols[1])) == null
                    || myBoard.getPiece(Integer.parseInt(rowsCols[0]), Integer.parseInt(rowsCols[1])).getIsBlack() == isWhite
                    || !myBoard.movePiece(Integer.parseInt(rowsCols[0]), Integer.parseInt(rowsCols[1]), Integer.parseInt(rowsCols[2]), Integer.parseInt(rowsCols[3]))) {
                // if user attempts to move the wrong color or while loop executes movePiece and comes out
                // false, or user inputs invalid/null/out of bound coordinates-prompt the user for another coordinate
                //I would use verifysourcedestination here if I could, but I'm not allowed
                System.out.println("Move is not legal, re-enter different coordinates: ");
                move = myScanner.nextLine();
                rowsCols = move.split(" ");
            }
            if (isWhite) { //interchange boolean values to switch player turns
                isWhite = false;
            } else {
                isWhite = true;
            }
            for (int i = 0; i < 8; i++) { // checking the top and bottom rows for pawn promotion
                if (myBoard.getPiece(0, i) == null || myBoard.getPiece(7, i) == null) {
                    if (isWhite) {
                        isWhite = false;
                    } else {
                        isWhite = true;
                    }
                } else if (myBoard.getPiece(0, i).getCharacter() == '\u2659' || myBoard.getPiece(7, i).getCharacter() == '\u265f') {
                    //if a pawn of the opposite color takes a space in the opposite row, call pawnPromo from Piece class
                    Piece.pawnPromo(myBoard, i);
                    if (isWhite) {
                        isWhite = false;
                    } else {
                        isWhite = true;
                    }
                } else {
                    if (isWhite) {
                        isWhite = false;
                    } else {
                        isWhite = true;
                    }
                }
            }
        }
        System.out.println(myBoard.toString()); //prints final board layout after the game is over
        for (int i = 0; i < 8; i++) {  //checks every space for either white or black king, whichever king is finally discovered, that color is printed as winner
            for (int x = 0; x < 8; x++) {
                if (myBoard.getPiece(i, x) != null) {
                    if (myBoard.getPiece(i, x).getCharacter() == '\u265a') {
                        System.out.println("Black has won the game!");
                    }
                    if (myBoard.getPiece(i, x).getCharacter() == '\u2654') {
                        System.out.println("White has won the game!");
                    }
                }
            }
        }
    }
}