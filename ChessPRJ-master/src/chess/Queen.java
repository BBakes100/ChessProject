package chess;

public class Queen extends ChessPiece {

	public Queen(Player player) {
		super(player);

	}

	public String type() {
		return "Queen";

	}

	public boolean isValidMove(Move move, IChessPiece[][] board) {

		boolean valid = false;

		if(rookValidMove(move, board) || bishopValidMove(move, board)){
			valid = true;
		}

		return valid;
	}

	public boolean rookValidMove(Move move, IChessPiece[][] board){
		boolean valid = false;

		if (super.isValidMove(move, board)) {

			//Rook moves in the same row
			if (move.fromRow == move.toRow) {

				//Rook moves right
				if(move.toColumn > move.fromColumn){

					//Check right of the rook for pieces
					for(int i = move.fromColumn + 1; i < move.toColumn;
						i++){

						//Check each column going up
						if(board[move.toRow][i] == null){
							valid = true;
						}else{
							valid = false;
							return valid;
						}
					}
					valid = true;

					//Rook moves left
				}else if(move.toColumn < move.fromColumn){

					//Check left of the rook for pieces
					for(int i = move.fromColumn - 1; i > move.toColumn;
						i--){

						//Check each column going down
						if(board[move.toRow][i] == null){
							valid = true;
						}else{
							valid = false;
							return valid;
						}
					}
					valid = true;
				}


				//rook moves in the same column
			} else if (move.fromColumn == move.toColumn) {

				//Rook moves up
				if(move.toRow > move.fromRow){

					//Check north of the rook for pieces
					for(int i = move.fromRow + 1; i < move.toRow; i++){

						//Check each row going up
						if(board[i][move.toColumn] == null){
							valid = true;
						}else{
							valid = false;
							return valid;
						}
					}
					valid = true;


					//Rook moves down
				}else {

					//Check left of the rook for pieces
					for(int i = move.fromRow - 1; i > move.toRow; i--){

						//Check each row going down
						if(board[i][move.toColumn] == null){
							valid = true;
						}else{
							valid = false;
							return valid;
						}
					}

					valid = true;
				}
			}
		} else {
			valid = false;
		}

		return valid;
	}

	public boolean bishopValidMove(Move move, IChessPiece[][] board){
		boolean valid = false;

		if (super.isValidMove(move, board)) {

			//Bishop moves diagonal up right
			if (move.toRow > move.fromRow && move.toColumn >
					move.fromColumn) {

				for (int i = 1; i <= move.toColumn - move.fromColumn;
					 i++) {

					int j = move.toColumn - move.fromColumn;

					//Check that move was diagonal up right
					if (move.toRow == move.fromRow + j && move.toColumn
							== move.fromColumn + j) {

						//Check to see if a piece is blocking diagonal
						if (board[move.fromRow + i][move.fromColumn + i]
								!= null && move.fromRow + i != move.toRow &&
								move.fromColumn + i != move.toColumn) {
							valid = false;
							break;
						} else {
							valid = true;
						}
					}
				}
			}

			//Bishop moves diagonal down right
			else if (move.toRow < move.fromRow && move.toColumn >
					move.fromColumn) {

				for (int i = 1; i <= move.toColumn - move.fromColumn;
					 i++) {

					int j = move.toColumn - move.fromColumn;

					//Check that move was diagonal down right
					if (move.toRow == move.fromRow - j && move.toColumn
							== move.fromColumn + j) {

						//Check to see if a piece is blocking diagonal
						if (board[move.fromRow - i][move.fromColumn + i]
								!= null && move.fromRow - i !=
								move.toRow && move.fromColumn + i
								!= move.toColumn) {
							valid = false;
							break;
						} else {
							valid = true;
						}
					}
				}
			}

			//Bishop moves diagonal down left
			else if (move.toRow < move.fromRow && move.toColumn <
					move.fromColumn) {

				for (int i = move.fromColumn - move.toColumn; i
						>= 1; i--) {

					int j = move.fromColumn - move.toColumn;

					//Check that move was diagonal down left
					if (move.toRow == move.fromRow - j &&
							move.toColumn == move.fromColumn - j) {

						//Check to see if a piece is blocking diagonal
						if (board[move.fromRow - i]
								[move.fromColumn - i] != null &&
								move.fromRow - i != move.toRow &&
								move.fromColumn - i != move.toColumn) {
							valid = false;
							break;
						} else {
							valid = true;
						}
					}
				}
			}

			//Bishop moves diagonal up left
			else if (move.toRow > move.fromRow && move.toColumn <
					move.fromColumn) {

				for (int i = move.fromColumn - move.toColumn; i >= 1;
					 i--) {

					int j = move.fromColumn - move.toColumn;

					//Check that move was diagonal up left
					if (move.toRow == move.fromRow + j && move.toColumn
							== move.fromColumn - j) {

						//Check to see if a piece is blocking diagonal
						if (board[move.fromRow + i][move.fromColumn - i]
								!= null && move.fromRow + i !=
								move.toRow && move.fromColumn - i
								!= move.toColumn) {
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
