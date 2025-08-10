package me.huu_thinh.chess.view;

public class Constants {
	
	
	public static final int FRAME_WIDTH = 870;
	public static final int FRAME_HEIGHT = 650;
	
	public static final int WIDTH = 650;
	public static final int HEIGHT = 650;
	
	public static final int MIN_X_BOARD = 0;
	public static final int MIN_Y_BOARD = 0;
	public static final int MAX_X_BOARD = 8;
	public static final int MAX_Y_BOARD = 8;
	
	
	public static int getWidthSquare() {	
		return WIDTH/8;
	}
	
	public static int getHeightSquare() {
		return HEIGHT/8;
	}
	public static int getWidthSquareDiv4() {
		return WIDTH/16;
	}
	public static int getHeightSquareDiv4() {
		return HEIGHT/16;
	}
	
}
