package board;

public interface IClockTypeInterface {
	public void setCyclesPerSecond(float cyclesPerSecond);
	public void reset();
	public void update();
	public void setPaused(boolean paused);
	public boolean hasElapsedCycle();
}
