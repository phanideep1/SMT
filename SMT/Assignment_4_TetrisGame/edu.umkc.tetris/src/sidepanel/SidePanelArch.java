package sidepanel;


import tetris.TetrisImpl;
import board.IPieceTypeInterface;
import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

public class SidePanelArch extends AbstractMyxSimpleBrick
{
    public static final IMyxName msg_IPieceTypeInterface = MyxUtils.createName("board.IPieceTypeInterface");

    public IPieceTypeInterface OUT_IPieceType;

	private ISidePanelImp _imp;

    public SidePanelArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ISidePanelImp getImplementation(){
        try{
			return new SidePanelImpl(TetrisImpl.getTetrisImpl());    
        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
        	
    }

    public void init(){
        _imp.init();
    }
    
    public void begin(){
    	System.out.println("begin creating Piece Object");
        OUT_IPieceType = (IPieceTypeInterface) MyxUtils.getFirstRequiredServiceObject(this,msg_IPieceTypeInterface);
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
		return null;
	}
}