package me.huu_thinh.chess.model.pieces;

import java.util.List;

import me.huu_thinh.chess.model.board.PiecePostion;
import me.huu_thinh.chess.model.move.Move;
import me.huu_thinh.chess.model.move.type.AttackMove;
import me.huu_thinh.chess.util.PieceInfo;
import me.huu_thinh.chess.util.PieceType;

public class Knight extends Piece {
	
	private static final int[][] moves = {{2,1},{-2,1},{2,-1},{-2,-1},
										  {1,2},{1,-2},{-1,2},{-1,-2}};
	private static final int sizemove = moves.length;

	public Knight(int pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	public PieceType getPieceType() {
		return PieceType.KNIGHT;
	}
	@Override
	public void getPossiableMove(PiecePostion piece,List<Move> move,boolean whiteturn,boolean onlyAttack) {
		int x = getX();
		int y = getY();
		for(int i = 0; i < sizemove;i++) {
			int newx = x + moves[i][0];
			int newy = y + moves[i][1];
			if(outSide(newx,newy)) continue;
			PieceInfo info = piece.getPieceInfo(newx,newy);
			if(info == PieceInfo.NONE) {
				if(!onlyAttack) move.add(new Move("N",toPos(), toPos(newx,newy)));
				continue;
			}
			if(!isSameColor(info,whiteturn)) {
				move.add(new AttackMove("N",toPos(), toPos(newx,newy)));
			}
		}
	}

	@Override
	public int getMaterialScore(boolean lategame) {
		return 300;
	}
}
