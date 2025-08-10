package me.huu_thinh.chess.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import me.huu_thinh.chess.assets.ImageInput;
import me.huu_thinh.chess.model.Game;
import me.huu_thinh.chess.view.panel.MainPanel;

public class Main extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Game game;
	private MainPanel panel;
	
	public Main() {
		uiSetup();
		new ImageInput();
		game = new Game();
		panel = new MainPanel(game);
		add(panel);
	}
	//1. e4 e5 2. Qh5 Nf6 3. Qxf7+ Kxf7 4. Nf3 Nxe4 5. Bd3 Nxf2 6. Kxf2 Qh4+ 7. Ke2 Be7 8. Rf1 Rf8 9. Nxh4+ Kg8 10. Rxf8+ Bxf8 11. Nf5 g6 12. Ne7+ Bxe7 13. Bxg6 hxg6 14. Nc3 d6 15. d4 exd4 16. Ne4 Bg4+ 17. Kd3 Nc6 18. Bg5 Bxg5 19. Nxg5 Nb4+ 20. Kxd4 Nxc2+ 21. Kd3 Nxa1 22. Kc3 Rd8 23. Ne6 d5 24. b3 Bxe6 25. Kb2 Nc2 26. Kxc2 d4 27. Kc1 d3 28. Kd2 g5 29. b4 c5 30. bxc5 Bd5 31. Kxd3 Bc6+ 32. Kc4 g4 33. Kb4 Rd4+ 34. Ka3 g3 35. h3 Bxg2 36. h4 Bh3 37. Kb3 g2 38. Kc3 Rd8 39. Kc2 2g1 40. Kb2 Qf2+ 41. Kc1 Qxh4 42. Kc2 Qc4+ 43. Kb2 Rd2+ 44. Kb1 Qc2+ 45. Ka1 Qc1# 
	private void uiSetup() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		setTitle("Chess");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	getContentPane().setPreferredSize(new Dimension(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT));
    	pack();
		setLocationRelativeTo(null);
    	setVisible(true);
    	setResizable(false);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Main();
			}
	
		});
	}
	
}
