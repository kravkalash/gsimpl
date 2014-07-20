package ulf.gs.solution;

import static org.junit.Assert.*;
import net.bigpoint.assessment.gasstation.GasPump;
import net.bigpoint.assessment.gasstation.GasStation;
import net.bigpoint.assessment.gasstation.GasType;
import net.bigpoint.assessment.gasstation.exceptions.GasTooExpensiveException;
import net.bigpoint.assessment.gasstation.exceptions.NotEnoughGasException;

import org.junit.Test;

public class GasStationImplTest extends GasStationImpl {

	@Test
	public void test() throws NotEnoughGasException, GasTooExpensiveException {
        GasStation keks = new GasStationImpl();
        
        keks.setPrice(GasType.REGULAR, 1.50);
        keks.setPrice(GasType.SUPER, 1.60);
        keks.setPrice(GasType.DIESEL, 1.40);
        
        keks.addGasPump(new GasPump(GasType.REGULAR, 1000.0));
        keks.addGasPump(new GasPump(GasType.SUPER, 1000.0));
        keks.addGasPump(new GasPump(GasType.DIESEL, 1000.0));
        
        assertEquals(0.0, keks.getRevenue(), 0.00001);
        
        keks.buyGas(GasType.DIESEL, 50.0, 2.0);
        
        assertEquals(70.0, keks.getRevenue(), 0.00001);
        keks.buyGas(GasType.DIESEL, 50.0, 2.0);
        keks.buyGas(GasType.DIESEL, 50.0, 2.0);
	}

}
