package board;

import java.awt.Font;

import comp.BoardPanel.BoardPanelImp;

public interface IBoardInterface {
	
public static final int COLOR_MIN = 35;
	
	/**
	 * Maximum color component values for tiles. This is required if we
	 * want to show both light and dark shading on our tiles.
	 */
	public static final int COLOR_MAX = 255 - COLOR_MIN;
	
	/**
	 * The width of the border around the game board.
	 */
	public static final int BORDER_WIDTH = 5;
	
	/**
	 * The number of columns on the board.
	 */
	public static final int COL_COUNT = 10;
		
	/**
	 * The number of visible rows on the board.
	 */
	public static final int VISIBLE_ROW_COUNT = 20;
	
	/**
	 * The number of rows that are hidden from view.
	 */
	public static final int HIDDEN_ROW_COUNT = 2;
	
	/**
	 * The total number of rows that the board contains.
	 */
	public static final int ROW_COUNT = VISIBLE_ROW_COUNT + HIDDEN_ROW_COUNT;
	
	/**
	 * The number of pixels that a tile takes up.
	 */
	public static final int TILE_SIZE = 24;
	
	/**
	 * The width of the shading on the tiles.
	 */
	public static final int SHADE_WIDTH = 4;
	
	/**
	 * The central x coordinate on the game board.
	 */
	public static final int CENTER_X = COL_COUNT * TILE_SIZE / 2;
	
	/**
	 * The central y coordinate on the game board.
	 */
	public static final int CENTER_Y = VISIBLE_ROW_COUNT * TILE_SIZE / 2;
		
	/**
	 * The total width of the panel.
	 */
	public static final int PANEL_WIDTH = COL_COUNT * TILE_SIZE + BORDER_WIDTH * 2;
	
	/**
	 * The total height of the panel.
	 */
	public static final int PANEL_HEIGHT = VISIBLE_ROW_COUNT * TILE_SIZE + BORDER_WIDTH * 2;
	
	/**
	 * The larger font to display.
	 */
	public static final Font LARGE_FONT = new Font("Tahoma", Font.BOLD, 16);

	/**
	 * The smaller font to display.
	 */
	
	
	public static final Font SMALL_FONT = new Font("Tahoma", Font.BOLD, 12);
	public int checkLines();
	public void clear();
	public boolean isValidAndEmpty(TileType type, int x, int y, int rotation) ;
	public void addPiece(TileType type, int x, int y, int rotation) ;
	
	public BoardPanelImp boardpanelobject();
}
