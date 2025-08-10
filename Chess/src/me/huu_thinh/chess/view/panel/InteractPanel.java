package me.huu_thinh.chess.view.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import me.huu_thinh.chess.view.dialog.CopyPGNDialog;
import me.huu_thinh.chess.view.dialog.NewGameDialog;

public class InteractPanel extends JPanel {


		/**
	 * 
	 */
	private static final long serialVersionUID = -3967655163366673259L;

		public InteractPanel(MainPanelGame panel) {
			
			
		setBackground(Color.decode("#40ad76"));
		setLayout(null);
		JButton b = new JButton("Trò chơi mới");
		b.setBounds(50, 400, 125, 50);
		add(b);
		b.addActionListener((a) ->{
			NewGameDialog.showDialogReset(panel);
			panel.repaintGame();
		});
		JButton b2 = new JButton("Sao chép PGN");
		b2.setBounds(50, 450, 125, 50);
		b2.addActionListener((a) ->{
			copyPGN(panel.getGame().getBoard().getPgnMove());
			CopyPGNDialog.openLog();
		});
		add(b2);
		setBorder(BorderFactory.createLineBorder(Color.decode("#028a0f"), 3));
		
		JTextArea pgnArea = new JTextArea();
        pgnArea.setEditable(false);
        pgnArea.setWrapStyleWord(true);
        pgnArea.setLineWrap(true);
        pgnArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane pgnScroll = new JScrollPane(pgnArea);
        pgnScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pgnScroll.setBounds(42, 50, 150, 300);
        add(pgnScroll);
        panel.setJTextAreaUpdate(pgnArea);
		
	}
		
		private void copyPGN(String copy) {
			StringSelection selection = new StringSelection(copy);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(selection, selection);
		}

}
