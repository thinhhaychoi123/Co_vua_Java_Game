package me.huu_thinh.chess.view.panel;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import me.huu_thinh.chess.controller.MouseController;
import me.huu_thinh.chess.model.Game;

public class MainPanelGame  extends JPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1948719684150099008L;
	private Game game;
	private JTextArea jta;
	
	public MainPanelGame(Game input) {
		game = input;
		new MouseController(this);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawGame(g,this.getMousePosition());
	}
	
	public void drawGame(Graphics g,Point point) {
		BoardRenderer.drawBoard(g);
		BoardRenderer.drawCheckMate(g,game);
		BoardRenderer.drawLastMove(g, game);
		BoardRenderer.drawPiece(g,game);
		BoardRenderer.drawPieceCanMove(g,game);
		BoardRenderer.drawPieceDrag(g,game,point);
		BoardRenderer.drawString(g);
	}

	public Game getGame() {
		return game;
	}


	public void repaintGame() {
		repaint();
		revalidate();
	}


	public void updateJTextPgn() {
		String getcm = game.getBoard().printCurrentMove();
		jta.setText(getcm);
	}


	public void setJTextAreaUpdate(JTextArea jta) {
		this.jta = jta;
		updateJTextPgn();
	}


	
	
}
