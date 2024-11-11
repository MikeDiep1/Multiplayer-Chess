// Written by Michael Diep, diep0020
public class Bishop {
    //instance variables
    private int row;
    private int col;
    private boolean isBlack;
    public Bishop(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        if(!board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)){
            return false;
        }
        if (board.verifyDiagonal(this.row, this.col, endRow, endCol)){
            // if piece is legally moving diagonally without capturing another piece
            return true;
        }
        // (illegal move)
        return false;
    }
}
