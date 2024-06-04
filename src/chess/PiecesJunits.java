package chess;

import org.junit.Test;

import static org.junit.Assert.*;

public class PiecesJunits {


    @Test
    public void testTypeMethod() {
        IChessPiece Queen = new Queen(Player.WHITE);
        assertEquals(Queen.type(), "Queen");
    }

    @Test
    public void testPawnMovement(){
        ChessModel c = new ChessModel();
        Move m = new Move(1,1, 3, 1);

        if(c.isValidMove(m)){
            c.move(m);
        }

        assertEquals(c.pieceAt(3, 1).type(), "Pawn");
        assertEquals(c.pieceAt(3, 1).player(), Player.BLACK);
        }

    @Test
    public void testKnightMovement(){
        ChessModel c = new ChessModel();
        Move m = new Move(7,1, 5, 0);

        if(c.isValidMove(m)){
            c.move(m);
        }

        assertEquals(c.pieceAt(5, 0).type(), "Knight");
        assertEquals(c.pieceAt(5, 0).player(), Player.WHITE);
    }

    @Test
    public void testBishopMovement(){
        ChessModel c = new ChessModel();
        Move m = new Move(1,4, 3, 4);
        Move m1 = new Move(0,5, 5, 0);

        if(c.isValidMove(m)){
            c.move(m);
        }

        if(c.isValidMove(m1)){
            c.move(m1);
        }

        assertEquals(c.pieceAt(5, 0).type(), "Bishop");
        assertEquals(c.pieceAt(5, 0).player(), Player.BLACK);
    }

    @Test
    public void testQueenMovement(){
        ChessModel c = new ChessModel();
        Move m = new Move(1,4, 3, 4);
        Move m1 = new Move(0,3, 2, 5);

        if(c.isValidMove(m)){
            c.move(m);
        }

        if(c.isValidMove(m1)){
            c.move(m1);
        }

        assertEquals(c.pieceAt(2, 5).type(), "Queen");

        Move m2 = new Move(2,5, 2, 0);
        if(c.isValidMove(m2)){
            c.move(m2);
        }


        assertEquals(c.pieceAt(2, 0).type(), "Queen");
        assertTrue(c.pieceAt(2, 5) == null);
    }

    @Test
    public void testRookMovement(){
        ChessModel c = new ChessModel();
        Move m = new Move(1,7, 3, 7);
        Move m1 = new Move(0,7, 2, 7);
        Move m2 = new Move(2,7, 2, 0);

        if(c.isValidMove(m)){
            c.move(m);
        }

        if(c.isValidMove(m1)){
            c.move(m1);
        }

        if(c.isValidMove(m2)){
            c.move(m2);
        }

        assertEquals(c.pieceAt(2, 0).type(), "Rook");
    }

    @Test
    public void testKingMovement(){
        ChessModel c = new ChessModel();
        Move m = new Move(6,4, 4, 4);
        Move m1 = new Move(7,3, 5, 5);
        Move m2 = new Move(1,4, 3, 4);
        Move m3 = new Move(0,4, 1, 4);


        if(c.isValidMove(m)){
            c.move(m);
        }

        if(c.isValidMove(m1)){
            c.move(m1);
        }

        if(c.isValidMove(m2)){
            c.move(m2);
        }

        if(c.isValidMove(m3)){
            c.move(m3);
        }

        assertEquals(c.pieceAt(1, 4).type(), "King");

        Move m4 = new Move(1,4, 2, 5);

        if(c.isValidMove(m4)){
            c.move(m4);
        }

        assertEquals(c.pieceAt(1, 4).type(), "King");
    }

}
