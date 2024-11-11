// Written by Michael Diep, diep0020
public class Knight {
    //instance variables
    private int row;
    private int col;
    private boolean isBlack;
    public Knight(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        if(!board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)){
            return false;
        }
        if ((Math.abs(this.row-endRow) == 2 && Math.abs(this.col-endCol) == 1)) {
            // if piece is legally moving up or down 2 spaces and over 1
            return true;
        }
        if ((Math.abs(this.row-endRow) == 1 && Math.abs(this.col-endCol) == 2)) {
            // if piece is legally moving up or down 1 space and over 2
            return true;
        }
        // (illegal move)
        return false;
    }
}
