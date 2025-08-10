package me.huu_thinh.chess.view.dialog;

import javax.swing.JOptionPane;

import me.huu_thinh.chess.view.panel.MainPanelGame;

public class NewGameDialog {

	public static void showDialogReset(MainPanelGame panel) {
		int choose = JOptionPane.showConfirmDialog(null, "Bạn có muốn tạo game mới không ?","Xác nhận", JOptionPane.YES_NO_OPTION);
		if(choose == JOptionPane.YES_OPTION) {
			panel.getGame().reset();
			panel.updateJTextPgn();
		}
	}
	
}
