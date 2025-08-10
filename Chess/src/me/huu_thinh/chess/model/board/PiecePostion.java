package me.huu_thinh.chess.model.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.huu_thinh.chess.model.move.Move;
import me.huu_thinh.chess.model.move.type.CastlesMove;
import me.huu_thinh.chess.model.move.type.EnPassantMove;
import me.huu_thinh.chess.model.move.type.PawnFirstTwoMove;
import me.huu_thinh.chess.model.move.type.PromoteMove;
import me.huu_thinh.chess.model.pieces.Bishop;
import me.huu_thinh.chess.model.pieces.King;
import me.huu_thinh.chess.model.pieces.Knight;
import me.huu_thinh.chess.model.pieces.Pawn;
import me.huu_thinh.chess.model.pieces.Piece;
import me.huu_thinh.chess.model.pieces.Queen;
import me.huu_thinh.chess.model.pieces.Rook;
import me.huu_thinh.chess.util.PieceInfo;
import me.huu_thinh.chess.util.PieceType;

public class PiecePostion {
	
	public final String pospiece = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
	public final String pospiece2 = "8888k888/8/8/8/8/8/8/8888K888";
	
	private int[] pos = new int[64];
	public List<Piece> whitePiece = new ArrayList<>();
	public List<Piece> blackPiece = new ArrayList<>();
	public King whiteking;
	public King blackking;
	public int turn = 1;
	private List<Integer> en_passant = new ArrayList<>();
	private List<Integer> rook_can_castle = new ArrayList<>();

	public PiecePostion() {
		Arrays.fill(pos, -1);
	}
	
	public PiecePostion(final PiecePostion other) {
		this(other,null);
	}
	public PiecePostion(final PiecePostion other,Move move) {
		if(other != null) {
			System.arraycopy(other.pos, 0, pos, 0, pos.length);
			for(Piece p : other.whitePiece){
				this.whitePiece.add(p.getClone());
			}
			for(Piece p : other.blackPiece){
				this.blackPiece.add(p.getClone());
			}
			for(int i : other.en_passant) {
				this.en_passant.add(i);
			}
			for(int aaa : other.rook_can_castle) {
				this.rook_can_castle.add(aaa);
			}
			this.turn = other.turn;
			loadKing();
		}
		if(move != null) {
			onUpdateMove(move);
		}
	}
	
	private void loadKing() {
		for(Piece p : this.whitePiece) {
			if(p.getPieceType() == PieceType.KING) {
				this.whiteking = (King) p;
				break;
			}
		}
		for(Piece p : this.blackPiece) {
			if(p.getPieceType() == PieceType.KING) {
				this.blackking = (King) p;
				break;
			}
		}
	}
	public int getPieceID(int pos) {
		if(pos < 0 || pos >= 64) return -1;
		return this.pos[pos];
	}
	public int getPieceID(int x,int y) {
		return getPieceID(y*8+x);
	}
	public PieceInfo getPieceFromPos(int x,int y) {
		return getPieceFromPos(y*8+x);
	}
	public PieceInfo getPieceFromPos(int from) {
		int c = pos[from];
		if(c < 0) return PieceInfo.NONE; 
		if(c >= 6) return PieceInfo.WHITE; 
		return PieceInfo.BLACK;
	}

	public void reset() {
		loadPiece(pospiece);
	}

	public void loadPiece(String s) {
		Arrays.fill(pos, -1);
		this.whitePiece.clear();
		this.blackPiece.clear();
		int x = 0;
		int y = 0;
		char[] c = s.toCharArray();
		for(int i = 0;i < c.length;i++) {
			char next = c[i];
			if(next == '/') {
				y++;
				x = 0;
				continue;
			}
			Piece p = createPiece(next,x,y);
			if(p != null) {
				addPiece(p,Character.isUpperCase(next));
			}
			x++;
		}
		this.turn = 1;
		loadKing();
		removeEnPassants();
		createRookCastle();
	}

	private void addPiece(Piece p,boolean white) {
		if(white) {
			this.whitePiece.add(p);
			pos[p.toPos()] = p.getPieceType().getID() + 6;
		} else {
			this.blackPiece.add(p);
			pos[p.toPos()] = p.getPieceType().getID();
		}
	}

	private Piece createPiece(char next, int x, int y) {
		int pos = y * 8 + x;
		switch(Character.toLowerCase(next)) {
		case 'k':
			return new King(pos);
		case 'p':
			return new Pawn(pos);
		case 'n':
			return new Knight(pos);
		case 'b':	
			return new Bishop(pos);
		case 'r':
			return new Rook(pos);
		case 'q':
			return new Queen(pos);
		default: 
			return null;
		
		}
		
	}
	
	public void movePiece(int from, int to) {
		int get1 = this.pos[from];
		int get = this.pos[to];
		this.pos[to] = get1;
		this.pos[from] = get;
	}
	

	public King getKing(boolean white) {
		return white ? this.whiteking : this.blackking;
	}
	public int getRookCastlePos(boolean white, boolean left) {
		return white ? (left ? 56 : 63) : (left ? 0 : 7);
	}
	public boolean containsRookCanCastle(boolean white, boolean left) {
		int targetr = getRookCastlePos(white,left);
		return this.rook_can_castle.contains(targetr);
	}
	private void removeEnPassants() {
		this.en_passant.clear();
	}

	private void createRookCastle() {
		rook_can_castle.clear();
		rook_can_castle.add(0);
		rook_can_castle.add(7);
		rook_can_castle.add(56);
		rook_can_castle.add(63);
	}
	private void removePiece(int to) {
		pos[to] = -1;
		if(!whitePiece.removeIf(p -> p.isSamePos(to))) {
			blackPiece.removeIf(p -> p.isSamePos(to));
		}
	}

	public PieceInfo getPieceInfo(int x,int y) {
		return getPieceInfo(y*8+x);
	}
	public PieceInfo getPieceInfo(int from) {
		int c = pos[from];
		if(c < 0) return PieceInfo.NONE; 
		if(c >= 6) return PieceInfo.WHITE; 
		return PieceInfo.BLACK;
	}

	public boolean isWhiteTurn() {
		return this.turn % 2 == 1;
	}

	public List<Piece> getListPiece(boolean white) {
		return white ? this.whitePiece : this.blackPiece;
	}

	public boolean containEnPassant(int x, int y) {
		return containEnPassant(y*8+x);
	}
	public boolean containEnPassant(int target) {
		return en_passant.contains(target);
	}

	public boolean isKingInCheck(boolean white) {
		if(white) {
			for(Piece currentpiece : this.blackPiece) {
				List<Move> list = new ArrayList<>();	
				currentpiece.getPossiableMove(this,list, false,true);
				for(Move m : list) {
					if(this.whiteking.isSamePos(m.getTo())) {
						return true;
					}
				}
			}
		} else {
			for(Piece currentpiece : this.whitePiece) {
				List<Move> list = new ArrayList<>();	
				currentpiece.getPossiableMove(this,list, true,true);
				for(Move m : list) {
					if(this.blackking.isSamePos(m.getTo())) {
						return true;
					}
				}
			}
		}
		return false;
	}
	public void onUpdateMove(Move move) {
		int from = move.getFrom();//Điểm đầu
		int to = move.getTo();//Điểm tới
		Piece p = getPiece(from);
		if(p == null) return;
		//Nếu xe đã di chuyển, xóa khả năng nhập thành về xe đó
		if(rook_can_castle.contains(from)) {
			rook_can_castle.remove(Integer.valueOf(from));
		}
		//Trong trường hợp bên quân kia ăn xe,cũng xóa khả năng nhập thành về xe đã mất
		if(rook_can_castle.contains(to)) {
			rook_can_castle.remove(Integer.valueOf(to));
		}
		p.updateMove();//Cập nhật di chuyển, chủ yếu cho vua
		removePiece(to); // Ăn quân bên kia ở điểm đến (nếu có)\
		
		int pos = this.pos[from]; //Lấy giá trị id ở quân ban đầu
		this.pos[from] = -1;//Xóa giá trị id vị trí cũ
		this.pos[to] = pos;//Cập nhật giá trị id mới ở vị trí mới
		p.updateLocation(to); //Cập nhật vị trí mới cho quân
		
		
		
		en_passant.clear();//Xóa tất cả khả năng bắt tốt qua đường hiện tại
		
		//Nếu tốt lần đầu đi 2 ô, thêm khả năng bị bắt tốt qua đường
		if(move instanceof PawnFirstTwoMove) {
			en_passant.add(to);
		}
		//Bắt tốt qua đường, tốt có thể đi chéo nếu có tốt địch di chuyển 2 ô
		if(move instanceof EnPassantMove) {
			int target = ((EnPassantMove)move).getTarget();
			removePiece(target);
		}
		//Nhập thành, vua có thể di chuyển 2 ô vào trong và xe chạy ra ngoài
		//Thường dùng để đưa vua vào vùng an toàn và đưa xe tham chiến
		//Yêu cầu vua và xe không di chuyển lần đầu tiên
		if(move instanceof CastlesMove) {
			CastlesMove cm = ((CastlesMove)move);
			int rookfrom = cm.getRookFrom(); //Vị trí xe ban đầu
			int rookto = cm.getRookTo(); //Vị trí mới của xe khi nhập thành
			int pos2 = this.pos[rookfrom];
			Piece rook = getPiece(cm.getRookFrom());
			rook.updateLocation(rookto);
			this.pos[rookfrom] = -1;
			this.pos[rookto] = pos2;
		}
		//Thăng cấp, xóa con tốt và thay thế bằng 1 quân mới (Hậu, Tượng, Xe hoặc Mã)
		//Cùng đội
		if(move instanceof PromoteMove) {
			int type = ((PromoteMove)move).getType();
			removePiece(to);
			Piece promote = null;
			switch(type) {
			case 0:
				promote =  new Queen(to);
				break;
			case 1:
				promote =  new Rook(to);
				break;
			case 2:
				promote =  new Bishop(to);
				break;
			case 3:
				promote =  new Knight(to);
				break;
			default: break;
			}
			if(promote != null) {
				addPiece(promote,isWhiteTurn());
			}
		}
		turn += 1;//Thêm turns
	}


	public Piece getPiece(int pos) {
		Piece p = null;
		for(Piece c : this.whitePiece) {
			if(c.isSamePos(pos)) {
				p = c;
				break;
			}
		}
		if(p == null) {
			for(Piece c : this.blackPiece) {
				if(c.isSamePos(pos)) {
					p = c;
					break;
				}
			}
		}
		return p;
	}
	
	

}
