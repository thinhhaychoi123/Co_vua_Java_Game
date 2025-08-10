package me.huu_thinh.chess.model.move.type;

import me.huu_thinh.chess.model.move.Move;

public class PromoteMove extends Move {
	
	private int type;

	public PromoteMove(Move move,int type) {
		this(move.getFrom(),move.getTo(),type);
	}
	
	public PromoteMove(int from, int to,int type) {
		super("",from, to);
		this.type = type;
	}

	public int getType() {
		return type;
	}
	private String toCurrentType() {
		switch(type) {
			case 0:
				return "Q";
			case 1: 
				return "R";
			case 2:
				return "B";
			case 3:
				return "K";
			default:
				return "";
		}
	}
	
	public String nameMove() {
		int filp = to;
		int x = filp % 8;
		int y = Math.floorDiv(filp, 8);
		
		String name = File[x] + Rank[y]+"="+toCurrentType();
		if(checkmove == 1) name+= "+";
		else if(checkmove == 2) name+="#";
		return name;
	}
	public String nameClearMove() {
		int filp = to;
		int x = filp % 8;
		int y = Math.floorDiv(filp, 8);
		
		String name = File[x] + Rank[y]+"="+toCurrentType();
		if(checkmove == 1) name+= "+";
		else if(checkmove == 2) name+="#";
		return name;
	}
}
