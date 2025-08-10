package me.huu_thinh.chess.util;

public enum PieceType {
	BISHOP(0),KING(1),KNIGHT(2),PAWN(3),QUEEN(4),ROOK(5);
	
	
	private int id;

	private PieceType(int id) {
		this.id = id;
	}

	public int getID() {
		return this.id;
	}

	public static PieceType fromValue(int i) {
		for(PieceType type : values()) {
			if(type.id == i) return type;
		}
		return null;
	}
}
