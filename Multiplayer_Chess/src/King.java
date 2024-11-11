// Written by Michael Diep, diep0020
public class King {
    //instance variables
    private int row;
    private int col;
    private boolean isBlack;
    public King(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        if(!board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)){
            return false;
        }
        if (board.verifyAdjacent(this.row, this.col, endRow, endCol)){
            // if piece is legally moving adjacently
            return true;
        }
            // Moving in a non-adjacent column. (illegal move)
        return false;
    }
}
