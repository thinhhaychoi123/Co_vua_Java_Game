package me.huu_thinh.chess.model.move;

public class Move {

	
	public static final String[] Rank = {"8","7","6","5","4","3","2","1"};
	public static final String[] File = {"a","b","c","d","e","f","g","h"};
	
	public String p = "";
	protected int from;
	protected int to;
	private boolean isClearMove;
	protected int checkmove;
	
	public Move(String p,int from,int to) {
		this.p = p;
		this.from = from;
		this.to = to;
		this.isClearMove = false;
		this.checkmove = 0;
	}
	public String getPieceName() {
		return this.p;
	}
	public int getFrom() {
		return this.from;
	}

	public int getTo() {
		return this.to;
	}
	
	public boolean isSame(int from,int to) {
		return this.from == from && this.to == to;
	}
	

	@Override
	public String toString() {
		return "Move [from=" + from + ", to=" + to + "]";
	}
	public String nameMove() {
		int filp = to;
		int x = filp % 8;
		int y = Math.floorDiv(filp, 8);
		String name = p+ File[x]+Rank[y];
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
		
		String clear = "";
		if(xf != x) {
			clear += File[xf];
		} else
		if(yf != y) {
			clear+= Rank[yf];
		}
		String name = p+clear+ File[x]+Rank[y];
		if(checkmove == 1) name+= "+";
		else if(checkmove == 2) name+="#";
		return name;
	}
	public boolean isClearMove() {
		return this.isClearMove;
	}
	public void setClearMove(boolean b) {
		this.isClearMove = b;
	}
	public void updateCheckMove(int value) {
		this.checkmove = value;
	}
}
