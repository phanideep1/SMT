package board;

public interface IPieceTypeInterface {
	
	public TileType getPieceType();
	public TileType getNextPieceType();
	public int getPieceCol();
	public int getPieceRow();
	public int getPieceRotation();
	
}
