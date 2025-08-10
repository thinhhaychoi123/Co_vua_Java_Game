package me.huu_thinh.chess.model.pieces;

import java.util.List;

import me.huu_thinh.chess.model.board.PiecePostion;
import me.huu_thinh.chess.model.move.Move;
import me.huu_thinh.chess.model.move.type.AttackMove;
import me.huu_thinh.chess.util.PieceInfo;
import me.huu_thinh.chess.util.PieceType;

public class Bishop extends Piece {

	private static final int[][] move_list = {{1,1},{1,-1},{-1,1},{-1,-1}};
	private static final int sizemove = move_list.length;
	
	public Bishop(int pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public PieceType getPieceType() {
		// TODO Auto-generated method stub
		return PieceType.BISHOP;
	}

	@Override
	public void getPossiableMove(PiecePostion piece,List<Move> move,boolean whiteturn,boolean onlyAttack) {
		int x = getX();
		int y = getY();
		for(int i = 0;i < sizemove;i++) { 
			int newx = x + move_list[i][0];
			int newy = y + move_list[i][1];
			while(true) {
				if(outSide(newx,newy)) break;
				PieceInfo info = piece.getPieceInfo(newx,newy);
				if(info == PieceInfo.NONE) {
					if(!onlyAttack) move.add(new Move("B",toPos(), toPos(newx,newy)));
					newx += move_list[i][0];
					newy += move_list[i][1];
				} else {
				if(!isSameColor(info,whiteturn)) {
					move.add(new AttackMove("B",toPos(), toPos(newx,newy)));
				}
					break;	
				}
			}
		}
	}

	@Override
	public int getMaterialScore(boolean lategame) {
		return lategame ? 330 : 300;
	}







	

	

	
}
