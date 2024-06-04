package chess;

public class Rook extends ChessPiece {

	public Rook(Player player) {
		super(player);
	}

	public String type() {
		return "Rook";
	}

	// determines if the move is valid for a rook piece
	//need a check for if there is a piece in the way
	public boolean isValidMove(Move move, IChessPiece[][] board) {

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


}

