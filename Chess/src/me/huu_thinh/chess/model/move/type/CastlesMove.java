package me.huu_thinh.chess.model.move.type;

import me.huu_thinh.chess.model.move.Move;

public class CastlesMove extends Move {

	private int rookfrom;
	private int rookto;
	private boolean kingside;
	public CastlesMove(int from, int to, int rookfrom, int rookto,boolean kingside) {
		super("",from, to);
		this.rookfrom = rookfrom;
		this.rookto = rookto;
		this.kingside = kingside;
	}
	public int getRookFrom() {
		return rookfrom;
	}
	public int getRookTo() {
		return rookto;
	}
	public String nameMove() {
		String name = kingside ? "O-O" : "O-O-O";
		if(checkmove == 1) name+= "+";
		else if(checkmove == 2) name+="#";
		return name;
	}


	
	
}
