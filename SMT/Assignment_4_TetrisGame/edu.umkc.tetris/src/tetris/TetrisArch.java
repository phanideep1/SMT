package tetris;


import board.IBoardInterface;
import board.IClockTypeInterface;
import board.IPieceTypeInterface;
import board.TileType;
import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

public class TetrisArch extends AbstractMyxSimpleBrick implements IPieceTypeInterface
{
    public static final IMyxName msg_IClockTypeInterface = MyxUtils.createName("board.IClockTypeInterface");
    public static final IMyxName msg_IBoardInterface = MyxUtils.createName("board.IBoardInterface");
  
    public static final IMyxName msg_IPieceTypeInterface = MyxUtils.createName("board.IPieceTypeInterface");
    public IBoardInterface OUT_IBoardType;
    public IClockTypeInterface OUT_IClockType;

	private ITetrisImp _imp;

    public TetrisArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ITetrisImp getImplementation(){
      try{
			return new TetrisImpl();    
        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
        
    }

    public void init(){
        _imp.init();
    }
    
    public void begin(){
      OUT_IBoardType = (IBoardInterface) MyxUtils.getFirstRequiredServiceObject(this,msg_IBoardInterface);
      System.out.println("board type object"+MyxUtils.getFirstRequiredServiceObject(this,msg_IBoardInterface));
        if (OUT_IBoardType == null){
 			System.err.println("Error: Interface tertisgame.IBoardType returned null");
			return;       
        }
        OUT_IClockType = (IClockTypeInterface) MyxUtils.getFirstRequiredServiceObject(this,msg_IClockTypeInterface);
        System.out.println("board type object"+OUT_IClockType);
        if (OUT_IClockType == null){
 			System.err.println("Error: Interface tertisgame.IClockType returned null");
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
		if (arg0.equals(msg_IPieceTypeInterface)){
			return this;
		}        
		if (arg0.equals(msg_IPieceTypeInterface)){
			return this;
		}        
		return null;
	}
  
    
    public TileType getPieceType ()   {
		return _imp.getPieceType();
    }    
    public TileType getNextPieceType ()   {
		return _imp.getNextPieceType();
    }    
    public int getPieceCol ()   {
		return _imp.getPieceCol();
    }    
    public int getPieceRow ()   {
		return _imp.getPieceRow();
    }    
    public int getPieceRotation ()   {
		return _imp.getPieceRotation();
    }

	public boolean isPaused() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isNewGame() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getLevel() {
		// TODO Auto-generated method stub
		return 0;
	}    
   
  
}