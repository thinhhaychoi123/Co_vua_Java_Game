package me.huu_thinh.chess.model.pieces;

public class DeadPiece {

	private int pieceid;
	private int pos;
	
	public DeadPiece(int pieceid, int pos) {
		super();
		this.pieceid = pieceid;
		this.pos = pos;
	}
	
	public int getPieceid() {
		return pieceid;
	}
	public int getPos() {
		return pos;
	}
	
}
