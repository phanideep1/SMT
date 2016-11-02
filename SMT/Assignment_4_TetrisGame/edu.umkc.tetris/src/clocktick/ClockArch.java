package clocktick;


import board.IClockTypeInterface;

import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

public class ClockArch extends AbstractMyxSimpleBrick implements IClockTypeInterface
{
    public static final IMyxName msg_IClockTypeInterface = MyxUtils.createName("board.IClockTypeInterface");


	private IClockTickImp _imp;

    public ClockArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IClockTickImp getImplementation(){
        try{
			return new ClockImpl();    
        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void init(){
        _imp.init();
    }
    
    public void begin(){
        _imp.begin();
    }
    
    public void end(){
        _imp.end();
    }
    
    public void destroy(){
        _imp.destroy();
    }
    
	public Object getServiceObject(IMyxName arg0) {
		if (arg0.equals(msg_IClockTypeInterface)){
			return this;
		}        
		return null;
	}
  
    
    public void setCyclesPerSecond (float cyclesPerSecond)   {
		_imp.setCyclesPerSecond(cyclesPerSecond);
    }    
    public void reset ()   {
		_imp.reset();
    }    
    public void update ()   {
		_imp.update();
    }    
    public void setPaused (boolean paused)   {
		_imp.setPaused(paused);
    }    
    public boolean hasElapsedCycle ()   {
		return _imp.hasElapsedCycle();
    }    
}