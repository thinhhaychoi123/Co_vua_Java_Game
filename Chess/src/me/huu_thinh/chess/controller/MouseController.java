package me.huu_thinh.chess.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

import me.huu_thinh.chess.view.panel.MainPanelGame;

public class MouseController implements MouseListener, MouseMotionListener {

	
	private MainPanelGame panel;
	
	public MouseController(MainPanelGame panel) {
		this.panel = panel;
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(panel.getGame().isDrag()) {
			panel.repaintGame();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)) {
		this.panel.getGame().setSelectPiece(e.getX(), e.getY());
		
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)) {
			this.panel.getGame().setDrag(e.getX(), e.getY());	
			this.panel.repaintGame();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.panel.getGame().setUnDrag(e.getX(), e.getY());
		this.panel.repaintGame();
		if(panel.getGame().hasMoving()) {
			panel.getGame().changeSide();
			panel.updateJTextPgn();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
