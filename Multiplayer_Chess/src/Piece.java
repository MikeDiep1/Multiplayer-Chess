// Written by Michael Diep, diep0020
import java.util.Scanner;
public class Piece {

    // Instance variables
    private char character;
    private int row;
    private int col;
    private boolean isBlack;

    /**
     * Constructor.
     *
     * @param character The character representing the piece.
     * @param row       The row on the board the piece occupies.
     * @param col       The column on the board the piece occupies.
     * @param isBlack   The color of the piece.
     */
    public Piece(char character, int row, int col, boolean isBlack) {
        this.character = character;
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Determines if moving this piece is legal.
     *
     * @param board  The current state of the board.
     * @param endRow The destination row of the move.
     * @param endCol The destination column of the move.
     * @return If the piece can legally move to the provided destination on the board.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        switch (this.character) {
            // Pawn chars
            case '\u2659':
            case '\u265f':
                Pawn pawn = new Pawn(row, col, isBlack);
                return pawn.isMoveLegal(board, endRow, endCol);
            // Rook chars
            case '\u2656':
            case '\u265c':
                Rook rook = new Rook(row, col, isBlack);
                return rook.isMoveLegal(board, endRow, endCol);
            // Knight chars
            case '\u265e':
            case '\u2658':
                Knight knight = new Knight(row, col, isBlack);
                return knight.isMoveLegal(board, endRow, endCol);
            // Bishop chars
            case '\u265d':
            case '\u2657':
                Bishop bishop = new Bishop(row, col, isBlack);
                return bishop.isMoveLegal(board, endRow, endCol);
            // Queen chars
            case '\u265b':
            case '\u2655':
                Queen queen = new Queen(row, col, isBlack);
                return queen.isMoveLegal(board, endRow, endCol);
            // King chars
            case '\u265a':
            case '\u2654':
                King king = new King(row, col, isBlack);
                return king.isMoveLegal(board, endRow, endCol);
            default:
                return false;
        }
    }

    /**
     * Sets the position of the piece.
     *
     * @param row The row to move the piece to.
     * @param col The column to move the piece to.
     */
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Returns the current chess unicode character.
     *
     * @return Unicode character.
     */
    public char getCharacter() {
        return this.character;
    }

    /**
     * Return the color of the piece.
     *
     * @return The color of the piece.
     */
    public boolean getIsBlack() {
        return this.isBlack;
    }

    /**
     * Tests the equality of two Piece objects based on
     * their character parameter.
     *
     * @param other An instance of Piece to compare with this
     *              instance.
     * @return Boolean value representing equality result.
     */
    public boolean equals(Piece other) {
        return this.character == other.character;
    }

    /**
     * Returns a string representation of the piece.
     *
     * @return A string representation of the piece.
     */
    public String toString() {
        return "" + this.character;
    }

    public static void pawnPromo(Board board, int c) {
                System.out.println("Promote your pawn to a special piece:");
                Scanner myScanner = new Scanner(System.in);
                String retrieve;
                retrieve = myScanner.nextLine();
        if (board.getPiece(0, c).getCharacter() == '\u2659') { //creates a new special white piece in place of the pawn
            if (retrieve.equals("Queen")) {
                Piece newQueen = new Piece('\u2655', 0, c, board.getPiece(0, c).getIsBlack());
                board.setPiece(0, c, newQueen);
            }
            if (retrieve.equals("Bishop")) {
                Piece newBishop = new Piece('\u2657', 0, c, board.getPiece(0, c).getIsBlack());
                board.setPiece(0, c, newBishop);
            }
            if (retrieve.equals("Knight")) {
                Piece newKnight = new Piece('\u2658', 0, c, board.getPiece(0, c).getIsBlack());
                board.setPiece(0, c, newKnight);
            }
            if (retrieve.equals("Rook")) {
                Piece newRook = new Piece('\u2656', 0, c, board.getPiece(0, c).getIsBlack());
                board.setPiece(0, c, newRook);
            }
        }
        else{
                if (retrieve.equals("Queen")) { //creates a new special black piece in place of the pawn
                    Piece blackQueen = new Piece('\u265b', 7, c, board.getPiece(7, c).getIsBlack());
                    board.setPiece(7, c, blackQueen);
                }
                if (retrieve.equals("Bishop")) {
                    Piece blackBishop = new Piece('\u265d', 7, c, board.getPiece(7, c).getIsBlack());
                    board.setPiece(7, c, blackBishop);
                }
                if (retrieve.equals("Knight")) {
                    Piece blackKnight = new Piece('\u265e', 7, c, board.getPiece(7, c).getIsBlack());
                    board.setPiece(7, c, blackKnight);
                }
                if (retrieve.equals("Rook")) {
                    Piece blackRook = new Piece('\u265c', 7, c, board.getPiece(7, c).getIsBlack());
                    board.setPiece(7, c, blackRook);
                }
            }
        }
    }