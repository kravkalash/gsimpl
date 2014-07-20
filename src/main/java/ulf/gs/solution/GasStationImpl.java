package ulf.gs.solution;

import java.util.ArrayList;
import java.util.Collection;

import net.bigpoint.assessment.gasstation.GasPump;
import net.bigpoint.assessment.gasstation.GasStation;
import net.bigpoint.assessment.gasstation.GasType;
import net.bigpoint.assessment.gasstation.exceptions.GasTooExpensiveException;
import net.bigpoint.assessment.gasstation.exceptions.NotEnoughGasException;

public class GasStationImpl implements GasStation{
	
	public GasStationImpl() {
		this.gasPumps = new ArrayList<GasPump>();
		this.revenue = 0.0;
		this.priceRegular = 0.0;
		this.priceSuper = 0.0;
		this.priceDiesel = 0.0;
		this.numberOfCancellationsNoGas = 0;
		this.numberOfCancellationsTooExpensive = 0;
		this.numberOfSales = 0;
	}
	
	private Collection<GasPump> gasPumps;
	private double revenue;
	private double priceRegular;
	private double priceSuper;
	private double priceDiesel;
	private int numberOfCancellationsNoGas;
	private int numberOfCancellationsTooExpensive;
	private int numberOfSales;
	
	
	//looks for the pump with the lowest difference from needed to available fuel
	private GasPump getFittingPump(double amountNeeded, GasType gastypeNeeded) throws NotEnoughGasException 
	{
		double amountDifference = Double.MAX_VALUE;
		GasPump fittingPump = null;
		
		for(GasPump gp : this.gasPumps)
		{
			if (gp.getGasType() != gastypeNeeded) continue;
			if (gp.getRemainingAmount() < amountNeeded) continue;
			if (amountDifference > gp.getRemainingAmount() - amountNeeded)
			{
				amountDifference = gp.getRemainingAmount() - amountNeeded;
				fittingPump = gp;
			}
		}
		
		if (fittingPump == null)
		{
			numberOfCancellationsNoGas++;
			throw new NotEnoughGasException();
		}
		
		return fittingPump;
	}
	
	public void addGasPump(GasPump pump) {
		this.gasPumps.add(pump);		
	}
	
	//returns a deep copy of gasPumps
	public Collection<GasPump> getGasPumps() {
		Collection<GasPump> ret = new ArrayList<GasPump>();
		for(GasPump gp : this.gasPumps)
		{
			GasPump newgp = new GasPump(gp.getGasType(), gp.getRemainingAmount());
			ret.add(newgp);
		}
		return ret;
	}

	//handles a customer buying gas
	public double buyGas(GasType type, double amountInLiters,
			double maxPricePerLiter) throws NotEnoughGasException,
			GasTooExpensiveException {
		
		if(this.getPrice(type) > maxPricePerLiter)
		{
			numberOfCancellationsTooExpensive++;
			throw new GasTooExpensiveException();
		}
		
		GasPump gp = getFittingPump(amountInLiters, type);
		
		this.numberOfSales++;
		this.revenue += amountInLiters * this.getPrice(type);
		gp.pumpGas(amountInLiters);
		
		return amountInLiters * this.getPrice(type);
	}

	public double getRevenue() {
		return this.revenue;
	}

	public int getNumberOfSales() {
		return this.numberOfSales;
	}

	public int getNumberOfCancellationsNoGas() {
		return this.numberOfCancellationsNoGas;
	}

	public int getNumberOfCancellationsTooExpensive() {
		return this.numberOfCancellationsTooExpensive;
	}

	public double getPrice(GasType type) {
		switch (type)
		{
			case REGULAR:	return this.priceRegular;
			case SUPER:		return this.priceSuper;
			case DIESEL:	return this.priceDiesel;
			default:		return -1.0;
		}
	}

	public void setPrice(GasType type, double price) {
		switch (type)
		{
			case REGULAR:	this.priceRegular = price;
			case SUPER:		this.priceSuper   = price;
			case DIESEL:	this.priceDiesel  = price;
		}
	}
}
