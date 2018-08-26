/*
 * TaxDemo.java
 */

package com.mortaneous.patterns;

public class TaxDemo
{
	public static void main(String[] args)
	{
		new TaxDemo().run(args);
	}
	
	public void run(String[] args)
	{
		if(args.length < 2) {
			System.out.println("SYNTAX: " + this.getClass().getName() + " <amount> <state>");
			System.exit(0);
		}
		
		SalesTaxRate rateMN = new StateTax(6.5/100, "MN");
		SalesTaxRate rateCA = new StateTax(5.125/100, "CA");
		SalesTaxRate rateNY = new StateTax(0.04, "NY");
		SalesTaxRate rateNM = new StateTax(0.0325, "NM");
		SalesTaxRate rateTX = new StateTax(0.05, "TX");
		
		TaxCalculator taxCalc = new TaxCalculator();
	
		switch(args[1]) {
		case "MN" : taxCalc.setTaxRate(rateMN); break;
		case "CA" : taxCalc.setTaxRate(rateCA); break;
		case "NY" : taxCalc.setTaxRate(rateNY); break;
		case "NM" : taxCalc.setTaxRate(rateNM); break;
		case "TX" : taxCalc.setTaxRate(rateTX); break;
		default:
			System.out.println("Invalid State: " + args[1]);
			System.out.println("Valid values : MN, CA, NY, NM, TX");
			System.exit(0);
		};
		
		taxCalc.setAmount(Double.parseDouble(args[0]));
		System.out.println("Tax for " + args[0] + " in " + args[1] + " is " + taxCalc.getTaxAmount());
	}
}