package me.huu_thinh.chess.view.dialog;

import javax.swing.JOptionPane;

public class PromoteDialog {

	
	public static int showMessageForPromotion() {
		Object[] options = { "Hậu", "Xe", "Tượng", "Mã" };
		
		return JOptionPane.showOptionDialog(null, "Hãy biến quân tốt thành", null,
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	}

}
