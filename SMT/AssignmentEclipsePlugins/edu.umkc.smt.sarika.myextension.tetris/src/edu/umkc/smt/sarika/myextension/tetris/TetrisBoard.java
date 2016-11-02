package edu.umkc.smt.sarika.myextension.tetris;


import edu.umkc.smt.sarika.plugin.IGame;

public class TetrisBoard implements IGame {

	@Override
	public void getTetris() {
		// TODO Auto-generated method stub
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
		
		
		

	}

}
