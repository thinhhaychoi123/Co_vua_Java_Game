package me.huu_thinh.chess.model;

import javax.swing.JOptionPane;

import me.huu_thinh.chess.model.board.Board;
import me.huu_thinh.chess.util.GameState;
import me.huu_thinh.chess.view.Constants;

public class Game {

	private Board board;
	
	private GameState state = GameState.STOPING;
	
	private boolean drag = false;
	
	private int selectPiece = -1;

	private boolean ismoving = false;

	
	
	
	
	public Game() {
		this.board = new Board();
		reset();
	}
	
	public void reset() {
		board.resetAllPieces();
		board.updateCurrentMoving();
		changeSide();
	}

	
	public void changeSide() {
		changeSide(false);
	}

	public void changeSide(boolean isundo) {
		this.drag = false;
		checkWinGame();
		checkIsOver();
	}
	
	private void checkIsOver() {
		if(state == GameState.BLACK_WIN) {
			JOptionPane.showMessageDialog(null, "Bên đen đã chiến thắng, gg !");
			return;
		} else if(state == GameState.WHITE_WIN) {
			JOptionPane.showMessageDialog(null, "Bên trắng đã chiến thắng, gg !");
			return;
		} else if(state == GameState.DRAW) {
			JOptionPane.showMessageDialog(null, "Hòa cờ mất rồi !");
			return;
		}
	}

	private void checkWinGame() {
		if(this.state != GameState.PLAYING) {
			changeState(GameState.PLAYING);
		}
		if(board.getPiecePostion().isKingInCheck(true) && board.isWhiteTurn()) {
			if(board.isNullMove()) {
				changeState(GameState.BLACK_WIN);
				return;
			}
		} else if(board.getPiecePostion().isKingInCheck(false) && !board.isWhiteTurn()) {
			if(board.isNullMove()) {
				changeState(GameState.WHITE_WIN);
				return;
			}
		} else {
			if(board.isNullMove()) {
				changeState(GameState.DRAW);
				return;
			}
		}
	}

	private void changeState(GameState state) {
		this.state = state;
	}

	public Board getBoard() {
		return this.board;
	}
	
	public int getSelectPiece() {
		return this.selectPiece;
	}
	public void setSelectPiece(int piece) {
		this.selectPiece = piece;
	}
	public void setUnDrag(int x,int y) {
		this.ismoving = false;
		int xp = Math.floorDiv(x, Constants.getWidthSquare());
		int yp = Math.floorDiv(y, Constants.getHeightSquare());
//		int to = board.getPiecePostion().getPieceID(yp*8+xp);//Nay la lay quan co, ngu vcl
		int posto = yp*8+xp;
		if(xp >= 0 && xp < 8 && yp >= 0 && yp < 8) {
		if(selectPiece >= 0) { 
			if(selectPiece == posto) {
				setSelectPiece(-1);
				drag = false;
				return;
			} else {
				if(!board.canMove(selectPiece,posto)) {
					setSelectPiece(-1);
					drag = false;
					return;
				} else {
					board.movePiece(selectPiece, posto);
					setSelectPiece(-1);
					this.ismoving = true;
				}
			}
		}
		}
	}
	public void setSelectPiece(int x,int y) {
		int xp = Math.floorDiv(x, Constants.getWidthSquare());
		int yp = Math.floorDiv(y, Constants.getHeightSquare());
		int from = board.getPiecePostion().getPieceID(yp*8+xp);
		if(from > 0) {
			if((from >= 6) != board.isWhiteTurn()) return;
			this.drag = false;
			setSelectPiece(yp*8+xp);
		}
	}
	public void setDrag(int x,int y) {
		int xp = Math.floorDiv(x, Constants.getWidthSquare());
		int yp = Math.floorDiv(y, Constants.getHeightSquare());
//		xp = GameSettings.canFlipBoard() ? (7 - xp) : xp;
//		yp = GameSettings.canFlipBoard() ? (7 - yp) : yp;
		int from = board.getPiecePostion().getPieceID(yp*8+xp);
		if(from >= 0) {
//			if((from >= 6) != this.isWhiteTurn) return;
			setSelectPiece(yp*8+xp);
			this.drag = true;
		}
	}

	public boolean isDrag() {
		// TODO Auto-generated method stub
		return this.drag;
	}

	public boolean hasMoving() {
		return this.ismoving;
	}
	
	
}
