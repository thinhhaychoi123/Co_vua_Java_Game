package me.huu_thinh.chess.model.pieces;

import java.util.List;

import me.huu_thinh.chess.model.board.PiecePostion;
import me.huu_thinh.chess.model.move.Move;
import me.huu_thinh.chess.model.move.type.AttackMove;
import me.huu_thinh.chess.model.move.type.EnPassantMove;
import me.huu_thinh.chess.model.move.type.PawnFirstTwoMove;
import me.huu_thinh.chess.model.move.type.PromoteMove;
import me.huu_thinh.chess.util.PieceInfo;
import me.huu_thinh.chess.util.PieceType;

public class Pawn extends Piece {
	
	public Pawn(int pos) {
		super(pos);
	}

	@Override
	public PieceType getPieceType() {
		// TODO Auto-generated method stub
		return PieceType.PAWN;
	}


	@Override
	public void getPossiableMove(PiecePostion piece,List<Move> move,boolean whiteturn,boolean onlyAttack) {
		int x = getX();
		int y = getY();
		int m = whiteturn ? -1 : 1; 
		int start = whiteturn ? 6 : 1; 
		for(int i = -1;i < 2;i++) {
			Move movenext = null;
			if(i == 0) {
				if(onlyAttack) continue;
				int newy = y + m;
				if(isOut(newy)) continue;
				if(piece.getPieceInfo(x, newy) != PieceInfo.NONE) continue;
				movenext = new Move("",toPos(),toPos(x,newy));
				if(y == start) {
					newy += m;
					if(!isOut(newy) && piece.getPieceInfo(x, newy) == PieceInfo.NONE) {
					move.add(new PawnFirstTwoMove("",toPos(),toPos(x,newy)));
					}
				}
			} else {
				int newx = x + i;
				int newy = y + m;
				if(outSide(newx,newy)) continue;
				PieceInfo info = piece.getPieceInfo(newx, newy);
				if(info != PieceInfo.NONE) {
					if(!isSameColor(info, whiteturn))
						movenext = new AttackMove("",toPos(),toPos(newx,newy));
				} else {
					//Bat tot qua duong
					if(piece.containEnPassant(newx,y))
						movenext = new EnPassantMove("",toPos(),toPos(newx,newy),toPos(newx,y));
				}
			}
			if(movenext != null) {
				int to = movenext.getTo();
				boolean c = whiteturn ? to <= 7 : to >= 56;
				if(c) {
					for(int type = 0;type < 4;type++) {
						move.add(new PromoteMove(movenext,type));
					}
				} else {
					move.add(movenext);
				}
			}
		}
	}

	public boolean isOut(int x) {
		return x < 0 || x >= 8;
	}

	@Override
	public int getMaterialScore(boolean lategame) {
		// TODO Auto-generated method stub
		return 100;
	}




	

}
