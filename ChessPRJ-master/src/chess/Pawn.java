package chess;

/**********************************************************************
 * Pawn piece used in a game of chess. Pawn has an owner and a set
 * of valid moves it can take.
 *
 * Class name: Pawn
 * @author Benjamin Howe and Brandon Baker
 * @version Winter 2022
 *********************************************************************/

public class Pawn extends ChessPiece {

	/*********************************************************************
	 * Constructor for pawn calls ChessPiece constructor then assigns
	 * owner to pawn. (Black or White)
	 *
	 * @param player owner of piece
	 **********************************************************************/
	public Pawn(Player player) {
		super(player);
	}

	/**********************************************************************
	 * Method that displays the type of ChessPiece, Pawn for this class.
	 *
	 * @return Pawn, name of piece
	 **********************************************************************/
	public String type() {
		return "Pawn";
	}

	/**********************************************************************
	 * Method checks to see if move is valid for ChessPiece type, "Pawn".
	 *
	 * @param move move of a piece on the board
	 * @param board current board with all the pieces on it
	 *
	 * @return whether pawn move was valid
	 **********************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {

		boolean valid = false;

		//Check super to see if general moves rules are true
		if(super.isValidMove(move, board)) {

			//White pawn
			if (this.player() == Player.WHITE) {

				//Pawn is in starting position
				if (move.fromRow == 6) {

					//Pawn moves up 1
					if((move.toRow == move.fromRow - 1) &&
							(board[move.fromRow - 1]
									[move.fromColumn] == null)
							&& (move.toColumn == move.fromColumn)) {

						valid = true;

						//Pawn can capture
					}else if(((move.toRow == move.fromRow - 1) &&
							(move.toColumn == move.fromColumn + 1)) ||
							((move.toRow == move.fromRow - 1) &&
									(move.toColumn == move.fromColumn - 1))){

						//Pawn can only capture diagonal left
						if(move.fromColumn == 0){

							if(board[move.fromRow - 1][move.fromColumn + 1]
									!= null){
								valid = true;
							}

							//Pawn can only capture diagonal right
						}else if (move.fromColumn == 7){

							if(board[move.fromRow - 1][move.fromColumn - 1]
									!= null){
								valid = true;
							}

							//Pawn can capture both diagonals
						}else {

							if(board[move.fromRow - 1][move.fromColumn + 1]
									!= null){

								if(move.toColumn == move.fromColumn + 1){

									valid = true;
								}

							}

							if((board[move.fromRow - 1][move.fromColumn - 1]
									!= null)){

								if(move.toColumn == move.fromColumn - 1){

									valid = true;
								}
							}
						}

						//Opposing piece in front of pawn
					}else if ((move.toRow == move.fromRow - 1) &&
							(board[move.fromRow - 1][move.fromColumn]
									!= null)){

						//Pawn can only capture left
						if(move.fromColumn < 7){
							if(board[move.fromRow - 1][move.fromColumn + 1]
									!= null) {

								//Check Diagonals is opposing player
								if (board[move.fromRow - 1]
										[move.fromColumn + 1].player() !=
										board[move.fromRow]
												[move.fromColumn].player()) {

									//Checks player move is diagonal
									if (move.toRow == move.fromRow - 1 &&
											move.toColumn ==
													move.fromColumn + 1) {

										valid = true;
									}
								}
							}

						}else {
							if (board[move.fromRow - 1][move.fromColumn - 1]
									!= null) {

								//Check Diagonals is opposing player
								if (board[move.fromRow - 1]
										[move.fromColumn - 1].player() !=
										board[move.fromRow]
												[move.fromColumn].player()) {

									//Checks player move is diagonal
									if (move.toRow == move.fromRow - 1 &&
											move.toColumn ==
													move.fromColumn - 1) {

										valid = true;

									}

								}
							}
						}

						//Pawn moves up 2
					} else if(move.toRow == move.fromRow - 2 &&
							(board[move.fromRow - 2]
									[move.fromColumn] == null) &&
							(move.fromColumn == move.toColumn)){

						valid = true;

						//Opposing piece is blocking movement
						if(board[move.fromRow - 1][move.fromColumn]
								!= null){

							valid = false;

						}
					}


					//Pawn is not in row 1
				}else{

					//Pawn moves up 1
					if((move.toRow == move.fromRow - 1) &&
							(board[move.fromRow - 1][move.fromColumn]
									== null)
							&& (move.toColumn == move.fromColumn)) {

						valid = true;

						//Pawn can capture
					}else if(((move.toRow == move.fromRow - 1) &&
							(move.toColumn == move.fromColumn + 1)) ||
							((move.toRow == move.fromRow - 1) &&
									(move.toColumn ==
											move.fromColumn - 1))){

						//Pawn can only capture diagonal left
						if(move.fromColumn == 0){

							if(board[move.fromRow - 1]
									[move.fromColumn + 1]
									!= null){
								valid = true;
							}

							//Pawn can only capture diagonal right
						}else if (move.fromColumn == 7){

							if(board[move.fromRow - 1]
									[move.fromColumn - 1] != null){
								valid = true;
							}

							//Pawn can capture both diagonals
						}else {

							if(board[move.fromRow - 1]
									[move.fromColumn + 1] != null){

								if(move.toColumn == move.fromColumn + 1){

									valid = true;
								}

							}

							if((board[move.fromRow - 1][move.fromColumn - 1]
									!= null)){

								if(move.toColumn == move.fromColumn - 1){

									valid = true;
								}
							}
						}

						//Opposing piece in front of pawn
					}else if ((move.toRow == move.fromRow - 1) &&
							(board[move.fromRow - 1][move.fromColumn]
									!= null)){

						//Pawn can only capture left
						if(move.fromColumn < 7){
							if(board[move.fromRow - 1]
									[move.fromColumn + 1] != null) {

								//Check Diagonals is opposing player
								if (board[move.fromRow - 1]
										[move.fromColumn + 1].player() !=
										board[move.fromRow]
												[move.fromColumn].player()){

									//Checks player move is diagonal
									if (move.toRow == move.fromRow - 1 &&
											move.toColumn ==
													move.fromColumn + 1) {

										valid = true;
									}
								}
							}
						}else {
							if (board[move.fromRow - 1]
									[move.fromColumn - 1] != null) {

								//Check Diagonals is opposing player
								if (board[move.fromRow - 1]
										[move.fromColumn - 1].player() !=
										board[move.fromRow]
												[move.fromColumn].player()) {

									//Checks player move is diagonal
									if (move.toRow == move.fromRow - 1 &&
											move.toColumn ==
													move.fromColumn - 1) {

										valid = true;

									}

								}
							}
						}
					}
				}
			}


			//Black pawn
			if (this.player() == Player.BLACK) {

				//Pawn is in starting position
				if (move.fromRow == 1) {

					//Pawn moves up 1
					if((move.toRow == move.fromRow + 1) &&
							(board[move.fromRow + 1]
									[move.fromColumn] == null) &&
							(move.toColumn == move.fromColumn)) {

						valid = true;

						//Pawn can capture
					}else if(((move.toRow == move.fromRow + 1) &&
							(move.toColumn == move.fromColumn + 1)) ||
							((move.toRow == move.fromRow + 1) &&
									(move.toColumn ==
											move.fromColumn - 1))){

						//Pawn can only capture diagonal left
						if(move.fromColumn == 0){

							if(board[move.fromRow + 1][move.fromColumn + 1]
									!= null){
								valid = true;
							}

							//Pawn can only capture diagonal right
						}else if (move.fromColumn == 7){

							if(board[move.fromRow + 1][move.fromColumn - 1]
									!= null){
								valid = true;
							}

							//Pawn can capture both diagonals
						}else {

							if(board[move.fromRow + 1][move.fromColumn + 1]
									!= null){

								if(move.toColumn == move.fromColumn + 1){

									valid = true;
								}

							}

							if((board[move.fromRow + 1][move.fromColumn - 1]
									!= null)){

								if(move.toColumn == move.fromColumn - 1){

									valid = true;
								}
							}
						}

						//Opposing piece in front of pawn
					}else if ((move.toRow == move.fromRow + 1) &&
							(board[move.fromRow + 1][move.fromColumn]
									!= null)){

						if(move.fromColumn < 7){

							if(board[move.fromRow + 1][move.fromColumn + 1]
									!= null) {

								//Check Diagonals is opposing player
								if (board[move.fromRow + 1]
										[move.fromColumn + 1].player() !=
										board[move.fromRow]
												[move.fromColumn].player()) {

									//Checks player move is diagonal
									if (move.toRow == move.fromRow + 1 &&
											move.toColumn ==
													move.fromColumn + 1) {

										valid = true;
									}
								}
							}
						}else {
							if (board[move.fromRow + 1][move.fromColumn - 1]
									!= null) {

								//Check Diagonals is opposing player
								if (board[move.fromRow + 1]
										[move.fromColumn - 1].player() !=
										board[move.fromRow]
												[move.fromColumn].player()) {

									//Checks player move is diagonal
									if (move.toRow == move.fromRow + 1 &&
											move.toColumn ==
													move.fromColumn - 1) {

										valid = true;
									}
								}
							}
						}

						//Pawn moves up 2
					} else if(move.toRow == move.fromRow + 2 &&
							(board[move.fromRow + 2][move.fromColumn]
									== null) &&
							(move.fromColumn == move.toColumn)){

						valid = true;

						//Opposing piece is blocking movement
						if(board[move.fromRow + 1][move.fromColumn]
								!= null){

							valid = false;

						}
					}

					//Pawn is not in row 7
				}else{

					//Pawn moves up 1
					if((move.toRow == move.fromRow + 1) &&
							(board[move.fromRow + 1][move.fromColumn]
									== null)
							&& (move.toColumn == move.fromColumn)) {

						valid = true;

						//Pawn can capture
					}else if(((move.toRow == move.fromRow + 1) &&
							(move.toColumn == move.fromColumn + 1)) ||
							((move.toRow == move.fromRow + 1) &&
									(move.toColumn ==
											move.fromColumn - 1))){

						//Pawn can only capture diagonal left
						if(move.fromColumn == 0){

							if(board[move.fromRow + 1][move.fromColumn + 1]
									!= null){
								valid = true;
							}

							//Pawn can only capture diagonal right
						}else if (move.fromColumn == 7){

							if(board[move.fromRow + 1][move.fromColumn - 1]
									!= null){
								valid = true;
							}

							//Pawn can capture both diagonals
						}else {

							if(board[move.fromRow + 1][move.fromColumn + 1]
									!= null){

								if(move.toColumn == move.fromColumn + 1){

									valid = true;
								}

							}

							if((board[move.fromRow + 1][move.fromColumn - 1]
									!= null)){

								if(move.toColumn == move.fromColumn - 1){

									valid = true;
								}
							}
						}

						//Opposing piece in front of pawn
					}else if ((move.toRow == move.fromRow + 1) &&
							(board[move.fromRow + 1][move.fromColumn]
									!= null)){

						if(move.fromColumn < 7){

							if(board[move.fromRow + 1][move.fromColumn + 1]
									!= null) {

								//Check Diagonals is opposing player
								if (board[move.fromRow + 1]
										[move.fromColumn + 1].player() !=
										board[move.fromRow]
												[move.fromColumn].player()) {

									//Checks player move is diagonal
									if (move.toRow == move.fromRow + 1 &&
											move.toColumn ==
													move.fromColumn + 1) {

										valid = true;
									}
								}
							}
						}else {
							if (board[move.fromRow + 1][move.fromColumn - 1]
									!= null) {

								//Check Diagonals is opposing player
								if (board[move.fromRow + 1]
										[move.fromColumn - 1].player() !=
										board[move.fromRow]
												[move.fromColumn].player()) {

									//Checks player move is diagonal
									if (move.toRow == move.fromRow + 1 &&
											move.toColumn ==
													move.fromColumn - 1) {

										valid = true;
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
