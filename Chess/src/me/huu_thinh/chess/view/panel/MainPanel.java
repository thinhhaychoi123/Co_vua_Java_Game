package me.huu_thinh.chess.view.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import me.huu_thinh.chess.model.Game;
import me.huu_thinh.chess.view.Constants;


public class MainPanel extends JPanel
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param game 
	 * 
	 */
	

	public MainPanel(Game game) {
		setPreferredSize(new Dimension(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT));
		setLayout(new BorderLayout());
		
		MainPanelGame s = new MainPanelGame(game);
		s.setPreferredSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));
		add(s,BorderLayout.CENTER);
		
		InteractPanel s2 = new InteractPanel(s);
		s2.setPreferredSize(new Dimension(Constants.FRAME_WIDTH-Constants.WIDTH,Constants.HEIGHT));
		add(s2,BorderLayout.EAST);
	}

}
