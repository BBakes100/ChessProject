package chess;

public class Knight extends ChessPiece {

	public Knight(Player player) {
		super(player);
	}

	public String type() {
		return "Knight";
	}

	public boolean isValidMove(Move move, IChessPiece[][] board){
		boolean valid = true;

		if(super.isValidMove(move, board)) {

			if (move.toRow == move.fromRow + 1 &&
					move.toColumn == move.fromColumn + 2 ||
					move.toRow == move.fromRow - 1 &&
							move.toColumn == move.fromColumn + 2 ||
					move.toRow == move.fromRow + 1 &&
							move.toColumn == move.fromColumn - 2 ||
					move.toRow == move.fromRow - 1 &&
							move.toColumn == move.fromColumn - 2 ||
					move.toRow == move.fromRow + 2 &&
							move.toColumn == move.fromColumn + 1 ||
					move.toRow == move.fromRow + 2 &&
							move.toColumn == move.fromColumn - 1 ||
					move.toRow == move.fromRow - 2 &&
							move.toColumn == move.fromColumn + 1 ||
					move.toRow == move.fromRow - 2 &&
							move.toColumn == move.fromColumn - 1) {

				valid = true;

			} else {

				valid = false;

			}
		} else {

			valid = false;

		}
		return valid;
	}

}
