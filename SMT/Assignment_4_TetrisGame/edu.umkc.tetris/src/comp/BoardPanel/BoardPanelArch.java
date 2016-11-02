package comp.BoardPanel;


import tetris.TetrisImpl;
import board.IBoardInterface;
import board.IPieceTypeInterface;
import board.TileType;
import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

public class BoardPanelArch extends AbstractMyxSimpleBrick implements IBoardInterface
{
    public static final IMyxName msg_IPieceTypeInterface = MyxUtils.createName("board.IPieceTypeInterface");
    public static final IMyxName msg_IBoardInterface = MyxUtils.createName("board.IBoardInterface");
    public IPieceTypeInterface OUT_IPieceType;

   	public IBoardPanelImp _imp;

   	

       public BoardPanelArch (){
       	
   		_imp = getImplementation();
   		if (_imp != null){
   			_imp.setArch(this);
   		} else {
   			System.exit(1);
   		}
   	}
       
       protected IBoardPanelImp getImplementation(){
           try{
   			return new BoardPanelImp(TetrisImpl.getTetrisImpl());    
           } catch (Exception e){
               System.err.println(e.getMessage());
               return null;
           }
       }

       
       
      
       
       public void init(){
           _imp.init();
       }
       
       public void begin(){
       	System.out.println("in board panel begin maethos");
           OUT_IPieceType = (IPieceTypeInterface) MyxUtils.getFirstRequiredServiceObject(this,msg_IPieceTypeInterface);
           System.out.println("in board panel begin maethos"+OUT_IPieceType);
           if (OUT_IPieceType == null){
    			System.err.println("Error: Interface tertisgame.IPieceType returned null");
   			return;       
           }
           _imp.begin();
       }
       
       public void end(){
           _imp.end();
       }
       
       public void destroy(){
           _imp.destroy();
       }
       
   	public Object getServiceObject(IMyxName arg0) {
   		if (arg0.equals(msg_IBoardInterface)){
   			return this;
   		}        
   		return null;
   	}
     
       //To be imported: TileType
       public int checkLines ()   {
   		return _imp.checkLines();
       }    
       public void clear ()   {
   		_imp.clear();
       }    
       public boolean isValidAndEmpty (TileType type,int x,int y,int rotation)   {
   		return _imp.isValidAndEmpty(type,x,y,rotation);
       }    
       public void addPiece (TileType type,int x,int y,int rotation)   {
   		_imp.addPiece(type,x,y,rotation);
       }

   	@Override
   	public BoardPanelImp boardpanelobject() {
   		// TODO Auto-generated method stub
   		return (BoardPanelImp)_imp;
   	}    
}