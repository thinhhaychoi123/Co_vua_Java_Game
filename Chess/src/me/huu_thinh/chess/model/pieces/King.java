package me.huu_thinh.chess.model.pieces;

import java.util.ArrayList;
import java.util.List;

import me.huu_thinh.chess.model.board.PiecePostion;
import me.huu_thinh.chess.model.move.Move;
import me.huu_thinh.chess.model.move.type.AttackMove;
import me.huu_thinh.chess.model.move.type.CastlesMove;
import me.huu_thinh.chess.util.PieceInfo;
import me.huu_thinh.chess.util.PieceType;

public class King extends Piece {

	private static final int[][] move_list = {{1,1},{1,-1},{-1,1},{-1,-1},{0,1},{0,-1},{-1,0},{1,0}};
	private static final int sizemove = move_list.length;
	private boolean isfirstmove = true;
	
	public King(int pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public PieceType getPieceType() {
		// TODO Auto-generated method stub
		return PieceType.KING;
	}
	public void updateMove() {
		this.isfirstmove = false;
	}
	
	@Override
	public void getPossiableMove(PiecePostion piece,List<Move> move,boolean whiteturn,boolean onlyAttack) {
		int x = getX();
		int y = getY();
		for(int i = 0; i < sizemove;i++) {
			int newx = x + move_list[i][0];
			int newy = y + move_list[i][1];
			if(outSide(newx,newy)) continue;
			PieceInfo info = piece.getPieceInfo(newx,newy);
			if(info == PieceInfo.NONE) {
				if(!onlyAttack) move.add(new Move("K",toPos(), toPos(newx,newy)));
				continue;
			}
			if(!isSameColor(info,whiteturn)) {
				move.add(new AttackMove("K",toPos(), toPos(newx,newy)));
			}
		}
		//Nhap thanh
		if(isfirstmove && !onlyAttack) {
			//Kiem tra quan xe co the nhap thanh (neu xe chua di chuyen)
			boolean queenside = piece.containsRookCanCastle(whiteturn, true);
			boolean kingside = piece.containsRookCanCastle(whiteturn, false);
			if(!queenside && !kingside) {
				return;
			}
			List<Move> attack = new ArrayList<>();
			for(Piece p : piece.getListPiece(!whiteturn)) {
				p.getPossiableMove(piece, attack, !whiteturn, true);
			}
			//Bi chieu la khong nhap thanh duoc
			if(attack.stream().anyMatch(m -> m.getTo() == toPos())) {
				return;
			}
			//Nhap thanh ben canh hau (Nhap thanh xa)
			for(int i = 1;i < 3;i++) {
				int pos = y*8+(x - i);
				PieceInfo info = piece.getPieceInfo(pos);
				if(info != PieceInfo.NONE) {
					//Bi chan khong cho nhap thanh
					queenside = false;
					break;
				}
//				if(attack.stream().anyMatch(m -> m.getTo() == pos)) {
//					//O nay bi tan cong nen khong nhap thanh
//					queenside = false;
//					break;
//				}
			}
			//Nhap thanh ben canh vua (Nhap thanh gan)
			for(int i = 1;i < 3;i++) {
				int pos = y*8+(x + i);
				PieceInfo info = piece.getPieceInfo(pos);
				if(info != PieceInfo.NONE) {
					//Bi chan khong cho nhap thanh
					kingside = false;
					break;
				}
//				if(attack.stream().anyMatch(m -> m.getTo() == pos)) {
//					//O nay bi tan cong nen khong nhap thanh
//					kingside = false;
//					break;
//				}
			}
			//Kiem tra va cho them viec nhap thanh
			if(queenside) {
				int rook = piece.getRookCastlePos(whiteturn, true);
				move.add(new CastlesMove(toPos(), toPos(x-2,y),rook,rook+3, false));
			}
			if(kingside) {
				int rook = piece.getRookCastlePos(whiteturn, false);
				move.add(new CastlesMove(toPos(), toPos(x+2,y),rook,rook-2,true));
			}
		}
		
	}

	@Override
	public int getMaterialScore(boolean lategame) {
		return 0;
	}



	
	
}
