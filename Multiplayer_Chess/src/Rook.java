// Written by Michael Diep, diep0020
public class Rook {
    //instance variables
    private int row;
    private int col;
    private boolean isBlack;
    public Rook(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        if(!board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)){
            return false;
        }
        if(board.verifyVertical(this.row, this.col, endRow, endCol)
                || board.verifyHorizontal(this.row, this.col, endRow, endCol)){
            return true;
            // if piece is legally moving vertically or horizontally without capturing a piece
        }
//        // (illegal move)
        return false;
    }
}