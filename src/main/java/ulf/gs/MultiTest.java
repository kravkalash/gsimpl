package ulf.gs;

import net.bigpoint.assessment.gasstation.GasPump;
import net.bigpoint.assessment.gasstation.GasStation;
import net.bigpoint.assessment.gasstation.GasType;
import ulf.gs.solution.GasStationImpl;

public class MultiTest
{
	public static GasStation keks = new GasStationImpl();
	
	public static void main( String[] arguments )
	{
		GasStationImpl.setTestMode(true);
		
		//creating sample gas station
        keks.setPrice(GasType.REGULAR, 2.0);
        keks.setPrice(GasType.SUPER, 3.0);
        keks.setPrice(GasType.DIESEL, 1.0);
		
		keks.addGasPump(new GasPump(GasType.REGULAR, 1000.0));
	    keks.addGasPump(new GasPump(GasType.SUPER, 1000.0));
	    keks.addGasPump(new GasPump(GasType.DIESEL, 1000.0));
		
	    //generating threads
		Thread t1 = new Thread( new TestThreadOne() );
		Thread t2 = new Thread( new TestThreadOne() );
		Thread t3 = new Thread( new TestThreadTwo() );
		Thread t4 = new Thread( new TestThreadTwo() );
		Thread t5 = new Thread( new TestThreadOne() );
		Thread t6 = new Thread( new TestThreadOne() );
		Thread t7 = new Thread( new TestThreadTwo() );
		Thread t8 = new Thread( new TestThreadTwo() );
		
		//test output: current date
		System.out.println( new java.util.Date());
		
		//starting all threads
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
	}
}