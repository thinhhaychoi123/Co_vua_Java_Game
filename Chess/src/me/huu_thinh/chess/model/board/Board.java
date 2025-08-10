package me.huu_thinh.chess.model.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import me.huu_thinh.chess.model.move.Move;
import me.huu_thinh.chess.model.move.MoveGenetator;
import me.huu_thinh.chess.view.dialog.PromoteDialog;

public class Board {
	
	private PiecePostion pos;
	private List<Move> nextmove = new ArrayList<Move>();
	private boolean black_is_checking = false;
	private boolean white_is_checking = false;

	private Stack<Move> lastmove = new Stack<Move>();
	
	
	
	
	public Board() {
		this.pos = new PiecePostion();
	}


	public PiecePostion getPiecePostion() {
		return this.pos;
	}


	public void resetAllPieces() {
		pos.reset();
		this.lastmove.clear();
	}
	
	public String printCurrentMove() {
		String s = "";
		if(lastmove.isEmpty()) { s += "1.";}
		for(int i = 0;i < lastmove.size();i++) {
			Move m = lastmove.get(i);
			
			if(i % 2 == 0) {
				s += (((i+2)/2)+"."+(m.isClearMove() ? m.nameClearMove() : m.nameMove()));
			} else {
				s += "\t"+(m.isClearMove() ? m.nameClearMove() : m.nameMove())+" \n"; 
			}
		}
		return s;
	}
	public String getPgnMove() {
		String s = "";
		for(int i = 0;i < lastmove.size();i++) {
			Move m = lastmove.get(i);
			if(i % 2 == 0) {
				s += (((i+2)/2)+". "+(m.isClearMove() ? m.nameClearMove() : m.nameMove()));
			} else {
				s += " "+(m.isClearMove() ? m.nameClearMove() : m.nameMove())+" "; 
			}
		}
		return s;
	}

	private void updateCheckMate() {
		black_is_checking = pos.isKingInCheck(false);
		white_is_checking = pos.isKingInCheck(true);
	}

	private void updateNextMove() {
		nextmove.clear();
		MoveGenetator mg = new MoveGenetator(pos, pos.isWhiteTurn());
		mg.onCreateMove();
		mg.getNextMoveList().forEach(m -> nextmove.add(m));
	}

	public void updateMove(final Move next) {
		lastmove.add(next);//Thêm nước hiện tại
		if(nextmove.stream().filter(check -> check.nameMove().equals(next.nameMove())).count() > 1) {
			next.setClearMove(true);//Nếu có 2 quân có thể cùng di chuyển tới 1 khu vực, làm rõ ra quân nào bên nào đã di chuyển
		}
		pos.onUpdateMove(next); //Cập nhật nước đi từ quân đã chọn
		updateCurrentMoving(); //Cập nhật các nước đi kế và khả năng chiếu lên vua
		if(isKingCheck()) {
			next.updateCheckMove(1);//Cập nhật giá trị nước đi đang chiếu vua kia
			if(isNullMove()) {
				next.updateCheckMove(2);//Nếu bên kia không di chuyển được và bị chiếu, nghĩa là vua bị chiếu bí
			}
		}
	}


	public boolean isNullMove() {
		return nextmove.isEmpty();
	}


	public Move[] fillMove(int choose) {
		return nextmove.stream().filter(m -> m.getFrom()== choose).toArray(Move[]::new);
	}


	public boolean canMove(int from, int to) {
		for(Move move : nextmove) {
			if(move.getFrom() == from && move.getTo() == to) {
				return true;
			}
		}
		return false;
	}


	public void movePiece(int from, int to) {
		List<Move> list = nextmove.stream().filter(m -> m.isSame(from, to)).collect(Collectors.toList());
		Move move = list.get(0);
		if(list.size() == 4) {
			move = list.get(PromoteDialog.showMessageForPromotion());
		}
		updateMove(move);
	}


	public boolean isKingCheck(boolean white) {
		return white ? this.white_is_checking : this.black_is_checking;
	}
	public boolean isKingCheck() {
		return this.white_is_checking || this.black_is_checking;
	}


	public Move getLastMove() {
		if(lastmove.isEmpty()) return null;
		return lastmove.peek();
	}

	public boolean isWhiteTurn() {
		return pos.isWhiteTurn();
	}

	public void undoMove() {
		//Để làm sau
	}


	public void updateCurrentMoving() {
		updateCheckMate(); //Cập nhật khả năng chiếu lên vua
		updateNextMove(); //Cập nhật các nước đi kế cho bên quân đội kia
	}
	

}
