package me.huu_thinh.chess.model.move.type;

import me.huu_thinh.chess.model.move.Move;

public class EnPassantMove extends Move {

	private int target;
	
	public EnPassantMove(String p,int from, int to, int target) {
		super(p, from,to);
		this.target = target;
	}
	
	public int getTarget() {
		return this.target;
	}
	public String nameMove() {
		int filp = to;
		int x = filp % 8;
		int y = Math.floorDiv(filp, 8);
		String name = p+"x"+File[x] + Rank[y];
		if(checkmove == 1) name+= "+";
		else if(checkmove == 2) name+="#";
		return name;
	}
}
