package me.huu_thinh.chess.model.move.type;

import me.huu_thinh.chess.model.move.Move;

public class AttackMove extends Move {

	public AttackMove(String p,int from, int to) {
		super(p, from, to);
	}
	public String nameMove() {
		int filp = to;
		int x = filp % 8;
		int y = Math.floorDiv(filp, 8);
		if(p.equals("")) {
			int x1 = (from) % 8;
			String name = File[x1]+"x"+File[x] + Rank[y];
			if(checkmove == 1) name+= "+";
			else if(checkmove == 2) name+="#";
			return name;
		}
		String name = p+"x"+File[x] + Rank[y];
		if(checkmove == 1) name+= "+";
		else if(checkmove == 2) name+="#";
		return name;
	}
	
	public String nameClearMove() {
		int filpfrom = from;
		int filp = to;
		int xf = filpfrom % 8;
		int yf = Math.floorDiv(filpfrom, 8);
		int x = filp % 8;
		int y = Math.floorDiv(filp, 8);
		if(p.equals("")) {
			int x1 = (from) % 8;
			String name = File[x1]+"x"+File[x] + Rank[y];
			if(checkmove == 1) name+= "+";
			else if(checkmove == 2) name+="#";
			return name;
		}
		String clear = "";
		if(xf != x) {
			clear += File[xf];
		} else
		if(yf != y) {
			clear+= Rank[yf];
		}
		String name = p+clear+"x"+File[x] + Rank[y];
		if(checkmove == 1) name+= "+";
		else if(checkmove == 2) name+="#";
		return name;
	}
}
