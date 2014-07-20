package ulf.gs;

import net.bigpoint.assessment.gasstation.GasPump;
import net.bigpoint.assessment.gasstation.GasStation;
import net.bigpoint.assessment.gasstation.GasType;
import net.bigpoint.assessment.gasstation.exceptions.GasTooExpensiveException;
import net.bigpoint.assessment.gasstation.exceptions.NotEnoughGasException;
import ulf.gs.solution.GasStationImpl;

public class SingleTest
{
	public static void main( String[] args ) throws NotEnoughGasException, GasTooExpensiveException
    {
		GasStationImpl.setTestMode(true);
		
		GasStation keks = new GasStationImpl();
        
        keks.setPrice(GasType.REGULAR, 1.50);
        keks.setPrice(GasType.SUPER, 1.60);
        keks.setPrice(GasType.DIESEL, 1.40);
        
        keks.addGasPump(new GasPump(GasType.REGULAR, 1000.0));
        keks.addGasPump(new GasPump(GasType.SUPER, 1000.0));
        keks.addGasPump(new GasPump(GasType.DIESEL, 1000.0));
        
        System.out.println( new java.util.Date());
        
        keks.buyGas(GasType.DIESEL, 10.0, 2.0);
        System.out.println("Revenue: " + (Double.toString(keks.getRevenue())));
        keks.buyGas(GasType.SUPER, 10.0, 2.0);
        System.out.println("Revenue: " + (Double.toString(keks.getRevenue())));
        keks.buyGas(GasType.DIESEL, 10.0, 2.0);
        
        System.out.println("Revenue: " + (Double.toString(keks.getRevenue())));
        
        System.out.println( new java.util.Date());
    }
}