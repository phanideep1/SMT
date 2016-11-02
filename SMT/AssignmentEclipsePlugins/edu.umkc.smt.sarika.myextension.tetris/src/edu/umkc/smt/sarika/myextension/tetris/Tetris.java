package edu.umkc.smt.sarika.myextension.tetris;


import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import edu.umkc.smt.sarika.myextension.clock.IClock;
import edu.umkc.smt.sarika.myextension.clock2.IClock2;

/**
 * The {@code Tetris} class is responsible for handling much of the game logic and
 * reading user input.
 * @author Brendan Jones
 *
 */
public class Tetris extends JFrame {
	
	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = -4722429764792514382L;

	/**
	 * The number of milliseconds per frame.
	 */
	private static final long FRAME_TIME = 1000L / 50L;
	
	/**
	 * The number of pieces that exist.
	 */
	private static final int TYPE_COUNT = TileType.values().length;
	private static final int TYPE_COUNT2 = TileType2.values().length;
		
	/**
	 * The BoardPanel instance.
	 */
	private BoardPanel board;
	private BoardPanel2 board2;
	
	/**
	 * The SidePanel instance.
	 */
	private SidePanel side;
	private SidePanel2 side2;
	
	/**
	 * Whether or not the game is paused.
	 */
	private boolean isPaused;
	private boolean isPaused2;
	/**
	 * Whether or not we've played a game yet. This is set to true
	 * initially and then set to false when the game starts.
	 */
	private boolean isNewGame;
	private boolean isNewGame2;
	
	/**
	 * Whether or not the game is over.
	 */
	private boolean isGameOver;
	private boolean isGameOver2;
	
	/**
	 * The current level we're on.
	 */
	private int level;
	private int level2;
	
	/**
	 * The current score.
	 */
	private int score;
	private int score2;
	
	/**
	 * The random number generator. This is used to
	 * spit out pieces randomly.
	 */
	private Random random;
	
	/**
	 * The clock that handles the update logic.
	 */
	//private Clock logicTimer;
	private Clock2 logicTimer2;
				
	/**
	 * The current type of tile.
	 */
	private TileType currentType;
	private TileType2 currentType2;
	
	/**
	 * The next type of tile.
	 */
	private TileType nextType;
	private TileType2 nextType2;
		
	/**
	 * The current column of our tile.
	 */
	private int currentCol;
	private int currentCol2;
	
	/**
	 * The current row of our tile.
	 */
	private int currentRow;
	private int currentRow2;
	
	/**
	 * The current rotation of our tile.
	 */
	private int currentRotation;
	private int currentRotation2;
	
	/**
	 * Ensures that a certain amount of time passes after a piece is
	 * spawned before we can drop it.
	 */
	private int dropCooldown;
	private int dropCooldown2;
	
	/**
	 * The speed of the game.
	 */
	private float gameSpeed;
	private float gameSpeed2;
	
	/**
	 * Creates a new Tetris instance. Sets up the window's properties,
	 * and adds a controller listener.
	 */
	
	IConfigurationElement[] config = Platform.getExtensionRegistry()
			.getConfigurationElementsFor("edu.umkc.smt.sarika.myextension.clock");//Change to fit your own
	
	Object o1;
	Object o2;
	
	Tetris() {
try
{
		for (IConfigurationElement e : config) {
			System.out.println("Evaluating extension");
			o1 = e.createExecutableExtension("class");
			//o2 = e.createExecutableExtension("class2");
				/*
		 * Set the basic properties of the window.
		 */
		//super("Tetris");
		setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		/*
		 * Initialize the BoardPanel and SidePanel instances.
		 */
		this.board= new BoardPanel(this);
		this.board2 = new BoardPanel2(this);
		this.side= new SidePanel(this);
		this.side2= new SidePanel2(this);
		
		/*
		 * Add the BoardPanel and SidePanel instances to the window.
		 */
		add(board);
		add(side);
		add(board2);
		add(side2);
		
		/*
		 * Adds a custom anonymous KeyListener to the frame.
		 */
		addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
								
				switch(e.getKeyCode()) {
				
				/*
				 * Drop - When pressed, we check to see that the game is not
				 * paused and that there is no drop cooldown, then set the
				 * logic timer to run at a speed of 25 cycles per second.
				 */
				case KeyEvent.VK_DOWN:
					if(!isPaused2 && dropCooldown2 == 0) {
						logicTimer2.setCyclesPerSecond(25.0f);
					}
					break;
					
				/*
				 * Move Left - When pressed, we check to see that the game is
				 * not paused and that the position to the left of the current
				 * position is valid. If so, we decrement the current column by 1.
				 */
				case KeyEvent.VK_LEFT:
					if(!isPaused2 && board2.isValidAndEmpty(currentType2, currentCol2 - 1, currentRow2, currentRotation2)) {
						currentCol2--;
					}
					break;
					
				/*
				 * Move Right - When pressed, we check to see that the game is
				 * not paused and that the position to the right of the current
				 * position is valid. If so, we increment the current column by 1.
				 */
				case KeyEvent.VK_RIGHT:
					if(!isPaused2 && board2.isValidAndEmpty(currentType2, currentCol2 + 1, currentRow2, currentRotation2)) {
						currentCol2++;
					}
					break;
					
				/*
				 * Rotate Anticlockwise - When pressed, check to see that the game is not paused
				 * and then attempt to rotate the piece anticlockwise. Because of the size and
				 * complexity of the rotation code, as well as it's similarity to clockwise
				 * rotation, the code for rotating the piece is handled in another method.
				 */
				case KeyEvent.VK_UP:
					if(!isPaused2) {
						rotatePiece2((currentRotation2 == 0) ? 3 : currentRotation2 - 1);
					}
					break;
				
				/*
			     * Rotate Clockwise - When pressed, check to see that the game is not paused
				 * and then attempt to rotate the piece clockwise. Because of the size and
				 * complexity of the rotation code, as well as it's similarity to anticlockwise
				 * rotation, the code for rotating the piece is handled in another method.
				 */
				
					
				/*
				 * Pause Game - When pressed, check to see that we're currently playing a game.
				 * If so, toggle the pause variable and update the logic timer to reflect this
				 * change, otherwise the game will execute a huge number of updates and essentially
				 * cause an instant game over when we unpause if we stay paused for more than a
				 * minute or so.
				 */
				
				
				case KeyEvent.VK_S:
					
					if(!isPaused && dropCooldown == 0) {
						((IClock) o1).setCyclesPerSecond(25.0f);
					}
					break;
					
				/*
				 * Move Left - When pressed, we check to see that the game is
				 * not paused and that the position to the left of the current
				 * position is valid. If so, we decrement the current column by 1.
				 */
				case KeyEvent.VK_A:
					
				
				if(!isPaused && board.isValidAndEmpty(currentType, currentCol - 1, currentRow, currentRotation)) {
					currentCol--;		
					}
					break;
					
				/*
				 * Move Right - When pressed, we check to see that the game is
				 * not paused and that the position to the right of the current
				 * position is valid. If so, we increment the current column by 1.
				 */
				case KeyEvent.VK_D:
					if(!isPaused && board.isValidAndEmpty(currentType, currentCol + 1, currentRow, currentRotation)) {
						currentCol++;
					}
					break;
					
				/*
				 * Rotate Anticlockwise - When pressed, check to see that the game is not paused
				 * and then attempt to rotate the piece anticlockwise. Because of the size and
				 * complexity of the rotation code, as well as it's similarity to clockwise
				 * rotation, the code for rotating the piece is handled in another method.
				 */
				case KeyEvent.VK_E:
					if(!isPaused) {
						rotatePiece((currentRotation == 0) ? 3 : currentRotation - 1);
					}
					break;
				
				/*
			     * Rotate Clockwise - When pressed, check to see that the game is not paused
				 * and then attempt to rotate the piece clockwise. Because of the size and
				 * complexity of the rotation code, as well as it's similarity to anticlockwise
				 * rotation, the code for rotating the piece is handled in another method.
				 */
				
					
				/*
				 * Pause Game - When pressed, check to see that we're currently playing a game.
				 * If so, toggle the pause variable and update the logic timer to reflect this
				 * change, otherwise the game will execute a huge number of updates and essentially
				 * cause an instant game over when we unpause if we stay paused for more than a
				 * minute or so.
				 */
				case KeyEvent.VK_P:
					if(!isGameOver && !isNewGame) {
						isPaused = !isPaused;
						((IClock) o1).setPaused(isPaused);
					}
					if(!isGameOver2 && !isNewGame2) {
						isPaused2 = !isPaused2;
						logicTimer2.setPaused(isPaused2);
					}
					break;
				
				/*
				 * Start Game - When pressed, check to see that we're in either a game over or new
				 * game state. If so, reset the game.
				 */
				case KeyEvent.VK_ENTER:
					if(isGameOver || isNewGame) {
						resetGame();
						resetGame2();
					
					}
					if(isGameOver2 || isNewGame2) {
						resetGame2();
					    resetGame();
					}
					break;
				
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				switch(e.getKeyCode()) {
				
				/*
				 * Drop - When released, we set the speed of the logic timer
				 * back to whatever the current game speed is and clear out
				 * any cycles that might still be elapsed.
				 */
				case KeyEvent.VK_S:
					((IClock) o1).setCyclesPerSecond(gameSpeed);
					((IClock) o1).reset();
					break;
				case KeyEvent.VK_DOWN:
					logicTimer2.setCyclesPerSecond(gameSpeed2);
					logicTimer2.reset();
					break;
				}
				
			}
			
		});
		
		/*
		 * Here we resize the frame to hold the BoardPanel and SidePanel instances,
		 * center the window on the screen, and show it to the user.
		 */
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		//setLocation(850,200);
		}
} catch (CoreException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}


	}
	
	/**
	 * Starts the game running. Initializes everything and enters the game loop.
	 */
	void startGame() {
		/*
		 * Initialize our random number generator, logic timer, and new game variables.
		 */
		this.random = new Random();
		this.isNewGame = true;
		this.gameSpeed = 1.0f;
		
		/*
		 * Setup the timer to keep the game from running before the user presses enter
		 * to start it.
		 */
		((IClock) o1).getClock(gameSpeed);
		((IClock) o1).setPaused(true);
		
		while(true) {
			//Get the time that the frame started.
			long start = System.nanoTime();
			
			//Update the logic timer.
			((IClock) o1).update();
			
			/*
			 * If a cycle has elapsed on the timer, we can update the game and
			 * move our current piece down.
			 */
			if(((IClock) o1).hasElapsedCycle()) {
				updateGame();
			}
		
			//Decrement the drop cool down if necessary.
			if(dropCooldown > 0) {
				dropCooldown--;
			}
			
			//Display the window to the user.
			renderGame();
			
			/*
			 * Sleep to cap the framerate.
			 */
			long delta = (System.nanoTime() - start) / 1000000L;
			if(delta < FRAME_TIME) {
				try {
					Thread.sleep(FRAME_TIME - delta);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Updates the game and handles the bulk of it's logic.
	 */
	private void updateGame() {
		/*
		 * Check to see if the piece's position can move down to the next row.
		 */
		if(board.isValidAndEmpty(currentType, currentCol, currentRow + 1, currentRotation)) {
			//Increment the current row if it's safe to do so.
			currentRow++;
		} else {
			/*
			 * We've either reached the bottom of the board, or landed on another piece, so
			 * we need to add the piece to the board.
			 */
			board.addPiece(currentType, currentCol, currentRow, currentRotation);
			
			/*
			 * Check to see if adding the new piece resulted in any cleared lines. If so,
			 * increase the player's score. (Up to 4 lines can be cleared in a single go;
			 * [1 = 100pts, 2 = 200pts, 3 = 400pts, 4 = 800pts]).
			 */
			int cleared = board.checkLines();
			if(cleared > 0) {
				score += 50 << cleared;
				BoardPanel2.ROW_COUNT--;
			}
			
			/*
			 * Increase the speed slightly for the next piece and update the game's timer
			 * to reflect the increase.
			 */
			gameSpeed += 0.035f;
			((IClock) o1).setCyclesPerSecond(gameSpeed);
			((IClock) o1).reset();
			
			/*
			 * Set the drop cooldown so the next piece doesn't automatically come flying
			 * in from the heavens immediately after this piece hits if we've not reacted
			 * yet. (~0.5 second buffer).
			 */
			dropCooldown = 25;
			
			/*
			 * Update the difficulty level. This has no effect on the game, and is only
			 * used in the "Level" string in the SidePanel.
			 */
			level = (int)(gameSpeed * 1.70f);
			
			/*
			 * Spawn a new piece to control.
			 */
			spawnPiece();
		}		
	}
	
	/**
	 * Forces the BoardPanel and SidePanel to repaint.
	 */
	private void renderGame() {
		board.repaint();
		side.repaint();
	}
	
	/**
	 * Resets the game variables to their default values at the start
	 * of a new game.
	 */
	private void resetGame() {
		this.level = 1;
		this.score = 0;
		this.gameSpeed = 1.0f;
		this.nextType = TileType.values()[random.nextInt(TYPE_COUNT)];
		this.isNewGame = false;
		this.isGameOver = false;		
		board.clear();
		((IClock) o1).reset();
		((IClock) o1).setCyclesPerSecond(gameSpeed);
		spawnPiece();
	}
		
	/**
	 * Spawns a new piece and resets our piece's variables to their default
	 * values.
	 */
	private void spawnPiece() {
		/*
		 * Poll the last piece and reset our position and rotation to
		 * their default variables, then pick the next piece to use.
		 */
		this.currentType = nextType;
		this.currentCol = currentType.getSpawnColumn();
		this.currentRow = currentType.getSpawnRow();
		this.currentRotation = 0;
		this.nextType = TileType.values()[random.nextInt(TYPE_COUNT)];
		
		/*
		 * If the spawn point is invalid, we need to pause the game and flag that we've lost
		 * because it means that the pieces on the board have gotten too high.
		 */
		if(!board.isValidAndEmpty(currentType, currentCol, currentRow, currentRotation)) {
			this.isGameOver = true;
			((IClock) o1).setPaused(true);
		}		
	}

	/**
	 * Attempts to set the rotation of the current piece to newRotation.
	 * @param newRotation The rotation of the new peice.
	 */
	private void rotatePiece(int newRotation) {
		/*
		 * Sometimes pieces will need to be moved when rotated to avoid clipping
		 * out of the board (the I piece is a good example of this). Here we store
		 * a temporary row and column in case we need to move the tile as well.
		 */
		int newColumn = currentCol;
		int newRow = currentRow;
		
		/*
		 * Get the insets for each of the sides. These are used to determine how
		 * many empty rows or columns there are on a given side.
		 */
		int left = currentType.getLeftInset(newRotation);
		int right = currentType.getRightInset(newRotation);
		int top = currentType.getTopInset(newRotation);
		int bottom = currentType.getBottomInset(newRotation);
		
		/*
		 * If the current piece is too far to the left or right, move the piece away from the edges
		 * so that the piece doesn't clip out of the map and automatically become invalid.
		 */
		if(currentCol < -left) {
			newColumn -= currentCol - left;
		} else if(currentCol + currentType.getDimension() - right >= BoardPanel.COL_COUNT) {
			newColumn -= (currentCol + currentType.getDimension() - right) - BoardPanel.COL_COUNT + 1;
		}
		
		/*
		 * If the current piece is too far to the top or bottom, move the piece away from the edges
		 * so that the piece doesn't clip out of the map and automatically become invalid.
		 */
		if(currentRow < -top) {
			newRow -= currentRow - top;
		} else if(currentRow + currentType.getDimension() - bottom >= BoardPanel.ROW_COUNT) {
			newRow -= (currentRow + currentType.getDimension() - bottom) - BoardPanel.ROW_COUNT + 1;
		}
		
		/*
		 * Check to see if the new position is acceptable. If it is, update the rotation and
		 * position of the piece.
		 */
		if(board.isValidAndEmpty(currentType, newColumn, newRow, newRotation)) {
			currentRotation = newRotation;
			currentRow = newRow;
			currentCol = newColumn;
		}
	}
	
	/**
	 * Checks to see whether or not the game is paused.
	 * @return Whether or not the game is paused.
	 */
	public boolean isPaused() {
		return isPaused;
	}
	
	/**
	 * Checks to see whether or not the game is over.
	 * @return Whether or not the game is over.
	 */
	public boolean isGameOver() {
		return isGameOver;
	}
	
	/**
	 * Checks to see whether or not we're on a new game.
	 * @return Whether or not this is a new game.
	 */
	public boolean isNewGame() {
		return isNewGame;
	}
	
	/**
	 * Gets the current score.
	 * @return The score.
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Gets the current level.
	 * @return The level.
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Gets the current type of piece we're using.
	 * @return The piece type.
	 */
	public TileType getPieceType() {
		return currentType;
	}
	
	/**
	 * Gets the next type of piece we're using.
	 * @return The next piece.
	 */
	public TileType getNextPieceType() {
		return nextType;
	}
	
	/**
	 * Gets the column of the current piece.
	 * @return The column.
	 */
	public int getPieceCol() {
		return currentCol;
	}
	
	/**
	 * Gets the row of the current piece.
	 * @return The row.
	 */
	public int getPieceRow() {
		return currentRow;
	}
	
	/**
	 * Gets the rotation of the current piece.
	 * @return The rotation.
	 */
	public int getPieceRotation() {
		return currentRotation;
	}

	/**
	 * Entry-point of the game. Responsible for creating and starting a new
	 * game instance.
	 * @param args Unused.
	 */
	
	
	
	
	void startGame2() {
		/*
		 * Initialize our random number generator, logic timer, and new game variables.
		 */
		this.random = new Random();
		this.isNewGame2 = true;
		this.gameSpeed2 = 1.0f;
		
		/*
		 * Setup the timer to keep the game from running before the user presses enter
		 * to start it.
		 */
		//logicTimer2.getClock2(gameSpeed2);
		
		this.logicTimer2=new Clock2(gameSpeed2);
		logicTimer2.setPaused(true);
		
		while(true) {
			//Get the time that the frame started.
			long start = System.nanoTime();
			
			//Update the logic timer.
			logicTimer2.update();
			
			/*
			 * If a cycle has elapsed on the timer, we can update the game and
			 * move our current piece down.
			 */
			if(logicTimer2.hasElapsedCycle()) {
				updateGame2();
			}
		
			//Decrement the drop cool down if necessary.
			if(dropCooldown2 > 0) {
				dropCooldown2--;
			}
			
			//Display the window to the user.
			renderGame2();
			
			/*
			 * Sleep to cap the framerate.
			 */
			long delta = (System.nanoTime() - start) / 1000000L;
			if(delta < FRAME_TIME) {
				try {
					Thread.sleep(FRAME_TIME - delta);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Updates the game and handles the bulk of it's logic.
	 */
	private void updateGame2() {
		/*
		 * Check to see if the piece's position can move down to the next row.
		 */
		if(board2.isValidAndEmpty(currentType2, currentCol2, currentRow2 + 1, currentRotation2)) {
			//Increment the current row if it's safe to do so.
			currentRow2++;
		} else {
			/*
			 * We've either reached the bottom of the board, or landed on another piece, so
			 * we need to add the piece to the board.
			 */
			board2.addPiece(currentType2, currentCol2, currentRow2, currentRotation2);
			
			/*
			 * Check to see if adding the new piece resulted in any cleared lines. If so,
			 * increase the player's score. (Up to 4 lines can be cleared in a single go;
			 * [1 = 100pts, 2 = 200pts, 3 = 400pts, 4 = 800pts]).
			 */
			int cleared = board2.checkLines();
			if(cleared > 0) {
				score2 += 50 << cleared;
				BoardPanel.ROW_COUNT--;
			}
			
			/*
			 * Increase the speed slightly for the next piece and update the game's timer
			 * to reflect the increase.
			 */
			gameSpeed2 += 0.035f;
			logicTimer2.setCyclesPerSecond(gameSpeed2);
			logicTimer2.reset();
			
			/*
			 * Set the drop cooldown so the next piece doesn't automatically come flying
			 * in from the heavens immediately after this piece hits if we've not reacted
			 * yet. (~0.5 second buffer).
			 */
			dropCooldown2 = 25;
			
			/*
			 * Update the difficulty level. This has no effect on the game, and is only
			 * used in the "Level" string in the SidePanel.
			 */
			level2 = (int)(gameSpeed2 * 1.70f);
			
			/*
			 * Spawn a new piece to control.
			 */
			spawnPiece2();
		}		
	}
	
	/**
	 * Forces the BoardPanel and SidePanel to repaint.
	 */
	private void renderGame2() {
		board2.repaint();
		side2.repaint();
	}
	
	/**
	 * Resets the game variables to their default values at the start
	 * of a new game.
	 */
	private void resetGame2() {
		this.level2 = 1;
		this.score2 = 0;
		this.gameSpeed2 = 1.0f;
		this.nextType2 = TileType2.values()[random.nextInt(TYPE_COUNT2)];
		this.isNewGame2 = false;
		this.isGameOver2 = false;		
		board2.clear();
		logicTimer2.reset();
		logicTimer2.setCyclesPerSecond(gameSpeed2);
		spawnPiece2();
	}
		
	/**
	 * Spawns a new piece and resets our piece's variables to their default
	 * values.
	 */
	private void spawnPiece2() {
		/*
		 * Poll the last piece and reset our position and rotation to
		 * their default variables, then pick the next piece to use.
		 */
		this.currentType2 = nextType2;
		this.currentCol2 = currentType2.getSpawnColumn();
		this.currentRow2 = currentType2.getSpawnRow();
		this.currentRotation2 = 0;
		this.nextType2 = TileType2.values()[random.nextInt(TYPE_COUNT2)];
		
		/*
		 * If the spawn point is invalid, we need to pause the game and flag that we've lost
		 * because it means that the pieces on the board have gotten too high.
		 */
		if(!board2.isValidAndEmpty(currentType2, currentCol2, currentRow2, currentRotation2)) {
			this.isGameOver2 = true;
			logicTimer2.setPaused(true);
		}		
	}

	/**
	 * Attempts to set the rotation of the current piece to newRotation.
	 * @param newRotation The rotation of the new peice.
	 */
	private void rotatePiece2(int newRotation) {
		/*
		 * Sometimes pieces will need to be moved when rotated to avoid clipping
		 * out of the board (the I piece is a good example of this). Here we store
		 * a temporary row and column in case we need to move the tile as well.
		 */
		int newColumn = currentCol2;
		int newRow = currentRow2;
		
		/*
		 * Get the insets for each of the sides. These are used to determine how
		 * many empty rows or columns there are on a given side.
		 */
		int left = currentType2.getLeftInset(newRotation);
		int right = currentType2.getRightInset(newRotation);
		int top = currentType2.getTopInset(newRotation);
		int bottom = currentType2.getBottomInset(newRotation);
		
		/*
		 * If the current piece is too far to the left or right, move the piece away from the edges
		 * so that the piece doesn't clip out of the map and automatically become invalid.
		 */
		if(currentCol2 < -left) {
			newColumn -= currentCol2 - left;
		} else if(currentCol2 + currentType2.getDimension() - right >= BoardPanel2.COL_COUNT) {
			newColumn -= (currentCol2 + currentType2.getDimension() - right) - BoardPanel2.COL_COUNT + 1;
		}
		
		/*
		 * If the current piece is too far to the top or bottom, move the piece away from the edges
		 * so that the piece doesn't clip out of the map and automatically become invalid.
		 */
		if(currentRow2 < -top) {
			newRow -= currentRow2 - top;
		} else if(currentRow2 + currentType2.getDimension() - bottom >= BoardPanel2.ROW_COUNT) {
			newRow -= (currentRow2 + currentType2.getDimension() - bottom) - BoardPanel2.ROW_COUNT + 1;
		}
		
		/*
		 * Check to see if the new position is acceptable. If it is, update the rotation and
		 * position of the piece.
		 */
		if(board2.isValidAndEmpty(currentType2,newColumn, newRow, newRotation)) {
			currentRotation2 = newRotation;
			currentRow2 = newRow;
			currentCol2 = newColumn;
		}
	}
	
	/**
	 * Checks to see whether or not the game is paused.
	 * @return Whether or not the game is paused.
	 */
	public boolean isPaused2() {
		return isPaused2;
	}
	
	/**
	 * Checks to see whether or not the game is over.
	 * @return Whether or not the game is over.
	 */
	public boolean isGameOver2() {
		return isGameOver2;
	}
	
	/**
	 * Checks to see whether or not we're on a new game.
	 * @return Whether or not this is a new game.
	 */
	public boolean isNewGame2() {
		return isNewGame2;
	}
	
	/**
	 * Gets the current score.
	 * @return The score.
	 */
	public int getScore2() {
		return score2;
	}
	
	/**
	 * Gets the current level.
	 * @return The level.
	 */
	public int getLevel2() {
		return level2;
	}
	
	/**
	 * Gets the current type of piece we're using.
	 * @return The piece type.
	 */
	public TileType2 getPieceType2() {
		return currentType2;
	}
	
	/**
	 * Gets the next type of piece we're using.
	 * @return The next piece.
	 */
	public TileType2 getNextPieceType2() {
		return nextType2;
	}
	
	/**
	 * Gets the column of the current piece.
	 * @return The column.
	 */
	public int getPieceCol2() {
		return currentCol2;
	}
	
	/**
	 * Gets the row of the current piece.
	 * @return The row.
	 */
	public int getPieceRow2() {
		return currentRow2;
	}
	
	/**
	 * Gets the rotation of the current piece.
	 * @return The rotation.
	 */
	public int getPieceRotation2() {
		return currentRotation2;
	}

	/**
	 * Entry-point of the game. Responsible for creating and starting a new
	 * game instance.
	 * @param args Unused.
	 */

	/*public static void main(String[] args) {
		final Tetris tetris = new Tetris();
		Thread t1 = new Thread(){
		    public void run(){
		    	
		    	tetris.startGame();
		    }};
		    Thread t2 = new Thread(){
			    public void run(){
			    	
			    	tetris.startGame2();
			    }};
			    
			    t1.start();
			    t2.start();
		
		
	}*/

}