package chess;

public class King extends ChessPiece {

	public King(Player player) {
		super(player);
	}

	public String type() {
		return "King";
	}

	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean valid = false;

		if (super.isValidMove(move, board)) {

			if ((move.toRow == move.fromRow + 1 && move.toColumn
					== move.fromColumn) ||
					(move.toRow == move.fromRow - 1 && move.toColumn
							== move.fromColumn) ||
					(move.toRow == move.fromRow && move.toColumn
							== move.fromColumn + 1) ||
					(move.toRow == move.fromRow && move.toColumn
							== move.fromColumn - 1) ||
					(move.toRow == move.fromRow + 1 && move.toColumn
							== move.fromColumn + 1) ||
					(move.toRow == move.fromRow - 1 && move.toColumn
							== move.fromColumn + 1) ||
					(move.toRow == move.fromRow + 1 && move.toColumn
							== move.fromColumn - 1) ||
					(move.toRow == move.fromRow - 1 && move.toColumn
							== move.fromColumn - 1)) {

				valid = true;

				//Still need to make sure king doesn't move into check

				Move notKingMove;

				//Check to see if opposing piece can move on king
				for (int c = 0; c < 8; c++) {
					for (int r = 0; r < 8; r++) {

						//Piece there and other player
						if (board[r][c] != null && board[r][c].player()
								!= board[move.fromRow][move.fromColumn]
								.player()) {


							if (board[r][c].type().equals("Bishop") ||
									board[r][c].type().equals("Rook")
									|| board[r][c].type().equals("Queen")
									|| board[r][c].type()
									.equals("Knight")) {

								notKingMove = new Move(r, c,
										move.toRow, move.toColumn);

								//King captures a piece
								if (board[move.toRow][move.toColumn] !=
										null && board[move.toRow]
										[move.toColumn].player()
										!= board[r][c].player() &&
										((move.toRow == move.fromRow + 1
												&& move.toColumn ==
												move.fromColumn) ||
												(move.toRow ==
														move.fromRow - 1
														&& move.toColumn
														== move.fromColumn) ||
												(move.toRow == move.fromRow
														&& move.toColumn ==
														move.fromColumn + 1) ||
												(move.toRow == move.fromRow &&
														move.toColumn
																== move.fromColumn - 1) ||
												(move.toRow == move.fromRow + 1 &&
														move.toColumn == move.fromColumn + 1) ||
												(move.toRow == move.fromRow - 1 &&
														move.toColumn == move.fromColumn + 1) ||
												(move.toRow == move.fromRow + 1 &&
														move.toColumn == move.fromColumn - 1) ||
												(move.toRow == move.fromRow - 1 &&
														move.toColumn == move.fromColumn - 1))) {

									IChessPiece tempPiece = board[move.toRow][move.toColumn];
									board[move.toRow][move.toColumn] = null;

									IChessPiece tempPieceKing = board[move.fromRow][move.fromColumn];
									board[move.fromRow][move.fromColumn] = null;

									//when piece can move to king spot
									try {
										if (board[r][c].isValidMove(notKingMove, board)) {
											valid = false;
										}
									} catch (NullPointerException e) {

										//Not sure what to put in here

									} finally {


										board[move.toRow][move.toColumn] = tempPiece;
										board[move.fromRow][move.fromColumn] = tempPieceKing;
									}


								} else {

									if (((move.toRow == move.fromRow + 1 &&
											move.toColumn == move.fromColumn) ||
											(move.toRow == move.fromRow - 1 &&
													move.toColumn == move.fromColumn) ||
											(move.toRow == move.fromRow &&
													move.toColumn == move.fromColumn + 1) ||
											(move.toRow == move.fromRow &&
													move.toColumn == move.fromColumn - 1) ||
											(move.toRow == move.fromRow + 1 &&
													move.toColumn == move.fromColumn + 1) ||
											(move.toRow == move.fromRow - 1 &&
													move.toColumn == move.fromColumn + 1) ||
											(move.toRow == move.fromRow + 1 &&
													move.toColumn == move.fromColumn - 1) ||
											(move.toRow == move.fromRow - 1 &&
													move.toColumn == move.fromColumn - 1))) {

										//when piece can move to king spot
										IChessPiece tempPieceKing = board[move.fromRow]
												[move.fromColumn];
										board[move.fromRow][move.fromColumn] = null;

										if (board[r][c].isValidMove(notKingMove, board)) {
											valid = false;
										}

										board[move.fromRow][move.fromColumn] = tempPieceKing;

									}
								}

							}

							if (board[r][c].type().equals("King")) {

								notKingMove = new Move(r, c, move.toRow, move.toColumn);

								//when piece can move to king spot
								if((notKingMove.fromRow == move.toRow + 1 &&
										notKingMove.fromColumn == move.toColumn) ||
										(notKingMove.fromRow == move.toRow - 1 &&
												notKingMove.fromColumn == move.toColumn) ||
										(notKingMove.fromRow == move.toRow &&
												notKingMove.fromColumn == move.toColumn + 1) ||
										(notKingMove.fromRow == move.toRow &&
												notKingMove.fromColumn == move.toColumn - 1) ||
										(notKingMove.fromRow == move.toRow + 1 &&
												notKingMove.fromColumn == move.toColumn + 1) ||
										(notKingMove.fromRow == move.toRow - 1 &&
												notKingMove.fromColumn == move.toColumn + 1) ||
										(notKingMove.fromRow == move.toRow + 1 &&
												notKingMove.fromColumn == move.toColumn - 1) ||
										(notKingMove.fromRow == move.toRow - 1 &&
												notKingMove.fromColumn == move.toColumn - 1)) {

									valid = false;

								}
							}

							if (board[r][c].type().equals("Pawn")) {

								if (((move.toRow == move.fromRow + 1 &&
										move.toColumn == move.fromColumn) ||
										(move.toRow == move.fromRow - 1 &&
												move.toColumn == move.fromColumn) ||
										(move.toRow == move.fromRow &&
												move.toColumn == move.fromColumn + 1) ||
										(move.toRow == move.fromRow &&
												move.toColumn == move.fromColumn - 1) ||
										(move.toRow == move.fromRow + 1 &&
												move.toColumn == move.fromColumn + 1) ||
										(move.toRow == move.fromRow - 1 &&
												move.toColumn == move.fromColumn + 1) ||
										(move.toRow == move.fromRow + 1 &&
												move.toColumn == move.fromColumn - 1) ||
										(move.toRow == move.fromRow - 1 &&
												move.toColumn == move.fromColumn - 1))) {

									notKingMove = new Move(r, c, move.toRow, move.toColumn);

									//when piece can move to king spot
									if (board[r][c].player() == Player.WHITE) {

										if ((notKingMove.fromRow == move.toRow + 1
												&& notKingMove.fromColumn ==
												move.toColumn + 1) ||
												(notKingMove.fromRow == move.toRow + 1
														&& notKingMove.fromColumn
														== move.toColumn - 1)) {

											valid = false;

										}
									} else {

										if ((notKingMove.fromRow == move.toRow - 1
												&& notKingMove.fromColumn ==
												move.toColumn + 1) ||
												(notKingMove.fromRow == move.toRow - 1
														&& notKingMove.fromColumn ==
														move.toColumn - 1)) {

											valid = false;

										}

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
}