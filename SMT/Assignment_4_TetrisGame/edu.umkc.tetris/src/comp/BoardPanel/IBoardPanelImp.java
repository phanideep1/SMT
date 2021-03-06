package comp.BoardPanel;


import board.TileType;

public interface IBoardPanelImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (BoardPanelArch arch);
	public BoardPanelArch getArch();
	
	/*
  	  Myx Lifecycle Methods: these methods are called automatically by the framework
  	  as the bricks are created, attached, detached, and destroyed respectively.
	*/	
	public void init();	
	public void begin();
	public void end();
	public void destroy();

	/*
  	  Implementation primitives required by the architecture
	*/
  
    
    public boolean isValidAndEmpty (TileType type,int x,int y,int rotation)  ;        
    public void addPiece (TileType type,int x,int y,int rotation)  ;        
    public int checkLines ()  ;        
    public void clear ()  ;        
}