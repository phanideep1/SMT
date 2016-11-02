package edu.umkc.smt.sarika.myextension.clock;

public interface IClock {

	public void setCyclesPerSecond(float cyclesPerSecond);
	public void reset();
	public void update();
	public void setPaused(boolean paused);
	public boolean isPaused();
	public boolean hasElapsedCycle();
	public boolean peekElapsedCycle();
	public void getClock(float cyclesPerSecond);
	
}
