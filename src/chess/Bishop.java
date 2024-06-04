package chess;

public class Bishop extends ChessPiece {

	public Bishop(Player player) {
		super(player);
	}

	public String type() {
		return "Bishop";
	}

	public boolean isValidMove(Move move, IChessPiece[][] board) {

		boolean valid = false;

		if (super.isValidMove(move, board)) {

			//Bishop moves diagonal up right
			if (move.toRow > move.fromRow && move.toColumn > move.fromColumn) {

				for (int i = 1; i <= move.toColumn - move.fromColumn; i++) {

					int j = move.toColumn - move.fromColumn;

					//Check that move was diagonal up right
					if (move.toRow == move.fromRow + j && move.toColumn == move.fromColumn + j) {

						//Check to see if a piece is blocking diagonal
						if (board[move.fromRow + i][move.fromColumn + i] != null && move.fromRow + i != move.toRow && move.fromColumn + i != move.toColumn) {
							valid = false;
							break;
						} else {
							valid = true;
						}
					}
				}
			}

			//Bishop moves diagonal down right
			else if (move.toRow < move.fromRow && move.toColumn > move.fromColumn) {

				for (int i = 1; i <= move.toColumn - move.fromColumn; i++) {

					int j = move.toColumn - move.fromColumn;

					//Check that move was diagonal down right
					if (move.toRow == move.fromRow - j && move.toColumn == move.fromColumn + j) {

						//Check to see if a piece is blocking diagonal
						if (board[move.fromRow - i][move.fromColumn + i] != null && move.fromRow - i != move.toRow && move.fromColumn + i != move.toColumn) {
							valid = false;
							break;
						} else {
							valid = true;
						}
					}
				}
			}

			//Bishop moves diagonal down left
			else if (move.toRow < move.fromRow && move.toColumn < move.fromColumn) {

				for (int i = move.fromColumn - move.toColumn; i >= 1; i--) {

					int j = move.fromColumn - move.toColumn;

					//Check that move was diagonal down left
					if (move.toRow == move.fromRow - j && move.toColumn == move.fromColumn - j) {

						//Check to see if a piece is blocking diagonal
						if (board[move.fromRow - i][move.fromColumn - i] != null && move.fromRow - i != move.toRow && move.fromColumn - i != move.toColumn) {
							valid = false;
							break;
						} else {
							valid = true;
						}
					}
				}
			}

			//Bishop moves diagonal up left
			else if (move.toRow > move.fromRow && move.toColumn < move.fromColumn) {

				for (int i = move.fromColumn - move.toColumn; i >= 1; i--) {

					int j = move.fromColumn - move.toColumn;

					//Check that move was diagonal up left
					if (move.toRow == move.fromRow + j && move.toColumn == move.fromColumn - j) {

						//Check to see if a piece is blocking diagonal
						if (board[move.fromRow + i][move.fromColumn - i] != null && move.fromRow + i != move.toRow && move.fromColumn - i != move.toColumn) {
							valid = false;
							break;
						} else {
							valid = true;
						}
					}
				}
			}
		}
		return valid;
	}
}
