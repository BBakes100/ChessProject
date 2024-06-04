package chess;

import javax.swing.*;
import java.util.ArrayList;

/**********************************************************************
 * A model of Chess that is responsible for storing the chessboard
 * and implementing the game logic.
 *
 * Class name: ChessModel
 * @author Benjamin Howe and Brandon Baker
 * @version Winter 2022
 **********************************************************************/

public class ChessModel implements IChessModel {

    /**
     * 2D Board containing IChessPieces
     */
    private IChessPiece[][] board;

    /**
     * Chess player
     */
    private Player player;

    /**
     * Array list of keeping track of moves
     */
    ArrayList<SaveState> list = new ArrayList<>();

    /**
     * Determines if AI has moved
     */
    private boolean AIMoved;

    /**
     * Keeps track of opening AI moves
     */
    int first2Turns;

    /**********************************************************************
     * Constructor initializes chess board and places the pieces
     **********************************************************************/
    public ChessModel() {
        //initializing the board and respective players
        board = new IChessPiece[8][8];
        player = Player.WHITE;
        AIMoved = false;
        first2Turns = 0;

        //placing pieces in respective spots
        board[0][0] = new Rook(Player.BLACK);
        board[0][1] = new Knight(Player.BLACK);
        board[0][2] = new Bishop(Player.BLACK);
        board[0][3] = new Queen(Player.BLACK);
        board[0][4] = new King(Player.BLACK);
        board[0][5] = new Bishop(Player.BLACK);
        board[0][6] = new Knight(Player.BLACK);
        board[0][7] = new Rook(Player.BLACK);

        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(Player.BLACK);
        }

        board[7][0] = new Rook(Player.WHITE);
        board[7][1] = new Knight(Player.WHITE);
        board[7][2] = new Bishop(Player.WHITE);
        board[7][3] = new Queen(Player.WHITE);
        board[7][4] = new King(Player.WHITE);
        board[7][5] = new Bishop(Player.WHITE);
        board[7][6] = new Knight(Player.WHITE);
        board[7][7] = new Rook(Player.WHITE);

        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(Player.WHITE);
        }

    }

    /**********************************************************************
     * Checks for a checkmate by seeing if a player is in check and
     * checking every possible move for that players pieces.
     *
     * @return valid which specifies if it is a valid checkmate or not a
     * valid checkmate
     **********************************************************************/
    public boolean isComplete() {

        boolean valid = false;
        Move move;

        //King is in check
        if (inCheck(currentPlayer().next())) {

            valid = true;

            //Check spots on the board
            for (int fromRow = 0; fromRow < numRows(); fromRow++) {
                for (int fromColumn = 0; fromColumn < numColumns();
                     fromColumn++) {

                    //Check that it is not null
                    if (board[fromRow][fromColumn] != null) {

                        //Check that the piece belongs to same player in check
                        if (board[fromRow][fromColumn].player() ==
                                currentPlayer().next()) {

                            //Check all possible moves for that piece
                            for (int toRow = 0; toRow < numRows();
                                 toRow++) {
                                for (int toColumn = 0; toColumn <
                                        numColumns(); toColumn++) {

                                    move = new Move(fromRow, fromColumn,
                                            toRow, toColumn);

                                    /*Check to see if player still in
                                    check after each move*/
                                    if (board[fromRow][fromColumn]
                                            .isValidMove(move, board)) {

                                        /*Temp piece to make sure
                                        pieces don't go missing*/
                                        IChessPiece tempPiece =
                                                board[toRow][toColumn];
                                        move(move);

                                        //Player is out of check, not checkmate
                                        if (!inCheck(currentPlayer()
                                                .next())) {
                                            valid = false;
                                        }
                                        unMove();
                                        board[toRow][toColumn] =
                                                tempPiece;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return valid;
    }

    /**********************************************************************
     * Checks a valid move for a piece by seeing whether there is a piece
     * there, and checking the specific pieces valid move set.
     *
     * @param move move to be attempted
     *
     * @return valid which specifies if it is a valid or not
     **********************************************************************/
    public boolean isValidMove(Move move) {
        boolean valid = false;

        //checks that piece is there and move is valid for that piece
        if (board[move.fromRow][move.fromColumn] != null)
            if (board[move.fromRow][move.fromColumn].isValidMove(move, board))
                return true;

        return valid;
    }

    /**********************************************************************
     * Moves the piece from its current coordinates to a new set of
     * coordinates. Then sets old coordinate to null.
     *
     * @param move move to be attempted
     **********************************************************************/
    public void move(Move move) {

        //save the move that was successful
        saveMove(move);
        board[move.toRow][move.toColumn] =
                board[move.fromRow][move.fromColumn];
        board[move.fromRow][move.fromColumn] = null;
    }

    /**********************************************************************
     * Checks if the current players piece is in check by seeing if the
     * opposing player has any valid moves on the king.
     *
     * @param  p player checked to be in check
     *
     * @return valid whether player is in check or not
     **********************************************************************/
    public boolean inCheck(Player p) {
        boolean valid = false;
        Move move;
        int toRow = -1;
        int toColumn = -1;
        int fromRow;
        int fromColumn;

        //Grab king location
        for (int c = 0; c < numColumns(); c++) {
            for (int r = 0; r < numColumns(); r++) {

                //piece is in spot [r][c], is type king, and is player p piece
                if (board[r][c] != null && board[r][c].type().equals("King")
                        && board[r][c].player() != p.next()) {
                    toRow = r;
                    toColumn = c;
                }
            }
        }

        //Check to see if opposing piece can move on king
        for (int c = 0; c < numColumns(); c++) {
            for (int r = 0; r < numRows(); r++) {

                //Piece there and other player
                if (board[r][c] != null && board[r][c].player()
                        == p.next()) {
                    fromColumn = c;
                    fromRow = r;
                    move = new Move(fromRow, fromColumn,
                            toRow, toColumn);

                    //when piece can move to king spot
                    if (board[r][c].isValidMove(move, board)) {
                        valid = true;
                    }
                }
            }
        }
        return valid;
    }

    /**********************************************************************
     * Current player in game of chess (Black or White)
     *
     * @return player whose turn it is
     **********************************************************************/
    public Player currentPlayer() {
        return player;
    }

    /**********************************************************************
     * Number of rows on the chess board
     *
     * @return 8, number of rows on chess board
     **********************************************************************/
    public int numRows() {
        return 8;
    }

    /**********************************************************************
     * Number of columns on the chess board
     *
     * @return 8, number of columns on chess board
     **********************************************************************/
    public int numColumns() {
        return 8;
    }

    /**********************************************************************
     * Grabs piece at certain spot on the chess board
     *
     * @param row row of piece
     * @param column column of piece
     *
     * @return board[row][column] , piece on chess board
     **********************************************************************/
    public IChessPiece pieceAt(int row, int column) {
        return board[row][column];
    }

    /**********************************************************************
     * Changes the current player the other player
     **********************************************************************/
    public void setNextPlayer() {
        player = player.next();
    }

    /**********************************************************************
     * Sets a location on the board to an IChessPiece piece
     *
     * @param piece Chess piece that has a type and owner
     * @param row row of piece to be changed
     * @param column column of piece to be changed
     **********************************************************************/
    public void setPiece(int row, int column, IChessPiece piece) {
        board[row][column] = piece;
    }

    /**********************************************************************
     * AI method that finds first possible move out of check
     **********************************************************************/
    public void getOutOfCheck() {
        Move move;

        //Grab a black piece
        for (int fromRow = 0; fromRow < numRows(); fromRow++) {
            for (int fromColumn = 0; fromColumn < numColumns();
                 fromColumn++) {
                if (board[fromRow][fromColumn] != null) {
                    if (board[fromRow][fromColumn].player() ==
                            Player.BLACK) {

                        //All possible locations to move
                        for (int toRow = 0; toRow < numRows();
                             toRow++) {
                            for (int toColumn = 0; toColumn <
                                    numColumns(); toColumn++) {

                                move = new Move(fromRow, fromColumn,
                                        toRow, toColumn);
                                if (board[fromRow][fromColumn]
                                        != null) {

                                    /*Tests that move is valid and AI
                                    hasn't moved yet*/
                                    if (board[fromRow][fromColumn]
                                            .isValidMove(move, board)
                                            && !AIMoved) {

                                        move(move);

                                        /*Undo move is player is still
                                         in check*/
                                        if (inCheck(Player.BLACK)) {
                                            unMove();
                                        } else {
                                            AIMoved = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**********************************************************************
     * AI method finds first piece that is in danger of being captured,
     * then moves that piece to first possible safe location.
     **********************************************************************/
    public void attemptToRemoveFromDanger() {

        Move whiteOntoBlack;

        //Find a white piece
        for (int fromRow = 0; fromRow < numRows(); fromRow++) {
            for (int fromColumn = 0; fromColumn < numColumns();
                 fromColumn++) {
                if (board[fromRow][fromColumn] != null) {
                    if (board[fromRow][fromColumn].player() ==
                            Player.WHITE) {

                        //Find a black piece
                        for (int toRow = 0; toRow < numRows();
                             toRow++) {
                            for (int toColumn = 0; toColumn <
                                    numColumns(); toColumn++) {
                                if (board[toRow][toColumn] != null) {
                                    if (board[toRow][toColumn].player()
                                            == Player.BLACK) {

                                        if (board[fromRow][fromColumn] !=
                                                null && board[toRow][toColumn]
                                                != null) {

                                            whiteOntoBlack = new Move
                                                    (fromRow, fromColumn, toRow, toColumn);

                                            //Check to see if white can capture black piece
                                            if (board[fromRow][fromColumn]
                                                    .isValidMove(whiteOntoBlack,
                                                            board) && !AIMoved) {

                                                //Attempt to move black piece without
                                                // getting in check
                                                attemptMove(toRow, toColumn);

                                                //If AI is in check undo the move
                                                if (inCheck(Player.BLACK)) {
                                                    unMove();
                                                    AIMoved = false;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**********************************************************************
     * AI method moves piece in given location to first possible open spot
     * on the board
     *
     * @param blackRow row of current piece to be moved
     * @param blackColumn column of current piece to be moved
     **********************************************************************/
    public void attemptMove(int blackRow, int blackColumn) {

        Move move;

        //Check all possible moves
        for (int toRow = 0; toRow < numRows(); toRow++) {
            for (int toColumn = 0; toColumn < numColumns(); toColumn++) {

                move = new Move(blackRow, blackColumn, toRow, toColumn);

                if (board[blackRow][blackColumn] != null) {

                    //Once first valid move found, move piece
                    if (board[blackRow][blackColumn]
                            .isValidMove(move, board)) {
                        move(move);
                        AIMoved = true;
                    }
                }

            }
        }
    }

    /**********************************************************************
     * AI method attempts to take a player piece without putting that
     * piece in danger.
     **********************************************************************/
    public void attemptToTakeAPiece() {

        Move moveOnWhitePiece;

        //Find a black piece
        for (int fromRow = 0; fromRow < numRows(); fromRow++) {
            for (int fromColumn = 0; fromColumn < numColumns();
                 fromColumn++) {
                if (board[fromRow][fromColumn] != null) {
                    if (board[fromRow][fromColumn].player()
                            == Player.BLACK) {

                        //Find a white piece
                        for (int toRow = 0; toRow < numRows();
                             toRow++) {
                            for (int toColumn = 0; toColumn <
                                    numColumns(); toColumn++) {
                                if (board[toRow][toColumn] != null) {
                                    if (board[toRow][toColumn].player()
                                            == Player.WHITE) {

                                        if (board[toRow][toColumn] !=
                                                null && board[fromRow]
                                                [fromColumn] != null) {

                                            //move black piece to white piece if move is valid
                                            moveOnWhitePiece = new Move(fromRow,
                                                    fromColumn, toRow,
                                                    toColumn);
                                            if (board[fromRow][fromColumn]
                                                    .isValidMove(moveOnWhitePiece,
                                                            board) && !AIMoved) {

                                                move(moveOnWhitePiece);
                                                AIMoved = true;

                                                //If AI is in check or piece is in danger, undo move
                                                if (inCheck(Player.BLACK)
                                                        || inDanger(toRow, toColumn)) {
                                                    unMove();
                                                    AIMoved = false;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**********************************************************************
     * AI method tries to put the player in check (or checkmate)
     **********************************************************************/
    public void attemptToCheckmate() {

        Move moveToCheck;

        //Find a black piece
        for (int fromRow = 0; fromRow < numRows(); fromRow++) {
            for (int fromColumn = 0; fromColumn < numColumns();
                 fromColumn++) {
                if (board[fromRow][fromColumn] != null) {
                    if (board[fromRow][fromColumn].player() ==
                            Player.BLACK) {

                        //Make moves for every possible location
                        for (int toRow = 0; toRow < numRows();
                             toRow++) {
                            for (int toColumn = 0; toColumn <
                                    numColumns(); toColumn++) {

                                moveToCheck = new Move(fromRow,
                                        fromColumn, toRow, toColumn);
                                if (board[fromRow][fromColumn]
                                        != null) {

                                    //Test to see if moves are valid
                                    if (board[fromRow][fromColumn]
                                            .isValidMove(moveToCheck,
                                                    board) &&
                                            !AIMoved) {
                                        move(moveToCheck);

                                        //Conditions to undo AI move
                                        if (!inCheck(Player.WHITE) ||
                                                inDanger(toRow,
                                                        toColumn) ||
                                                inCheck(Player
                                                        .BLACK)){
                                            unMove();
                                            AIMoved = false;
                                        } else {
                                            AIMoved = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**********************************************************************
     * AI method checks to see if piece at certain location is in danger
     * of being captured.
     *
     * @param blackRow row of black piece on the board
     * @param blackColumn column of black piece on the board
     *
     * @return true or false depending on if piece is in danger
     **********************************************************************/
    public boolean inDanger(int blackRow, int blackColumn) {
        Move outDanger;

        //Finds a white piece
        for (int fromRow = 0; fromRow < numRows(); fromRow++) {
            for (int fromColumn = 0; fromColumn < numColumns();
                 fromColumn++) {
                if (board[fromRow][fromColumn] != null) {
                    if (board[fromRow][fromColumn].player() ==
                            Player.WHITE) {

                        outDanger = new Move(fromRow, fromColumn,
                                blackRow, blackColumn);

                        //When white piece can move onto location, piece is in danger
                        if (board[fromRow][fromColumn].isValidMove
                                (outDanger, board)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**********************************************************************
     * AI method checks moves first available piece to move, if AI hasn't
     * moved yet. Presets AI's first two turns if player isn't able
     * to be put in check.
     **********************************************************************/
    public void nothingElseToDoButMovePiece() {

        Move nothingElse;

        if (first2Turns >= 2) {
            //Finds a black pawn to move first
            for (int fromRow = 0; fromRow < numRows(); fromRow++) {
                for (int fromColumn = 0; fromColumn < numColumns();
                     fromColumn++) {
                    if (board[fromRow][fromColumn] != null) {
                        if (board[fromRow][fromColumn].player()
                                == Player.BLACK) {

                            //All possible moves
                            for (int toRow = 0; toRow < numRows();
                                 toRow++) {
                                for (int toColumn = 0; toColumn <
                                        numColumns(); toColumn++) {

                                    if (board[fromRow][fromColumn]
                                            != null) {
                                        nothingElse = new Move(fromRow,
                                                fromColumn, toRow,
                                                toColumn);

                                        /*Valid move and AI has not
                                        moved yet*/
                                        if ((board[fromRow][fromColumn]
                                                .isValidMove(
                                                        nothingElse,
                                                        board)) &&
                                                !AIMoved) {
                                            move(nothingElse);

                                            /*Piece does not move into
                                             danger*/
                                            if (!inDanger(toRow,
                                                    toColumn)) {
                                                AIMoved = true;
                                            } else {
                                                unMove();
                                                AIMoved = false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        //Second turn default move if no check opportunity
        if (first2Turns == 1) {
            nothingElse = new Move(1, 3, 3, 3);
            if (!AIMoved) {
                move(nothingElse);
                first2Turns++;
                AIMoved = true;
            }
        }

        //First turn default move
        if (first2Turns == 0) {
            nothingElse = new Move(1, 4, 2, 4);
            if (!AIMoved) {
                move(nothingElse);
                first2Turns++;
                AIMoved = true;
            }
        }
    }

    /**********************************************************************
     * Checks to see if AI has won the game.
     *
     * @return valid whether AI has won or not.
     **********************************************************************/
    public boolean AIWon() {

        boolean valid = false;
        Move move;

        //King is in check
        if (inCheck(Player.WHITE)) {

            valid = true;

            //Check spots on the board
            for (int fromRow = 0; fromRow < numRows(); fromRow++) {
                for (int fromColumn = 0; fromColumn < numColumns();
                     fromColumn++) {

                    //Check that it is not null
                    if (board[fromRow][fromColumn] != null) {

                        /*Check that the piece belongs to same player
                         in check*/
                        if (board[fromRow][fromColumn].player() ==
                                Player.WHITE) {

                            //Check all possible moves for that piece
                            for (int toRow = 0; toRow < numRows(); toRow++) {
                                for (int toColumn = 0; toColumn <
                                        numColumns(); toColumn++) {

                                    move = new Move(fromRow, fromColumn,
                                            toRow, toColumn);

                                    /*Check to see if player still in
                                     check after each move*/
                                    if (board[fromRow][fromColumn]
                                            .isValidMove(move, board)) {

                                        IChessPiece tempPiece =
                                                board[toRow][toColumn];

                                        move(move);

                                        /*If white is not in check AI
                                        has not won*/
                                        if (!inCheck(Player.WHITE)) {

                                            valid = false;
                                        }
                                        unMove();

                                        board[toRow][toColumn] =
                                                tempPiece;

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return valid;
    }

    /**********************************************************************
     * AI (Black) that plays against player (White). First tries to get
     * out of check (If in check). Tries to put player into check or
     * checkmate next. If no possible check moves, AI tries to safely
     * capture a piece, or will attempt to remove one of its pieces from
     * danger. Moves first piece if no pieces can be captured and no
     * AI piece is in danger.
     **********************************************************************/
    public void AI() {

        //AI move set to false each turn
        AIMoved = false;

        //When one piece has moved in any of these methods
        //AIMoved will be true

        //See if AI is in check
        if (inCheck(Player.BLACK)) {
            getOutOfCheck();
        } else {
            attemptToCheckmate();
            attemptToTakeAPiece();
            attemptToRemoveFromDanger();
            nothingElseToDoButMovePiece();
        }

        //AI promotion
        for(int i = 0; i < numColumns(); i++){

            IChessPiece promo = new Queen(Player.BLACK);

            if(board[7][i] != null) {
                if (pieceAt(7, i).type().equals("Pawn")) {
                    setPiece(7, i, promo);
                }
            }
        }
    }

    /**********************************************************************
     * Main method testing
     **********************************************************************/
    public static void main(String[] args) {
        ChessModel cb = new ChessModel();
        Move m = new Move(6, 2, 4, 2);
        cb.AI();
        Move m1 = new Move(4, 2, 3, 2);
        cb.AI();
        Move m3 = new Move(3, 2, 2, 2);

        if (cb.isValidMove(m)) {
            cb.move(m);
        }
        if (cb.isValidMove(m1)) {
            cb.move(m1);
        }
        if (cb.isValidMove(m3)) {
            cb.move(m3);
        }
        cb.AI();
        cb.display();
    }

    /**********************************************************************
     * Grabs the chess board of type IChessPiece[][]
     *
     * @return board which is a 8x8 array
     **********************************************************************/
    public IChessPiece[][] getBoard() {
        return board;
    }

    /**********************************************************************
     * Creates a new save state object, and assigns that save state a
     * fromRow, fromColumn, toRow, toColumn, fromPiece, and toPiece, and
     * adds that object to arraylist of type SaveState.
     *
     * @param move move that has just been done
     **********************************************************************/
    public void saveMove(Move move) {

        SaveState state = new SaveState();
        state.saveFromRow = move.fromRow;
        state.saveFromCol = move.fromColumn;
        state.saveFromPiece = board[move.fromRow][move.fromColumn];
        state.saveToCol = move.toColumn;
        state.saveToRow = move.toRow;
        state.saveToPiece = board[move.toRow][move.toColumn];

        list.add(state);
    }

    /**********************************************************************
     * Undoes the most recent move by accessing the save states, and
     * removing the last one in the arraylist. Pieces updated to account
     * for any captures using save states.
     **********************************************************************/
    public void unMove() {

        SaveState state = list.remove(list.size() - 1);
        board[state.saveFromRow][state.saveFromCol] =
                state.saveFromPiece;
        board[state.saveToRow][state.saveToCol] =
                state.saveToPiece;
    }

    /**********************************************************************
     * Displays board in main
     **********************************************************************/
    public void display() {
        IChessPiece[][] board = getBoard();

        for (int r = 7; r >= 0; r--) {
            for (int c = 7; c >= 0; c--) {
                if (board[r][c] != null)
                    System.out.print(board[r][c].type() + "\t");
                else
                    System.out.print("null\t");
            }
            System.out.println();
        }
    }
}
