package me.huu_thinh.chess.model.pieces;

import java.util.List;

import me.huu_thinh.chess.model.board.PiecePostion;
import me.huu_thinh.chess.model.move.Move;
import me.huu_thinh.chess.util.PieceInfo;
import me.huu_thinh.chess.util.PieceType;

public abstract class Piece implements Cloneable {

	private int x;
	private int y;
	
	public Piece(int pos){	
		updateLocation(pos);
	}
	public int getX() {
		return getX(false);
	}
	public int getY() {
		return getY(false);
	}
	
	public int getX(boolean flip) {
		return flip ? 7 - this.x : this.x;
	}
	public int getY(boolean flip) {
		return flip ? 7 - this.y : this.y;
	}
	
	
	public abstract PieceType getPieceType();
	
	public void updateMove() {}
	
	public abstract void getPossiableMove(PiecePostion piece,List<Move> moves,boolean whiteturn,boolean onlyAttack);
	
	public abstract int getMaterialScore(boolean lategame);
	
	public void getPossiableMove(PiecePostion piece,List<Move> moves,boolean whiteturn) {
		getPossiableMove(piece, moves, whiteturn,false);
	}
	
	public boolean outSide(int pos) {
		return pos < 0 || pos >= 64;
	}
	public boolean outSide(int x,int y) {
		return x < 0 || x >= 8 || y < 0 || y >= 8;
	}
	
	

	public void updateLocation(int pos) {
		this.x = pos % 8;
		this.y = Math.floorDiv(pos, 8);
	}

	public Piece getClone() {
		try {
			return (Piece) this.clone();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	public boolean isSamePos(int pos) {
		return isSamePos(pos % 8,Math.floorDiv(pos, 8));
	}
	private boolean isSamePos(int x, int y) {
		// TODO Auto-generated method stub
		return x == this.x && y == this.y;
	}
	public int toPos() {
		return toPos(this.x,this.y);
	}
	protected int toPos(int x,int y) {
		return y*8+x;
	}
	
	protected boolean isSameColor(final PieceInfo info,final boolean white) {
		if(white && info == PieceInfo.WHITE) {
			return true;
		}
		if(!white && info == PieceInfo.BLACK) {
			return true;
		}
		return false;
	} 

	
}
