import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;


public class SpaceCraftTest {

	@Test
	public void testCalcNewValues() {
		 	 SpaceCraft sc = new SpaceCraft();
		 	 sc.init();
		 	 assertEquals(0, sc.getBurnRate());
		 	
		 	 sc.setBurnRate(1000);
	
		 	 try {
				 sc.calcNewValues();
				 fail("Exception should be thrown");
		 	 } catch (Exception e){
				 ;//Exception expected
		 	 }
		 	
		 	 sc.setBurnRate(5);
		 	 sc.setFuel(45);
		 	 sc.setVelocity(100);
		 	 
		 	 
		 	 try{
				 sc.calcNewValues();
		 	 } catch (Exception e){
				 ;//Ignore this time
		 	 }
		 	 assertEquals(40, sc.getFuel());
		 	 assertEquals(108,sc.getVelocity());
	}
	@Test

	@Ignore
	public void oldTest(){
		 	 fail("If executed, this test should fail.");
	}
}
