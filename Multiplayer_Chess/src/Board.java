// Written by Michael Diep, diep0020
public class Board {

    // Instance variables
    private Piece[][] board;

    //TODO: Construct an object of type Board using given arguments.
    public Board() {
        this.board = new Piece[8][8];
    }

    // Accessor Methods

    //TODO: Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    //TODO: Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        if (piece == null){
            board[row][col] = null;
        }
        else {
            board[row][col] = new Piece(piece.getCharacter(), row, col, piece.getIsBlack());
        }
    }

    // csci1933.Projects.Project2Chess.Game functionality methods

    //TODO: Moves a Piece object from one cell in the board to another, provided that this movement is legal. Returns a boolean to signify success or failure.
    // This method calls all necessary helper functions to determine if a move is legal, and to execute the move if it is. Your csci1933.Projects.Project2Chess.Game class should not
    // directly call any other method of this class.
    // Hint: this method should call isMoveLegal() on the starting piece. 
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
//        if((0>startRow)||(8<=startRow)||(0>startCol)||(8<=startCol)){
//            return false; //checks if input starting input coordinates are out of bounds, otherwise board[startRow][startCol]
            //will crash because non-existent array does not equal to null
        //}
        if(board[startRow][startCol]!=null && getPiece(startRow, startCol).isMoveLegal(this, endRow, endCol)){ //returns false if piece move is illegal
            this.setPiece(endRow, endCol, board[startRow][startCol]);
            this.setPiece(startRow, startCol, null);
            return true;
        }
        else{return false;}
    }

    //TODO: Determines whether the game has been ended, i.e., if one player's King has been captured.
    public boolean isGameOver() {//using search method to loop through every cell and count the amount of Kings on entire board. If there's less than 2 then game is over.
        int count = 0;
        for(int i = 0; i < 8; i++){
            for(int x = 0; x < 8; x++){
                if(board[i][x] != null){
                    if(board[i][x].getCharacter() == '\u265a' || board[i][x].getCharacter() == '\u2654'){
                        count += 1;
                    }
                }
            }
        }
        return count < 2;
    }

    //TODO:
    // Construct a String that represents the Board object's 2D array. Return
    // the fully constructed String.
    public String toString() {
        String myBoard = "Board:\n    0 1 2 3 4 5 6 7 \n"; // sets the board template
        for (int i = 0; i < board.length; i++){
            myBoard += (" " + i + " "); //sets vertical board template with the count starting at 0 going down the board
            for (int x = 0; x < board.length; x++){ //writing every cell
                myBoard += "|";
                myBoard += (board[i][x] == null ? "\u2001" : board[i][x]);
            }
            myBoard += "|\n";
        }
        return myBoard;
    }

    //TODO: Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        for(int i = 0; i < 8; i++){
            for(int x = 0; x < 8; x++){
                board[i][x] = null;
            }
        }
    }

    // Movement helper functions

    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if((0>startRow)||(8<=startRow)||(0>endRow)||(8<=endRow)||(0>startCol)||(8<=startCol)||(0>endCol)||(8<=endCol)){
            return false;
            //if any of the input rows or columns equal less than 0 or are larger than 8 they are out of bounds of the array
        }
        if(board[startRow][startCol] == null){
            return false; //return false if starting is null
        }
        if(board[startRow][startCol] != null && board[startRow][startCol].getIsBlack() != isBlack){
            return false;
        }
        return board[endRow][endCol] == null || (board[endRow][endCol] != null && board[endRow][endCol].getIsBlack() != isBlack);
    }

    //TODO: Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        return Math.abs(endRow - startRow) <= 1 && Math.abs(endCol - startCol) <= 1;
    }

    //TODO: Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if(startRow != endRow) {
            return false;
        }
        for(int i = Math.min(startCol, endCol)+1; i < Math.max(endCol, startCol); i++){
            if(board[startRow][i] != null){
                return false;
            }
        }
        return true;
    }

    //TODO: Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if(startCol != endCol) {
            return false;
        }
        for(int i = Math.min(startRow, endRow)+1; i < Math.max(endRow, startRow); i++){
            if(board[i][startCol] != null){
                return false;
            }
        }
        return true;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        if(Math.abs(endRow-startRow)!=Math.abs(endCol-startCol)){
            return false;
        }
        //checks high to low, left to right diagonal move
        if((startRow > endRow && startCol > endCol) || (startRow < endRow && startCol < endCol)){
            int x = Math.min(startCol, endCol);
            for(int i = Math.min(startRow, endRow)+1; i < Math.max(endRow, startRow); i++) {
                x++;
                if (board[i][x] != null) {
                    return false;
                }
            }
        }
        //checks low to high, left to right diagonal move
        if((startRow > endRow && startCol < endCol) || (startRow < endRow && startCol > endCol)){
            int j = Math.max(startCol, endCol);
            for(int k = Math.min(startRow, endRow)+1; k < Math.max(endRow, startRow); k++) {
                j--;
                if (board[k][j] != null) {
                    return false;
                }
            }
        }
        return true;
    }
}