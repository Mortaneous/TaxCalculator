/*
 * TaxDemoW.java
 */

package com.mortaneous.patterns.strategy;

import javax.swing.SwingUtilities;
 
public class TaxDemoW
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater( () -> {
			TaxDemoView view = new TaxDemoView();
			view.setVisible(true);
		});
	}
	
}