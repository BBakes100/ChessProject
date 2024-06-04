package chess;

import java.awt.*;
import java.awt.event.*;
import java.sql.Array;
import java.util.ArrayList;
import javax.swing.*;

public class ChessPanel extends JPanel {

    private JButton[][] board;
    private JButton undo;
    private ChessModel model;

    private JButton promoQueen;
    private JButton promoBishop;
    private JButton promoKnight;
    private JButton promoRook;

    private ImageIcon wRook;
    private ImageIcon wBishop;
    private ImageIcon wQueen;
    private ImageIcon wKing;
    private ImageIcon wPawn;
    private ImageIcon wKnight;

    private ImageIcon bRook;
    private ImageIcon bBishop;
    private ImageIcon bQueen;
    private ImageIcon bKing;
    private ImageIcon bPawn;
    private ImageIcon bKnight;

    private boolean firstTurnFlag;
    private boolean whiteTurn;
    private int fromRow;
    private int toRow;
    private int fromCol;
    private int toCol;

    private listener listener;

    public ChessPanel() {
        model = new ChessModel();
        board = new JButton[model.numRows()][model.numColumns()];

        listener = new listener();
        createIcons();

        undo = new JButton("Undo", null);
        add(undo);
        undo.addActionListener(listener);
        undo.setVisible(true);

        JPanel boardpanel = new JPanel();
        JPanel buttonpanel = new JPanel();
        boardpanel.setLayout(new GridLayout(model.numRows(), model.numColumns(), 1, 1));

        for (int r = 0; r < model.numRows(); r++) {
            for (int c = 0; c < model.numColumns(); c++) {
                if (model.pieceAt(r, c) == null) {
                    board[r][c] = new JButton("", null);
                    board[r][c].addActionListener(listener);

                    setBackGroundColor(r, c);
                    boardpanel.add(board[r][c]);

                } else if (model.pieceAt(r, c).player() == Player.WHITE) {
                    placeWhitePieces(r, c);

                    setBackGroundColor(r, c);
                    boardpanel.add(board[r][c]);
                } else if (model.pieceAt(r, c).player() == Player.BLACK) {
                    placeBlackPieces(r, c);

                    setBackGroundColor(r, c);
                    boardpanel.add(board[r][c]);
                }
            }
        }
        add(boardpanel, BorderLayout.WEST);
        boardpanel.setPreferredSize(new Dimension(600, 600));
        add(buttonpanel);

        ImageIcon BQ = new ImageIcon("C:/Users/benja/OneDrive/Documents/CIS 163/piece images/bQueen.png");
        promoQueen = new JButton(null, BQ);
        ImageIcon BR = new ImageIcon("C:/Users/benja/OneDrive/Documents/CIS 163/piece images/bRook.png");
        promoRook = new JButton(null, BQ);
        ImageIcon BB = new ImageIcon("C:/Users/benja/OneDrive/Documents/CIS 163/piece images/bBishop.png");
        promoBishop = new JButton(null, BQ);
        ImageIcon BK = new ImageIcon("C:/Users/benja/OneDrive/Documents/CIS 163/piece images/bKnight.png");
        promoKnight = new JButton(null, BQ);
        ImageIcon WQ = new ImageIcon("C:/Users/benja/OneDrive/Documents/CIS 163/piece images/wQueen.png");
//        wpromoQueen = new JButton(null, BQ);
//        ImageIcon WR = new ImageIcon("C:/Users/benja/OneDrive/Documents/CIS 163/piece images/wRook.png");
//        wpromoRook = new JButton(null, BQ);
//        ImageIcon WB = new ImageIcon("C:/Users/benja/OneDrive/Documents/CIS 163/piece images/wBishop.png");
//        wpromoBishop = new JButton(null, BQ);
//        ImageIcon WK = new ImageIcon("C:/Users/benja/OneDrive/Documents/CIS 163/piece images/wKnight.png");
//        wpromoKight = new JButton(null, BQ);
        add(promoQueen);
        add(promoRook);
        add(promoBishop);
        add(promoKnight);
//        add();
//        add();
//        add();
//        add();
//        add();
//        add();
//        add();
        promoQueen.addActionListener(listener);
        promoQueen.setVisible(false);
        promoRook.addActionListener(listener);
        promoRook.setVisible(false);
        promoBishop.addActionListener(listener);
        promoBishop.setVisible(false);
        promoKnight.addActionListener(listener);
        promoKnight.setVisible(false);
//        promoQueen.addActionListener(listener);
//        promoQueen.setVisible(false);
//        promoQueen.addActionListener(listener);
//        promoQueen.setVisible(false);
//        promoQueen.addActionListener(listener);
//        promoQueen.setVisible(false);
//        promoQueen.addActionListener(listener);
//        promoQueen.setVisible(false);


        firstTurnFlag = true;
        whiteTurn = true;
    }

    private void setBackGroundColor(int r, int c) {
        if ((c % 2 == 1 && r % 2 == 0) || (c % 2 == 0 && r % 2 == 1)) {
            board[r][c].setBackground(Color.LIGHT_GRAY);
        } else if ((c % 2 == 0 && r % 2 == 0) || (c % 2 == 1 && r % 2 == 1)) {
            board[r][c].setBackground(Color.WHITE);
        }
    }

    private void placeWhitePieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, wPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, wRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, wKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, wBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, wQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, wKing);
            board[r][c].addActionListener(listener);
        }
    }

    private void placeBlackPieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, bPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, bRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, bKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, bBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, bQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, bKing);
            board[r][c].addActionListener(listener);
        }
    }

    private void createIcons() {
//        // Sets the Image for white player pieces
//        wRook = new ImageIcon("C:/Users/Brandon/IdeaProjects/CIS163/src/chess/wRook.png");
//        wBishop = new ImageIcon("C:/Users/Brandon/IdeaProjects/CIS163/src/chess/wBishop.png");
//        wQueen = new ImageIcon("C:/Users/Brandon/IdeaProjects/CIS163/src/chess/wQueen.png");
//        wKing = new ImageIcon("C:/Users/Brandon/IdeaProjects/CIS163/src/chess/wKing.png");
//        wPawn = new ImageIcon("C:/Users/Brandon/IdeaProjects/CIS163/src/chess/wPawn.png");
//        wKnight = new ImageIcon("C:/Users/Brandon/IdeaProjects/CIS163/src/chess/wKnight.png");
//
//        // Sets the Image for black player pieces
//        bRook = new ImageIcon("C:/Users/Brandon/IdeaProjects/CIS163/src/chess/bRook.png");
//        bBishop = new ImageIcon("C:/Users/Brandon/IdeaProjects/CIS163/src/chess/bBishop.png");
//        bQueen = new ImageIcon("C:/Users/Brandon/IdeaProjects/CIS163/src/chess/bQueen.png");
//        bKing = new ImageIcon("C:/Users/Brandon/IdeaProjects/CIS163/src/chess/bKing.png");
//        bPawn = new ImageIcon("C:/Users/Brandon/IdeaProjects/CIS163/src/chess/bPawn.png");
//        bKnight = new ImageIcon("C:/Users/Brandon/IdeaProjects/CIS163/src/chess/bKnight.png");

        // Sets the Image for white player pieces
        wRook = new ImageIcon("C:/Users/benja/OneDrive/Documents/CIS 163/piece images/wRook.png");
        wBishop = new ImageIcon("C:/Users/benja/OneDrive/Documents/CIS 163/piece images/wBishop.png");
        wQueen = new ImageIcon("C:/Users/benja/OneDrive/Documents/CIS 163/piece images/wQueen.png");
        wKing = new ImageIcon("C:/Users/benja/OneDrive/Documents/CIS 163/piece images/wKing.png");
        wPawn = new ImageIcon("C:/Users/benja/OneDrive/Documents/CIS 163/piece images/wPawn.png");
        wKnight = new ImageIcon("C:/Users/benja/OneDrive/Documents/CIS 163/piece images/wKnight.png");

        // Sets the Image for black player pieces
        bRook = new ImageIcon("C:/Users/benja/OneDrive/Documents/CIS 163/piece images/bRook.png");
        bBishop = new ImageIcon("C:/Users/benja/OneDrive/Documents/CIS 163/piece images/bBishop.png");
        bQueen = new ImageIcon("C:/Users/benja/OneDrive/Documents/CIS 163/piece images/bQueen.png");
        bKing = new ImageIcon("C:/Users/benja/OneDrive/Documents/CIS 163/piece images/bKing.png");
        bPawn = new ImageIcon("C:/Users/benja/OneDrive/Documents/CIS 163/piece images/bPawn.png");
        bKnight = new ImageIcon("C:/Users/benja/OneDrive/Documents/CIS 163/piece images/bKnight.png");

//        // Sets the Image for white player pieces
//        wRook = new ImageIcon("C:/Users/polar/GitHub/ChessPRJ/src/chess/wRook.png");
//        wBishop = new ImageIcon("C:/Users/polar/GitHub/ChessPRJ/src/chess/wBishop.png");
//        wQueen = new ImageIcon("C:/Users/polar/GitHub/ChessPRJ/src/chess/wQueen.png");
//        wKing = new ImageIcon("C:/Users/polar/GitHub/ChessPRJ/src/chess/wKing.png");
//        wPawn = new ImageIcon("C:/Users/polar/GitHub/ChessPRJ/src/chess/wPawn.png");
//        wKnight = new ImageIcon("C:/Users/polar/GitHub/ChessPRJ/src/chess/wKnight.png");
//
//        // Sets the Image for black player pieces
//        bRook = new ImageIcon("C:/Users/polar/GitHub/ChessPRJ/src/chess/bRook.png");
//        bBishop = new ImageIcon("C:/Users/polar/GitHub/ChessPRJ/src/chess/bBishop.png");
//        bQueen = new ImageIcon("C:/Users/polar/GitHub/ChessPRJ/src/chess/bQueen.png");
//        bKing = new ImageIcon("C:/Users/polar/GitHub/ChessPRJ/src/chess/bKing.png");
//        bPawn = new ImageIcon("C:/Users/polar/GitHub/ChessPRJ/src/chess/bPawn.png");
//        bKnight = new ImageIcon("C:/Users/polar/GitHub/ChessPRJ/src/chess/bKnight.png");

    }

    // method that updates the board
    private void displayBoard() {

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++)
                if (model.pieceAt(r, c) == null)
                    board[r][c].setIcon(null);
                else if (model.pieceAt(r, c).player() == Player.WHITE) {
                    if (model.pieceAt(r, c).type().equals("Pawn"))
                        board[r][c].setIcon(wPawn);

                    if (model.pieceAt(r, c).type().equals("Rook"))
                        board[r][c].setIcon(wRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                        board[r][c].setIcon(wKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                        board[r][c].setIcon(wBishop);

                    if (model.pieceAt(r, c).type().equals("Queen"))
                        board[r][c].setIcon(wQueen);

                    if (model.pieceAt(r, c).type().equals("King"))
                        board[r][c].setIcon(wKing);

                } else if (model.pieceAt(r, c).player() == Player.BLACK) {
                    if (model.pieceAt(r, c).type().equals("Pawn"))
                        board[r][c].setIcon(bPawn);

                    if (model.pieceAt(r, c).type().equals("Rook"))
                        board[r][c].setIcon(bRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                        board[r][c].setIcon(bKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                        board[r][c].setIcon(bBishop);

                    if (model.pieceAt(r, c).type().equals("Queen"))
                        board[r][c].setIcon(bQueen);

                    if (model.pieceAt(r, c).type().equals("King"))
                        board[r][c].setIcon(bKing);

                }
        }
        repaint();
    }

    // inner class that represents action listener for buttons
    //Check player with flags
    //Record first piece click
    //Check the second click, make sure it is valid, move piece, switch turn
    private class listener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            boolean AIon = false;

            if(event.getSource() == undo){
            try {
                model.unMove();
                displayBoard();
                if(!AIon) {
                    model.setNextPlayer();
                }
            }catch(IndexOutOfBoundsException e){
                JOptionPane.showMessageDialog(null, "Cant undo past beginning");
                }
            }

            for (int r = 0; r < model.numRows(); r++)
                for (int c = 0; c < model.numColumns(); c++)
                    if (board[r][c] == event.getSource())
                        //Record first piece click
                        if(firstTurnFlag) {
                            try {
                                //Same color piece is grabbed
                                if (model.pieceAt(r, c).player() == model.currentPlayer()) {

                                    fromRow = r;
                                    fromCol = c;
                                    firstTurnFlag = false;
                                }

                                //empty square selected will give null pointer
                            }catch(NullPointerException e){
                                JOptionPane.showMessageDialog(null, "Select a " + model.currentPlayer() + " piece");
                            }

                            //Second Click
                        } else {
                            toRow = r;
                            toCol = c;
                            firstTurnFlag = true;
                            Move m = new Move(fromRow, fromCol, toRow, toCol);

                            //check valid move
                            if ((model.isValidMove(m))) {

                                model.move(m);

                                //White promotion
                                if((toRow == 0) && (model.pieceAt(toRow, toCol).type().equals("Pawn"))) {

                                        IChessPiece temp = new Queen(Player.WHITE);
                                        //New Window pops up for choice
                                        model.setPiece(toRow,toCol, temp);

                                }

                                //Black promotion
                                if((toRow == 7) && (model.pieceAt(toRow, toCol).type().equals("Pawn"))) {

                                    IChessPiece temp = new Queen(Player.BLACK);
                                    //New Window pops up for choice
                                    model.setPiece(toRow,toCol, temp);

                                }

                                //make sure player moving is out of check and move back if they are
                                if(model.inCheck(model.currentPlayer())){

                                    JOptionPane.showMessageDialog(null, model.currentPlayer() + " needs to get out of check!");
                                    model.unMove();
                                    firstTurnFlag = true;

                                    //player not in check already
                                }else{

                                    //display board and next turn
                                    displayBoard();

                                    //checkmate
                                    if(model.isComplete()){
                                        JOptionPane.showMessageDialog(null, model.currentPlayer().next() + " won!");
                                    }

                                    //just check not checkmate
                                    else if(model.inCheck(model.currentPlayer().next())){
                                        JOptionPane.showMessageDialog(null, model.currentPlayer().next() + " is in check!");
                                    }

                                    firstTurnFlag = true;

                                    if(AIon) {
                                        //AI
                                        model.AI();
                                        displayBoard();

                                        //AI checkmate
                                        if (model.AIWon()) {
                                            JOptionPane.showMessageDialog(null, "AI won!");
                                        }

                                        //AI check
                                        else if (model.inCheck(model.currentPlayer())) {
                                            JOptionPane.showMessageDialog(null, model.currentPlayer() + " is in check!");
                                        }
                                    } else {
                                        model.setNextPlayer();
                                    }




                                }
                            }
                        }
        }
    }
}
