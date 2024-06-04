package chess;

public abstract class ChessPiece implements IChessPiece {

	private Player owner;

	protected ChessPiece(Player player) {
		this.owner = player;
	}

	public abstract String type();

	public Player player() {
		return owner;
	}

	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean valid = true;

		//Check that move is in boundaries
		if(move.toRow > 8 || move.toRow < 0 || move.toColumn > 8 || move.toColumn < 0 ||
				move.fromRow > 8 || move.fromRow < 0 || move.fromColumn > 8 || move.fromColumn < 0){
			valid = false;
			throw new IndexOutOfBoundsException();
		}

		//Check that piece has moved
		if ((move.fromRow == move.toRow) && (move.fromColumn == move.toColumn)) {
			valid = false;
		}

		//Verify that this piece is located at [move.fromRow, move.fromColumn] on the board
		if (this != board[move.fromRow][move.fromColumn]){
			valid = false;
		}

		//Check that the board at location [move.toRow, move.toColumn] does not contain a piece belonging to the same player
		if (board[move.toRow][move.toColumn] != null) {
			if(this.player() == board[move.toRow][move.toColumn].player()) {
				valid = false;
			}
		}

		return valid;
	}
}


