package ulf.gs;

import net.bigpoint.assessment.gasstation.GasType;
import net.bigpoint.assessment.gasstation.exceptions.GasTooExpensiveException;
import net.bigpoint.assessment.gasstation.exceptions.NotEnoughGasException;

//for testing purposes only
//is run from MultiTest.java
//used to pump some diesel fuel from the static gas station "keks"
public class TestThreadOne implements Runnable
{
	public void run()
	{
		try
		{
			MultiTest.keks.buyGas(GasType.DIESEL, 10.0, 2.0);
		} catch (NotEnoughGasException e) {
		} catch (GasTooExpensiveException e) {
		}		
	}	
}