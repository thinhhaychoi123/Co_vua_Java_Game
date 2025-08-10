package me.huu_thinh.chess.model.move;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.huu_thinh.chess.model.board.PiecePostion;
import me.huu_thinh.chess.model.pieces.Piece;

public class MoveGenetator {

	
	private PiecePostion pos;
	private boolean whitemove;
	private List<Move> nextmove = new ArrayList<Move>(); 
	
	public MoveGenetator(PiecePostion pos) {
		this(pos,pos.isWhiteTurn(),null);
	}
	
	public MoveGenetator(PiecePostion pos,boolean whitemove) {
		this(pos,whitemove,null);
	}
	public MoveGenetator(PiecePostion pos,boolean whitemove,final Move move) {
		if(move != null) {
			this.pos = new PiecePostion(pos,move);
		} else {
			this.pos = new PiecePostion(pos);
		}
		this.whitemove = whitemove;
	}
	public PiecePostion getPiecePostion() {
		return this.pos;
	}
	 public Move[] getNextMove(){                
	        return nextmove.toArray(new Move[nextmove.size()]);
	    }
	 public List<Move> getNextMoveList(){                
	        return nextmove;
	    }
	public void onCreateMove() {
		//Ben trang di chuyen
		  if(whitemove){
			  
			  for(Piece p : pos.whitePiece) { //Kiem tra cac quan trang
				  p.getPossiableMove(pos,nextmove, true);//Mo ra nhung nuoc di co the di
			  }
			  Iterator<Move> i = nextmove.iterator();
			  while(i.hasNext()) {
				  Move move = i.next();
				  PiecePostion newpos = new PiecePostion(pos,move);
				  if(newpos.isKingInCheck(true)) { //Neu nuoc di ma khien vua co the bi tan cong, se ghim quan khong cho di chuyen, tru khi vua thao ghim
					 i.remove();
				  }
			  }
		  } else {
			  //Danh cho ben den, tuong tu nhu ben trang
			  for(Piece p : pos.blackPiece) { 
				  p.getPossiableMove(pos,nextmove, false);
			  }
			  Iterator<Move> i = nextmove.iterator();
			  while(i.hasNext()) {
				  Move move = i.next();
				  PiecePostion newpos = new PiecePostion(pos,move);
				  if(newpos.isKingInCheck(false)) {
					 i.remove();
				  }
			  }
			  
		  }
	}

}
