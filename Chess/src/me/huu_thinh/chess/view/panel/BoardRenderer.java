package me.huu_thinh.chess.view.panel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import me.huu_thinh.chess.assets.ImageInput;
import me.huu_thinh.chess.model.Game;
import me.huu_thinh.chess.model.move.Move;
import me.huu_thinh.chess.model.move.type.AttackMove;
import me.huu_thinh.chess.model.move.type.PromoteMove;
import me.huu_thinh.chess.model.pieces.King;
import me.huu_thinh.chess.view.Constants;

public class BoardRenderer {

	private final static Color white_square = Color.decode("#CCDAE0");
	private final static Color black_square = Color.decode("#7094A9");
	
	public static void drawPiece(Graphics g, Game game) {
		for(int i = 0;i < 64;i++) {
			int pos = i;
//			int pos = GameSettings.canFlipBoard() ? 63 - i : i;
			int x = Math.floorMod(pos, 8);
			int y = Math.floorDiv(pos, 8);
			int choose = game.getBoard().getPiecePostion().getPieceID(i);
			if(choose >= 0) {
					if(game.isDrag() && i == game.getSelectPiece()) continue;
					g.drawImage(ImageInput.resize(ImageInput.getPieceImage(choose), Constants.getWidthSquare(), Constants.getHeightSquare()), 
							Constants.getWidthSquare()*x, 
							Constants.getHeightSquare()*y,
							null);
			}
		}
	}

	public static void drawBoard(Graphics g) {
		int size_x = Constants.WIDTH / 8;
		int size_y = Constants.HEIGHT / 8;
		for(int x = 0; x < 8;x ++)
			for(int y = 0; y < 8;y++) {
				int currentsizex = size_x*x;
				int currentsizey = size_y*y;
				if((x+y) % 2 == 0) {
					g.setColor(white_square);
					g.fillRect(currentsizex, currentsizey, size_x, size_y);
				} else {
					g.setColor(black_square);
					g.fillRect(currentsizex, currentsizey, size_x, size_y);
				}
			}
	}

	public static void drawPieceDrag(Graphics g, Game game, Point point) {
		if(!game.isDrag()) return;
		if(point == null) return;
		int choose = game.getBoard().getPiecePostion().getPieceID(game.getSelectPiece());
		if(choose < 0) {
			System.out.println("Not found !");
			return;
		}
		g.drawImage(ImageInput.resize(ImageInput.getPieceImage(choose),
				Constants.getWidthSquare(),
				Constants.getHeightSquare()),
				(int)(point.getX()-Constants.getWidthSquareDiv4()),
				(int)(point.getY()-Constants.getHeightSquareDiv4()),
				null);
	}
	
	public static void drawPieceCanMove(Graphics g, Game game) {
		int choose = game.getBoard().getPiecePostion().getPieceID(game.getSelectPiece());
		if(choose < 0) {
			return;
		}
//		g.setXORMode(Color.WHITE);
//		g.setColor(Color.decode("#ffff99"));
//		int size_x = Constants.WIDTH / 8;
//		int size_y = Constants.HEIGHT / 8;
//		int x = Math.floorMod(this.choose, 8);
//		int y = Math.floorDiv(this.choose, 8);
//		g.fillRect(size_x*x, size_y*y, size_x, size_y);
//		g.setPaintMode();
		for(Move f : game.getBoard().fillMove(game.getSelectPiece())) {
			if(f instanceof AttackMove) {
				g.setColor(Color.ORANGE);
			} else if (f instanceof PromoteMove) {
				g.setColor(Color.RED);
			}else {
				g.setColor(Color.BLACK);
			}
//			int to = GameSettings.canFlipBoard() ? 63 - f.getTo() : f.getTo();
			int to = f.getTo();
			int x1 = Math.floorMod(to, 8);
			int y1 = Math.floorDiv(to, 8);
			g.fillOval(x1*Constants.getWidthSquare()+(Constants.getWidthSquare()/2 - Constants.getWidthSquare()/8 ),
					y1*Constants.getHeightSquare()+(Constants.getHeightSquare()/2 - Constants.getHeightSquare()/8 ),
					Constants.getWidthSquare()/4,
					Constants.getHeightSquare()/4);
		}
		
	}
	
	public static void drawCheckMate(Graphics g, Game game) {
		g.setColor(Color.RED);
		int size_x = Constants.getWidthSquare();
		int size_y = Constants.getHeightSquare();
		
		if(game.getBoard().isKingCheck(false)) {
			King blackking = game.getBoard().getPiecePostion().getKing(false);
			g.fillRect(blackking.getX(false)*size_x, blackking.getY(false)*size_y, size_x, size_y);
		}
		if(game.getBoard().isKingCheck(true)) {
			King whiteking = game.getBoard().getPiecePostion().getKing(true);
			g.fillRect(whiteking.getX(false)*size_x, whiteking.getY(false)*size_y, size_x, size_y);
		}
		
	}

	public static void drawLastMove(Graphics g,Game game) {
			Move last = game.getBoard().getLastMove();
			if(last != null) {
				int size_x = Constants.getWidthSquare();
				int size_y = Constants.getHeightSquare();
				
				
				Color c = new Color(255,255,153);
//				g.setColor(c);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.55f));
				int xfrom = Math.floorMod(last.getFrom(), 8);
				int yfrom = Math.floorDiv(last.getFrom(), 8);
//				xfrom = GameSettings.canFlipBoard() ? (7 - xfrom) : xfrom;
//				yfrom = GameSettings.canFlipBoard() ? (7 - yfrom) : yfrom;
				int xto = Math.floorMod(last.getTo(), 8);
				int yto = Math.floorDiv(last.getTo(), 8);
//				xto = GameSettings.canFlipBoard() ? (7 - xto) : xto;
//				yto = GameSettings.canFlipBoard() ? (7 - yto) : yto;
				g2d.setColor(c);
				g2d.fillRect(size_x*xfrom, size_y*yfrom, size_x, size_y);

				g2d.fillRect(size_x*xto, size_y*yto, size_x, size_y);
				g2d.setComposite(AlphaComposite.SrcOver);
			
			
		}
	}
	
	public static void drawString(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        int fontSize = (int) (Constants.getHeightSquare() * 0.27);
        g2d.setFont(new Font("Arial", Font.BOLD, fontSize));
        FontMetrics fm = g2d.getFontMetrics();
		for(int i = 0;i < 8;i++) {
			if(i % 2 == 0) {
				 g2d.setColor(BoardRenderer.white_square);	
			} else {
				g2d.setColor(BoardRenderer.black_square);
			}
			String file = String.valueOf((char)('a' + i % 26));
			int textWidth = fm.stringWidth(file);
            int textHeight = fm.getAscent();
            int textX = Constants.getWidthSquare() *i + (Constants.getWidthSquare() - textWidth);
            int textY = Constants.getHeightSquare()*7 + (Constants.getHeightSquare() - textHeight - 4)  + fm.getAscent();
			g2d.drawString(file, textX, textY);
		}
		for(int i = 0;i < 8;i++) {
			if(i % 2 == 0) {
				 g2d.setColor(BoardRenderer.black_square);	
			} else {
				g2d.setColor(BoardRenderer.white_square);
			}
			String rank = String.valueOf((8-i));
			
            int textHeight = fm.getAscent();
            int textX = 1;
            int textY = Constants.getHeightSquare() * (i) + textHeight;
			g2d.drawString(rank, textX, textY);
		}
	}
	
	
}
