package ulf.gs;

import net.bigpoint.assessment.gasstation.GasType;
import net.bigpoint.assessment.gasstation.exceptions.GasTooExpensiveException;
import net.bigpoint.assessment.gasstation.exceptions.NotEnoughGasException;

//for testing purposes only
//is run from MultiTest.java
//used to pump some regular fuel from the static gas station "keks"
public class TestThreadTwo implements Runnable
{
	public void run()
	{
		try
		{
			MultiTest.keks.buyGas(GasType.REGULAR, 4.0, 2.0);
		} catch (NotEnoughGasException e) {
		} catch (GasTooExpensiveException e) {
		}		
	}	
}