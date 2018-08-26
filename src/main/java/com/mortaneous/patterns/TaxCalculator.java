/*
 * TaxCalculator
 */

package com.mortaneous.patterns;

public class TaxCalculator
{
	private SalesTaxRate taxRate;
	private double amount;
	
	public TaxCalculator() {};
	public TaxCalculator(SalesTaxRate taxRate)
	{
		setTaxRate(taxRate);
	}
	public TaxCalculator(SalesTaxRate taxRate, double amount)
	{
		this(taxRate);
		setAmount(amount);
	}
	
	public SalesTaxRate getTaxRate()
	{
		return taxRate;
	}
	public void setTaxRate(SalesTaxRate taxRate)
	{
		this.taxRate = taxRate;
	}
	
	public double getAmount()
	{
		return amount;
	}
	public void setAmount(double amount)
	{
		this.amount = amount;
	}
	
	public double getTaxAmount()
	{
		return amount * taxRate.getTaxRate();
	}
}