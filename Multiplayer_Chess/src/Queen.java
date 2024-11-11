// Written by Michael Diep, diep0020
public class Queen {
    //instance variables
    private int row;
    private int col;
    private boolean isBlack;
    public Queen(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        if(!board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)){
            return false;
        }
        if (board.verifyDiagonal(this.row, this.col, endRow, endCol)
                || board.verifyVertical(this.row, this.col, endRow, endCol)
                || board.verifyHorizontal(this.row, this.col, endRow, endCol)){
            //If piece is legally moving diagonal, vertical, or horizontal
            return true;
        }
        // (illegal move)
        return false;
    }
}
