/*
 * StateTax
 */

package com.mortaneous.patterns.strategy;

public class StateTax implements SalesTaxRate
{
	private double taxRate;
	private String stateCode;
	
	public StateTax(double taxRate, String stateCode)
	{
		setTaxRate(taxRate);
		setStateCode(stateCode);
	}
	
	@Override
	public double getTaxRate()
	{
		return taxRate;
	}
	public void setTaxRate(double taxRate)
	{
		this.taxRate = taxRate;
	}
	
	public String getStateCode()
	{
		return stateCode;
	}
	public void setStateCode(String stateCode)
	{
		this.stateCode = stateCode;
	}
}